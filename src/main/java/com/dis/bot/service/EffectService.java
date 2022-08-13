package com.dis.bot.service;

import com.dis.bot.pojo.character.RPGCharacter;
import com.dis.bot.pojo.combat.Effect;
import com.dis.bot.repository.Characters;
import org.springframework.stereotype.Service;

@Service
public class EffectService {

    private final Characters characters;

    public EffectService(Characters characters){
        this.characters = characters;
    }

    public RPGCharacter applyEffect(String characterName, String effectDescription, Long duration) {
        var character = characters.get(characterName);
        var effect = Effect.builder()
                .active(true)
                .description(effectDescription)
                .duration(duration)
                .build();
        character.applyEffect(effect);
        return character;
    }

    public RPGCharacter removeEffect(String characterName, String effect) {
        var character = characters.get(characterName);
        character.removeEffect(Effect.builder().description(effect).build());
        return character;
    }
}
