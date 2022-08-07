package com.dis.bot.commands.hp;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.repository.Characters;
import com.dis.bot.service.HealthPointsService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ShowHpCommand implements SlashCommand {

    private final HealthPointsService service;

    public ShowHpCommand(HealthPointsService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "show-hp";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return  event.reply()
            .withEphemeral(false)
            .withContent(service.showHpTable());
    }
}
