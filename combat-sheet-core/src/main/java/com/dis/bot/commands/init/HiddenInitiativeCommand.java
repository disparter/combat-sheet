package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CharacterService;
import com.dis.bot.service.CombatService;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.ChannelGetter.getChannel;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;

@Component
@Slf4j
public class HiddenInitiativeCommand implements SlashCommand {

    private final InitiativeService service;
    private final CharacterService characterService;
    private final CombatService combatService;

    public HiddenInitiativeCommand(InitiativeService service,
                                   CharacterService characterService,
                                   CombatService combatService){
        this.service = service;
        this.characterService = characterService;
        this.combatService = combatService;
    }

    @Override
    public String getName() {
        return "hidden-init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String initiatives = getEventOption(event, "initiatives");
        String characterNames = getEventOption(event, "names");

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

        var combat = combatService.newCombat(namesArray, getChannel(event));

        return  event.reply()
            .withEphemeral(true)
            .withContent(service.showInitiativeTable(combat));
    }

}
