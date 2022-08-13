package com.dis.bot.exception;

import com.dis.bot.service.CombatService;

public class InvalidActiveCombatFoundException extends RuntimeException {
    private final static String MESSAGE = "No Active Combat found for this channel";

    public static String getFormattedMessage() {
        return MESSAGE;
    }
}
