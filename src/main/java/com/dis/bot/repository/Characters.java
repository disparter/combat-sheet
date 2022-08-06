package com.dis.bot.repository;

import com.dis.bot.character.RPGCharacter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Characters {

    final Map<String, RPGCharacter> characters;

    public Characters() {
        this.characters = new HashMap<>();
    }

    public RPGCharacter getOrCreate(String name, Long hp){
        var result = characters.getOrDefault(name, new RPGCharacter());
        result.setHealthPoints(hp);
        result.setName(name);
        characters.putIfAbsent(name, result);
        return result;
    }

}
