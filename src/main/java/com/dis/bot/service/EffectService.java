package com.dis.bot.service;

import com.dis.bot.pojo.character.RPGCharacter;
import com.dis.bot.pojo.combat.Effect;
import com.dis.bot.repository.Characters;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EffectService {

    private final Characters characters;

    public EffectService(Characters characters){
        this.characters = characters;
    }

    public RPGCharacter applyEffect(String characterName, String effectDescription,
                                    Long duration, Long round) {
        var character = characters.get(characterName);
        var effect = Effect.builder()
                .active(true)
                .description(effectDescription)
                .startRound(round)
                .duration(duration)
                .build();
        character.applyEffect(effect);
        return character;
    }

    public RPGCharacter removeEffect(String characterName, String effect) {
        var character = characters.get(characterName);
        character.removeEffect(effect);
        return character;
    }

    public void moveRound(Set<RPGCharacter> instanceCharacters, long round){
        instanceCharacters.forEach(character -> character.getEffects().forEach(effect -> effect.moveRound(round)));
    }
}
