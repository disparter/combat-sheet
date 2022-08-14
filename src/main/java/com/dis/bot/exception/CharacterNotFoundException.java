package com.dis.bot.exception;

public class CharacterNotFoundException extends CharacterException {
    private final static String MESSAGE = "Character not found";

    public CharacterNotFoundException(String characterName){
        super(String.format("%s %s", MESSAGE, characterName));
    }
}
