package com.dis.bot.repository;

import com.dis.bot.character.RPGCharacter;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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

    public RPGCharacter create(String name, Long initiative){
        var result = characters.getOrDefault(name, new RPGCharacter(name, 0l));
        result.setInitiative(initiative);
        characters.putIfAbsent(name, result);
        return result;
    }

    public RPGCharacter getOrCreate(String name, Long hp, String memberName){
        var result = characters.getOrDefault(name, new RPGCharacter(name, hp));
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

    public String showInitiativeTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("Initiative Table:::\n");
        sb.append("```\n");
        characters.values().stream()
                .sorted(Comparator.comparingLong(RPGCharacter::getInitiative).reversed())
                .forEach(character ->
                        sb.append(String.format("\t%s\t%d%n", padTo(16, character.getName()), character.getInitiative())));
        sb.append("```");
        return sb.toString();
    }

    public String padTo(Integer maxLength, String value){
        var trimToIndex = maxLength>value.length()?value.length():maxLength;
        StringBuilder sb = new StringBuilder(value.substring(0, trimToIndex));
        for(int i = 0; i < maxLength; i++) {
            if(i >= value.length()){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public RPGCharacter setCurrentHP(String characterName, Long hp) {
        var result = characters.get(characterName);
        if(result == null) {
            throw new NullPointerException(characterName);
        }
        result.setCurrentHealthPoints(hp);
        return result;
    }
}
