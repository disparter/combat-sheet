package com.dis.bot.repository;

import com.dis.bot.character.RPGCharacter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class Characters {

    final Map<String, RPGCharacter> characters;
    final Map<String, RPGCharacter> charactersFromMembers;


    public Characters() {
        this.charactersFromMembers = new HashMap<>();
        this.characters = new HashMap<>();
    }

    public RPGCharacter getOrCreate(String name, Long hp, String memberName){
        var result = characters.getOrDefault(name, new RPGCharacter());
        result.setHealthPoints(hp);
        result.setName(name);
        characters.putIfAbsent(name, result);
        charactersFromMembers.putIfAbsent(memberName, result);
        return result;
    }

    public RPGCharacter setInitiative(String characterName, Long initiative) {
        var result = characters.get(characterName);
        if(result == null) {
            throw new NullPointerException(characterName);
        }
        result.setInitiative(initiative);
        return result;
    }

    public RPGCharacter setInitiativeFromMember(String memberName, Long initiative) {
        var result = charactersFromMembers.get(memberName);
        result.setInitiative(initiative);
        return result;
    }

    public RPGCharacter rollD20InitiativeFromMember(String memberName) {
        var result = charactersFromMembers.get(memberName);
        result.setInitiative((long)new Random().nextInt(20));
        return result;
    }

}
