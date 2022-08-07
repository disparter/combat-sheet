package com.dis.bot.repository;

import com.dis.bot.character.RPGCharacter;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.dis.bot.tool.StringTool.padTo;

@Component
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

    public RPGCharacter getOrCreate(String name, Long hp, String memberName){
        var result = characters.getOrDefault(name, new RPGCharacter(name, hp));
        characters.putIfAbsent(name, result);
        charactersFromMembers.putIfAbsent(memberName, result);
        return result;
    }

    public RPGCharacter setCurrentHP(String characterName, Long hp) {
        var result = characters.get(characterName);
        if(result == null) {
            throw new NullPointerException(characterName);
        }
        result.setCurrentHealthPoints(hp);
        return result;
    }

    public RPGCharacter applyDamage(String characterName, Long dmg) {
        var result = characters.get(characterName);
        if(result == null) {
            throw new NullPointerException(characterName);
        }
        result.applyDamage(dmg);
        return result;
    }

    public String showHpTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("HP Table:::\n");
        sb.append("```\n");
        characters.values().stream()
                .sorted(Comparator.comparingLong(RPGCharacter::getInitiative).reversed())
                .forEach(character ->
                        sb.append(String.format("\t%s\t%d/%d%n", padTo(16, character.getName()),
                                character.getCurrentHealthPoints(),
                                character.getHealthPoints())));
        sb.append("```");
        return sb.toString();
    }


    public RPGCharacter get(String characterName) {
        var result = characters.get(characterName);
        if(result == null) {
            throw new NullPointerException(characterName);
        }
        return result;
    }

    public RPGCharacter getWithMemberName(String memberName) {
        var result = this.charactersFromMembers.get(memberName);
        if(result == null) {
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
}
