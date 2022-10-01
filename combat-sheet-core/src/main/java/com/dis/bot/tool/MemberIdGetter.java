package com.dis.bot.tool;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;

public class MemberIdGetter {
    public static String getMemberId(ChatInputInteractionEvent event) {
        return event.getInteraction().getMember().orElseThrow().getId().asString();
    }
}
