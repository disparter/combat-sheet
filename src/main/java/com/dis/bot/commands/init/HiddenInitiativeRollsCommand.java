package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CharacterService;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.D20Roll.rollD20WithBonus;

@Component
public class HiddenInitiativeRollsCommand implements SlashCommand {

    private final InitiativeService service;
    private final CharacterService characterService;

    public HiddenInitiativeRollsCommand(InitiativeService service, CharacterService characterService){
        this.service = service;
        this.characterService = characterService;
    }

    @Override
    public String getName() {
        return "hidden-init-rolls";
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
                    .withContent("Number of arguments for character names and iniatiatives must be the same");
        }

        for(int i = 0; i < namesArray.length; i++){
            characterService.create(namesArray[i], rollD20WithBonus(Long.parseLong(initiativesArray[i])));
        }

        return  event.reply()
            .withEphemeral(true)
            .withContent(service.showInitiativeTable());
    }
}
