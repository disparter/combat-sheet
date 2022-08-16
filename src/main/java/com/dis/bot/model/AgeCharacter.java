package com.dis.bot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("age_characters")
@Data
public class AgeCharacter {

    @Id
    private String id;

    private String name;
    private List<String> classes;
    private Long level;
    private Long hp;
    private Long armorClass;
    private Long physicalDefense;
    private Long mentalDefense;
    private Long strength;
    private Long dexterity;
    private Long constitution;
    private Long intelligence;
    private Long wisdom;
    private Long charisma;

    private Long currentHp;
    private Long currentArmorClass;
    private Long currentPhysicalDefense;
    private Long currentMentalDefense;
    private Long currentBonusStrength;
    private Long currentBonusDexterity;
    private Long currentBonusConstitution;
    private Long currentBonusIntelligence;
    private Long currentBonusWisdom;
    private Long currentBonusCharisma;

    //TODO finish the model
}
