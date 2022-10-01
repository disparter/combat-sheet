package com.dis.bot.tool;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;

public class ChannelGetter {
    public static String getChannel(ChatInputInteractionEvent event) {
        return event.getInteraction().getChannelId().asString();
    }
}
