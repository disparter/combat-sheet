package com.dis.bot.exception;

public class CombatNotFoundException extends CombatException {
    private final static String MESSAGE = "Combat not found";

    public CombatNotFoundException(String characterName){
        super(String.format("%s %s", MESSAGE, characterName));
    }
}
