package com.dis.bot.commands.hp;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.HealthPointsService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOptionAsLong;

@Component
public class SetHPCommand implements SlashCommand {

    private final HealthPointsService service;

    public SetHPCommand(HealthPointsService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "set-hp";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long hp = getEventOptionAsLong(event, "hp");
        String characterName = getEventOption(event, "name");

        var character = service.setCurrentHP(characterName, hp);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s current HP set as %d ", character.getName(), character.getCurrentHealthPoints()));
    }
}
