package com.dis.bot.commands.age.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.age.AgeInitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AgeShowInitCommand implements SlashCommand {

    private final AgeInitiativeService service;

    public AgeShowInitCommand(AgeInitiativeService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "age-show-init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return  event.reply()
            .withEphemeral(false)
            .withContent(service.showInitiativeTable());
    }
}
