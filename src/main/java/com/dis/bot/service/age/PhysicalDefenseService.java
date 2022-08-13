package com.dis.bot.service.age;

import com.dis.bot.character.RPGCharacter;
import com.dis.bot.repository.age.AgeCharacters;
import org.springframework.stereotype.Service;

import static com.dis.bot.tool.D20Roll.rollD20WithBonus;

@Service
public class PhysicalDefenseService {

    private final AgeCharacters characters;

    public PhysicalDefenseService(AgeCharacters characters){
        this.characters = characters;
    }

    public RPGCharacter setPhysicalDefense(String characterName, Long pd) {
        var result = characters.get(characterName);
        result.setPhysicalDefense(pd);
        return result;
    }

    public PhysicalHitResult hit(String characterName, Long bonusHit) {
        var foe = characters.get(characterName);

        var attackTotal = rollD20WithBonus(bonusHit);
        var isHit = attackTotal >= foe.getPhysicalDefense();

        return PhysicalHitResult.builder()
                .pd(foe.getPhysicalDefense())
                .targetName(foe.getName())
                .totalHitValue(attackTotal)
                .isHit(isHit)
                .attackBonus(bonusHit)
                .roll(attackTotal - bonusHit)
                .build();
    }

}
