package com.dis.bot.service.age;

import com.dis.bot.model.age.AgeCharacter;
import com.dis.bot.repository.inmemory.age.AgeCharacters;
import com.dis.bot.repository.mongo.AgeCharacterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AgeCharacterService {

    private final AgeCharacters characters;
    private final AgeCharacterRepository repository;

    public AgeCharacterService(AgeCharacters characters, AgeCharacterRepository repository){
        this.characters = characters;
        this.repository = repository;
    }

    public void create(String name, Long initiative, Long hp, Long ac, Long pd, Long md) {
        var character = characters.create(name, initiative, hp, ac, pd, md);
        log.info("13th Age Character [{}] was created", character.getName());
    }

    public void createInMemory(AgeCharacter character) {
        characters.create(character.getName(), 0L, character.getHp(), character.getArmorClass(),
                character.getPhysicalDefense(), character.getMentalDefense());
    }

    public AgeCharacter fullCreate(String memberId, String memberName, String characterName, Long hp, Long ac, Long pd,
                                   Long md) {
        var character = AgeCharacter.builder()
                .id(UUID.randomUUID().toString())
                .name(characterName)
                .memberId(memberId)
                .memberUsername(memberName)
                .hp(hp)
                .currentHp(hp)
                .armorClass(ac)
                .currentArmorClass(ac)
                .physicalDefense(pd)
                .currentPhysicalDefense(pd)
                .mentalDefense(md)
                .currentMentalDefense(md)
                .build();

        return repository.insert(character);
    }
}
