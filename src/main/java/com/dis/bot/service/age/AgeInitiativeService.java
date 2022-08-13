package com.dis.bot.service.age;

import com.dis.bot.pojo.character.RPGCharacter;
import com.dis.bot.pojo.combat.Combat;
import com.dis.bot.repository.age.AgeCharacters;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static com.dis.bot.tool.StringTool.padTo;

@Service
public class AgeInitiativeService {

    private final AgeCharacters characters;

    public AgeInitiativeService(AgeCharacters characters){
        this.characters = characters;
    }

    public String showInitiativeTable(Combat combat){
        StringBuilder sb = new StringBuilder();
        sb.append("13th Age Initiative Table ::: Combat id [").append(combat.getId())
                .append("] ::: Round ").append(combat.getRound()).append(" \n");
        sb.append("```\n");
        sb.append("\tName        \tInitiative\t\tHP\tAC\tPD\tMD\n");
        this.characters.getAll().values().stream()
                .sorted(Comparator.comparingLong(RPGCharacter::getInitiative).reversed())
                .forEach(character ->
                        sb.append(String.format("\t%s\t%d    \t\t%d\t%d\t%d\t%d%n", padTo(16, character.getName()),
                                character.getInitiative(),
                                character.getHealthPoints(),
                                character.getArmorClass(),
                                character.getPhysicalDefense(),
                                character.getMentalDefense())));
        sb.append("```");
        return sb.toString();
    }

}
