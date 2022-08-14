package com.dis.bot.service;

import com.dis.bot.pojo.character.RPGCharacter;
import com.dis.bot.repository.Characters;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static com.dis.bot.tool.StringTool.padTo;

@Service
public class HealthPointsService {

    private final Characters characters;

    public HealthPointsService(Characters characters){
        this.characters = characters;
    }

    public RPGCharacter setCurrentHP(String characterName, Long hp) {
        var result = characters.get(characterName);
        result.setCurrentHealthPoints(hp);
        return result;
    }

    public RPGCharacter applyDamage(String characterName, Long dmg) {
        var result = characters.get(characterName);
        result.applyDamage(dmg);
        return result;
    }

    public String showHpTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("HP Table:::\n");
        sb.append("```\n");
        characters.getAllSelectedCharacters().values().stream()
                .sorted(Comparator.comparingLong(RPGCharacter::getInitiative).reversed())
                .forEach(character ->
                        sb.append(String.format("\t%s\t%d/%d%n", padTo(16, character.getName()),
                                character.getCurrentHealthPoints(),
                                character.getHealthPoints())));
        sb.append("```");
        return sb.toString();
    }
    
}
