package com.dis.bot.repository.dnd;

import com.dis.bot.character.DNDCharacter;
import com.dis.bot.repository.Characters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DndCharacters {

    final Map<String, DNDCharacter> dndCharacters;
    final Map<String, DNDCharacter> dndCharactersFromMembers;

    private final Characters characters;

    public DndCharacters(Characters characters) {
        this.characters = characters;
        this.dndCharacters = new HashMap<>();
        this.dndCharactersFromMembers = new HashMap<>();
    }


    public DNDCharacter create(String memberName, String name, Long hp, Long ac){
        var dndCharacter = new DNDCharacter(name, hp);
        dndCharacters.put(name, dndCharacter);
        dndCharactersFromMembers.putIfAbsent(memberName, dndCharacter);
        dndCharacter.setArmorClass(ac);
        characters.store(memberName, dndCharacter);
        return dndCharacter;
    }

    public DNDCharacter get(String characterName) {
        DNDCharacter result = dndCharacters.get(characterName);
        if(result == null){
            log.error("No dnd character found with name [{}]", characterName);
            throw new NullPointerException(characterName);
        }
        return result;
    }

    public DNDCharacter store(String memberName, DNDCharacter dndCharacter) {
        dndCharacters.putIfAbsent(dndCharacter.getName(), dndCharacter);
        dndCharactersFromMembers.putIfAbsent(memberName, dndCharacter);
        characters.store(memberName, dndCharacter);
        return dndCharacter;
    }
}
