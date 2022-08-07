package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class InitiativeCommand implements SlashCommand {

    private final Characters characters;

    public InitiativeCommand(Characters characters){
        this.characters = characters;
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
            .get();

        String characterName = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();


        var character = characters.setInitiative(characterName, initiative);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative set as %d ", character.getName(), character.getInitiative()));
    }
}
