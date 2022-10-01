package com.dis.bot.pojo.character;

import lombok.Getter;
import lombok.Setter;

public class ThirteenthAgeRPGCharacter extends DNDCharacter {
    @Getter
    @Setter
    Long mentalDefense;

    @Getter
    @Setter
    Long physicalDefense;

    public ThirteenthAgeRPGCharacter(String name, Long healthPoints) {
        super(name, healthPoints);
    }
}
