package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CharacterService;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class HiddenInitiativeCommand implements SlashCommand {

    private final InitiativeService service;
    private final CharacterService characterService;

    public HiddenInitiativeCommand(InitiativeService service, CharacterService characterService){
        this.service = service;
        this.characterService = characterService;
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
                .orElseThrow();

        String characterNames = event.getOption("names")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElseThrow();

        var namesArray = characterNames.split(",");
        var initiativesArray = initiatives.split(",");

        if(namesArray.length != initiativesArray.length){
            return  event.reply()
                    .withEphemeral(true)
                    .withContent("Number of arguments for character names and initiatives must be the same");
        }

        for(int i = 0; i < namesArray.length; i++){
            characterService.create(namesArray[i], Long.parseLong(initiativesArray[i]));
        }

        return  event.reply()
            .withEphemeral(true)
            .withContent(service.showInitiativeTable());
    }
}
