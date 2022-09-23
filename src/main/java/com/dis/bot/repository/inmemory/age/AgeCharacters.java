package com.dis.bot.repository.inmemory.age;

import com.dis.bot.pojo.character.ThirteenthAgeRPGCharacter;
import com.dis.bot.exception.AgeCharacterNotFoundException;
import com.dis.bot.repository.inmemory.dnd.DndCharacters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AgeCharacters {

    final Map<String, ThirteenthAgeRPGCharacter> ageCharacters;
    final Map<String, ThirteenthAgeRPGCharacter> ageCharactersFromMembers;

    private final DndCharacters dndCharacters;

    public AgeCharacters(DndCharacters characters) {
        this.dndCharacters = characters;
        this.ageCharacters = new HashMap<>();
        this.ageCharactersFromMembers = new HashMap<>();
    }

    public ThirteenthAgeRPGCharacter create(String name, Long initiative, Long hp, Long ac, Long pd, Long md){
        var ageCharacter = new ThirteenthAgeRPGCharacter(name, hp);
        ageCharacters.put(name, ageCharacter);
        ageCharacter.setInitiative(initiative);
        ageCharacter.setArmorClass(ac);
        ageCharacter.setPhysicalDefense(pd);
        ageCharacter.setMentalDefense(md);
        var dndCharacter = dndCharacters.store(ageCharacter);
        log.info("dnd character was successfully stored {}", dndCharacter.getName());
        return ageCharacter;
    }

    public ThirteenthAgeRPGCharacter create(String memberName, String name,
                                            Long hp, Long ac, Long pd, Long md){
        var ageCharacter = new ThirteenthAgeRPGCharacter(name, hp);
        ageCharacters.put(name, ageCharacter);
        ageCharactersFromMembers.putIfAbsent(memberName, ageCharacter);
        ageCharacter.setArmorClass(ac);
        ageCharacter.setPhysicalDefense(pd);
        ageCharacter.setMentalDefense(md);
        var dndCharacter = dndCharacters.store(memberName, ageCharacter);
        log.info("dnd character was sucecssfully stored {}", dndCharacter.getName());
        return ageCharacter;
    }

    public ThirteenthAgeRPGCharacter get(String characterName) {
        ThirteenthAgeRPGCharacter result = ageCharacters.get(characterName);
        if(result == null){
            log.error("No 13th age rpg character found with name [{}]", characterName);
            throw new AgeCharacterNotFoundException(characterName);
        }
        return result;
    }

    public Map<String, ThirteenthAgeRPGCharacter> getAll() {
        return ageCharacters;
    }

}
