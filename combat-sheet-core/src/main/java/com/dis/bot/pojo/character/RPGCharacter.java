package com.dis.bot.pojo.character;

import com.dis.bot.exception.EffectNotFoundException;
import com.dis.bot.pojo.combat.Effect;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class RPGCharacter {
    @Getter
    String name;
    @Getter
    Long healthPoints;
    @Getter @Setter
    Long currentHealthPoints;
    @Getter @Setter
    Long initiative;

    @Getter @Setter
    Set<Effect> effects;

    public RPGCharacter(String name, Long healthPoints){
        this.name = name;
        this.healthPoints = healthPoints;
        this.currentHealthPoints = healthPoints;
        this.initiative = 0L;
        this.effects = new HashSet<>();
    }

    public void applyDamage(Long damage){
        this.currentHealthPoints -= damage;
    }

    public void applyEffect(Effect effect){
        this.effects.add(effect);
    }

    public void removeEffect(String effectDescription){
       var effect = this.effects.stream()
               .filter(ef -> ef.getDescription().equals(effectDescription))
               .findFirst()
               .orElseThrow(() -> new EffectNotFoundException(effectDescription));

       effect.setActive(false);
    }

    public String getActiveEffects(long round){
        StringBuilder sb = new StringBuilder();
        effects.stream().filter(Effect::isActive)
                .sorted(Comparator.comparing(Effect::getStartRound))
                .forEach(effect -> sb
                        .append("[")
                        .append(effect.getDescription())
                        .append("|")
                        .append((effect.getDuration()) - (round - effect.getStartRound()))
                        .append("]"));
        return sb.toString();
    }
}
