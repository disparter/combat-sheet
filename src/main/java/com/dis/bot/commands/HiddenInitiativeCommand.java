package com.dis.bot.commands;

import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class HiddenInitiativeCommand implements SlashCommand {

    private final Characters characters;

    public HiddenInitiativeCommand(Characters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "hidden-init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String initiatives = event.getOption("initiatives")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        String characterNames = event.getOption("names")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        var namesArray = characterNames.split(",");
        var initiativesArray = initiatives.split(",");

        if(namesArray.length != initiativesArray.length){
            return  event.reply()
                    .withEphemeral(true)
                    .withContent("Number of arguments for character names and iniatiatives must be the same");
        }

        for(int i = 0; i < namesArray.length; i++){
            characters.create(namesArray[i], Long.parseLong(initiativesArray[i]));
        }

        return  event.reply()
            .withEphemeral(true)
            .withContent(characters.showInitiativeTable());
    }
}
