package com.dis.bot.exception;

public class AgeCharacterNotFoundException extends CharacterException {
    private final static String MESSAGE = "No 13th age rpg character found with name";

    public AgeCharacterNotFoundException(String characterName){
        super(String.format("%s %s", MESSAGE, characterName));
    }
}
