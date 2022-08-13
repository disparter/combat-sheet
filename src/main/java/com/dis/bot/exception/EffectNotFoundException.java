package com.dis.bot.exception;

public class EffectNotFoundException extends CharacterException {
    private final static String MESSAGE = "Effect not found";

    public EffectNotFoundException(String effectDescription){
        super(String.format("%s %s", MESSAGE, effectDescription));
    }
}
