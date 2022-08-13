package com.dis.bot.exception;

import com.dis.bot.service.CombatService;

public class InvalidActiveCombatFoundException extends CombatException {
    private final static String MESSAGE = "No Active Combat found for channel";

    public InvalidActiveCombatFoundException(String channel){
        super(String.format("%s %s", MESSAGE, channel));
    }

}
