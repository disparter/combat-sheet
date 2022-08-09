package com.dis.bot.tool;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;

public class MemberNameGetter {
    public static String getUsername(ChatInputInteractionEvent event) {
        return event.getInteraction().getMember().orElseThrow().getUsername();
    }
}