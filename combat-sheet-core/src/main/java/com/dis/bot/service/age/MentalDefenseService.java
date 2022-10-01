package com.dis.bot.service.age;

import com.dis.bot.pojo.character.RPGCharacter;
import com.dis.bot.repository.inmemory.age.AgeCharacters;
import org.springframework.stereotype.Service;

import static com.dis.bot.tool.D20Roll.rollD20WithBonus;

@Service
public class MentalDefenseService {

    private final AgeCharacters characters;

    public MentalDefenseService(AgeCharacters characters){
        this.characters = characters;
    }

    public RPGCharacter setMentalDefense(String characterName, Long md) {
        var result = characters.get(characterName);
        result.setMentalDefense(md);
        return result;
    }

    public MentalHitResult hit(String characterName, Long bonusHit) {
        var foe = characters.get(characterName);

        var attackTotal = rollD20WithBonus(bonusHit);
        var isHit = attackTotal >= foe.getMentalDefense();

        return MentalHitResult.builder()
                .md(foe.getMentalDefense())
                .targetName(foe.getName())
                .totalHitValue(attackTotal)
                .isHit(isHit)
                .attackBonus(bonusHit)
                .roll(attackTotal - bonusHit)
                .build();
    }

}
