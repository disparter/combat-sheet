package com.dis.bot.exception;

public class CharacterForMemberNotFoundException extends CharacterException {
    private final static String MESSAGE = "No character found for member";

    public CharacterForMemberNotFoundException(String memberName){
        super(String.format("%s %s", MESSAGE, memberName));
    }
}
