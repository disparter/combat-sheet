package com.dis.bot.service.dnd;

import com.dis.bot.character.RPGCharacter;
import com.dis.bot.repository.dnd.DndCharacters;
import org.springframework.stereotype.Service;

import static com.dis.bot.tool.D20Roll.rollD20WithBonus;

@Service
public class ArmorClassService {

    private final DndCharacters characters;

    public ArmorClassService(DndCharacters characters){
        this.characters = characters;
    }

    public RPGCharacter setArmorClass(String characterName, Long ac) {
        var result = characters.get(characterName);
        result.setArmorClass(ac);
        return result;
    }

    public HitResult hit(String characterName, Long bonusHit) {
        var foe = characters.get(characterName);

        var attackTotal = rollD20WithBonus(bonusHit);
        var isHit = attackTotal >= foe.getArmorClass();

        return HitResult.builder()
                .ac(foe.getArmorClass())
                .targetName(foe.getName())
                .totalHitValue(attackTotal)
                .isHit(isHit)
                .attackBonus(bonusHit)
                .roll(attackTotal - bonusHit)
                .build();
    }



}
