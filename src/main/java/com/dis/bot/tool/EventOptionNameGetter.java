package com.dis.bot.tool;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;

public class EventOptionNameGetter {
    public static String getEventOption(ChatInputInteractionEvent event, String optionName) {
        return event.getOption(optionName)
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElseThrow();
    }

    public static Long getEventOptionAsLong(ChatInputInteractionEvent event, String optionName) {
        return event.getOption(optionName)
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .orElseThrow();
    }
}