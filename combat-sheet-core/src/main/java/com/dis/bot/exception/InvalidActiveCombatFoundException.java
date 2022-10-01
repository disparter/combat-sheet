package com.dis.bot.exception;

public class InvalidActiveCombatFoundException extends RuntimeException {
    private final static String MESSAGE = "No Active Combat found for this channel";

    public static String getFormattedMessage() {
        return MESSAGE;
    }
}
