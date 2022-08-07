package com.dis.bot.character;

public class DNDNextCharacter extends DNDCharacter {
    Long strengthDefense;
    Long dexterityDefense;
    Long constitutionDefense;
    Long intelligenceDefense;
    Long wisdomDefense;
    Long charismaDefense;

    public DNDNextCharacter(String name, Long healthPoints) {
        super(name, healthPoints);
    }
}
