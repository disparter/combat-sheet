package com.dis.bot.commands;

import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ShowHpCommand implements SlashCommand {

    private final Characters characters;

    public ShowHpCommand(Characters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "show-hp";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return  event.reply()
            .withEphemeral(false)
            .withContent(characters.showHpTable());
    }
}
