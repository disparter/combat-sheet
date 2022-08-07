package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class D20InitCommand implements SlashCommand {

    private final InitiativeService service;

    public D20InitCommand(InitiativeService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "roll-init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        var memberName = event.getInteraction().getMember().orElseThrow().getUsername();
        var character = service.rollD20InitiativeFromMember(memberName);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative rolled as %d ", character.getName(), character.getInitiative()));
    }
}
