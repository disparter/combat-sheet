package com.dis.bot.pojo.character;

import com.dis.bot.pojo.combat.Effect;
import lombok.Getter;
import lombok.Setter;

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
        this.initiative = 0l;
        this.effects = new HashSet<>();
    }

    public void applyDamage(Long damage){
        this.currentHealthPoints -= damage;
    }

    public void applyEffect(Effect effect){
        this.effects.add(effect);
    }

}
