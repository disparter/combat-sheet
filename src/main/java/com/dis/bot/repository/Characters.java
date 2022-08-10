package com.dis.bot.repository;

import com.dis.bot.character.RPGCharacter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Characters {

    final Map<String, RPGCharacter> characters;
    final Map<String, RPGCharacter> charactersFromMembers;


    public Characters() {
        this.charactersFromMembers = new HashMap<>();
        this.characters = new HashMap<>();
    }

    public RPGCharacter create(String name, Long initiative){
        var result = characters.getOrDefault(name, new RPGCharacter(name, 0L));
        result.setInitiative(initiative);
        characters.putIfAbsent(name, result);
        return result;
    }

    public RPGCharacter getOrCreate(String memberName, String name, Long hp){
        var result = characters.getOrDefault(name, new RPGCharacter(name, hp));
        characters.putIfAbsent(name, result);
        charactersFromMembers.putIfAbsent(memberName, result);
        return result;
    }

    public RPGCharacter get(String characterName) {
        var result = characters.get(characterName);
        if(result == null) {
            log.error("No character found with name [{}]", characterName);
            throw new NullPointerException(characterName);
        }
        return result;
    }

    public RPGCharacter getWithMemberName(String memberName) {
        var result = this.charactersFromMembers.get(memberName);
        if(result == null) {
            log.error("No character found for member [{}]", memberName);
            throw new NullPointerException(memberName);
        }
        return result;
    }
    public Map<String, RPGCharacter> getAll() {
        return characters;
    }

    public Map<String, RPGCharacter> getAllSelectedCharacters() {
        return charactersFromMembers;
    }

    public RPGCharacter store(String memberName, RPGCharacter character){
        characters.putIfAbsent(character.getName(), character);
        charactersFromMembers.putIfAbsent(memberName, character);
        return character;
    }

}
