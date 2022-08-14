package com.dis.bot.repository.age;

import com.dis.bot.pojo.character.ThirteenthAgeRPGCharacter;
import com.dis.bot.exception.AgeCharacterNotFoundException;
import com.dis.bot.repository.dnd.DndCharacters;
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
        dndCharacters.store(ageCharacter);
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
        dndCharacters.store(memberName, ageCharacter);
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
