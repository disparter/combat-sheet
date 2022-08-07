package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class InitiativeCommand implements SlashCommand {

    private final InitiativeService service;

    public InitiativeCommand(InitiativeService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "initiative";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long initiative = event.getOption("initiative")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asLong)
            .orElseThrow();

        String characterName = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElseThrow();


        var character = service.setInitiative(characterName, initiative);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative set as %d ", character.getName(), character.getInitiative()));
    }
}
