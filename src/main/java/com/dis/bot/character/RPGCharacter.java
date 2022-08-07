package com.dis.bot.character;

import lombok.Getter;
import lombok.Setter;

public class RPGCharacter {
    @Getter
    String name;
    @Getter
    Long healthPoints;
    @Getter @Setter
    Long currentHealthPoints;
    @Getter @Setter
    Long initiative;

    public RPGCharacter(String name, Long healthPoints){
        this.name = name;
        this.healthPoints = healthPoints;
        this.currentHealthPoints = healthPoints;
        this.initiative = 0l;
    }

    public void applyDamage(Long damage){
        this.currentHealthPoints -= damage;
    }

}
