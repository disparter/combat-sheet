package com.dis.bot.service;

import com.dis.bot.pojo.character.RPGCharacter;
import com.dis.bot.pojo.combat.Combat;
import com.dis.bot.repository.Characters;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static com.dis.bot.tool.D20Roll.rollD20WithBonus;
import static com.dis.bot.tool.StringTool.padTo;

@Service
public class InitiativeService {

    private final Characters characters;

    public InitiativeService(Characters characters){
        this.characters = characters;
    }

    public RPGCharacter setInitiative(String characterName, Long initiative, Combat combat) {
        var character = this.characters.get(characterName);
        character.setInitiative(initiative);
        combat.addCharacter(character);
        return character;
    }

    public String showInitiativeTable(Combat combat){
        StringBuilder sb = new StringBuilder();
        sb.append("Initiative Table ::: Combat id ::: [").append(combat.getId()).append("]\n");
        sb.append("```\n");
        combat.getCharacters().stream()
                .sorted(Comparator.comparingLong(RPGCharacter::getInitiative).reversed())
                .forEach(character ->
                        sb.append(String.format("\t%s\t%d%n", padTo(16, character.getName()), character.getInitiative())));
        sb.append("```");
        return sb.toString();
    }

    public RPGCharacter setInitiativeFromMember(String memberName, Long initiative, Combat combat) {
        var character = characters.getWithMemberName(memberName);
        character.setInitiative(initiative);
        combat.addCharacter(character);
        return character;
    }

    public RPGCharacter rollD20InitiativeFromMemberWithBonus(String memberName, Long bonus, Combat combat) {
        var character = characters.getWithMemberName(memberName);
        character.setInitiative(rollD20WithBonus(bonus));
        combat.addCharacter(character);
        return character;
    }

    public RPGCharacter rollD20InitiativeFromMember(String memberName, Combat combat) {
        return rollD20InitiativeFromMemberWithBonus(memberName, 0L, combat);
    }

}
