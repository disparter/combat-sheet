package com.dis.bot.model.age;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Document("age_characters")
@Data
public class AgeCharacter {

    @Id
    private String id;
    private String name;
    private String oneUniqueThing;
    private String race;
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
    private Long recoveries;
    private Set<AgeSkill> skills;
    private List<AgePower> racialPowers;
    private List<AgePower> powers;
    private List<AgeSpell> spells;
    private List<AgePower> classFeatures;
    private List<AgeFeat> feats;

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
    private Long currentRecoveries;

    private Map<String, Long> iconRelationships;
    private Set<AgeEffect> activeEffects;
    private List<AgeEquipment> regularEquipment;
    private List<AgeEquipment> magicItems;
    private Long goldPieces;


}
