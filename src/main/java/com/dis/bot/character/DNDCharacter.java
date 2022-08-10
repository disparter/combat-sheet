package com.dis.bot.character;


import lombok.Getter;
import lombok.Setter;

public class DNDCharacter extends RPGCharacter {

    @Getter @Setter
    Long armorClass;

    public DNDCharacter(String name, Long healthPoints) {
        super(name, healthPoints);
    }
}
