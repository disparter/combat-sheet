package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ShowInitCommand implements SlashCommand {

    private final InitiativeService service;

    public ShowInitCommand(InitiativeService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "show-init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return  event.reply()
            .withEphemeral(false)
            .withContent(service.showInitiativeTable());
    }
}
