package com.dis.bot.service;

import com.dis.bot.character.RPGCharacter;
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

    public RPGCharacter setInitiative(String characterName, Long initiative) {
        var result = this.characters.get(characterName);
        result.setInitiative(initiative);
        return result;
    }

    public String showInitiativeTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("Initiative Table:::\n");
        sb.append("```\n");
        this.characters.getAll().values().stream()
                .sorted(Comparator.comparingLong(RPGCharacter::getInitiative).reversed())
                .forEach(character ->
                        sb.append(String.format("\t%s\t%d%n", padTo(16, character.getName()), character.getInitiative())));
        sb.append("```");
        return sb.toString();
    }

    public RPGCharacter setInitiativeFromMember(String memberName, Long initiative) {
        var result = characters.getWithMemberName(memberName);
        result.setInitiative(initiative);
        return result;
    }

    public RPGCharacter rollD20InitiativeFromMemberWithBonus(String memberName, Long bonus) {
        var result = characters.getWithMemberName(memberName);
        result.setInitiative(rollD20WithBonus(bonus));
        return result;
    }

    public RPGCharacter rollD20InitiativeFromMember(String memberName) {
        return rollD20InitiativeFromMemberWithBonus(memberName, 0L);
    }

}
