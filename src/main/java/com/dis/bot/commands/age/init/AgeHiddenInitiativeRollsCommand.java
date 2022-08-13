package com.dis.bot.commands.age.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CombatService;
import com.dis.bot.service.age.AgeCharacterService;
import com.dis.bot.service.age.AgeInitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.ChannelGetter.getChannel;
import static com.dis.bot.tool.D20Roll.rollD20WithBonus;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;

@Component
public class AgeHiddenInitiativeRollsCommand implements SlashCommand {

    private final AgeInitiativeService service;
    private final AgeCharacterService characterService;
    private final CombatService combatService;

    public AgeHiddenInitiativeRollsCommand(AgeInitiativeService service,
                                           AgeCharacterService characterService,
                                           CombatService combatService){
        this.service = service;
        this.characterService = characterService;
        this.combatService = combatService;

    }

    @Override
    public String getName() {
        return "age-hidden-init-rolls";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String initiatives = getEventOption(event, "initiatives");
        String characterNames = getEventOption(event, "names");
        String hps = getEventOption(event, "hps");
        String acs = getEventOption(event, "acs");
        String pds = getEventOption(event, "pds");
        String mds = getEventOption(event, "mds");

        var namesArray = characterNames.split(",");
        var initiativesArray = initiatives.split(",");
        var hpsArray = hps.split(",");
        var acsArray = acs.split(",");
        var pdsArray = pds.split(",");
        var mdsArray = mds.split(",");

        if(namesArray.length != initiativesArray.length
           || acsArray.length != initiativesArray.length
           || pdsArray.length != initiativesArray.length
           || mdsArray.length != initiativesArray.length){
            return  event.reply()
                    .withEphemeral(true)
                    .withContent("Number of arguments for character names, initiatives, acs, pds, mds must be the same");
        }

        for(int i = 0; i < namesArray.length; i++){
            characterService.create(namesArray[i], rollD20WithBonus(Long.parseLong(initiativesArray[i])),
                Long.parseLong(hpsArray[i]),
                Long.parseLong(acsArray[i]),
                Long.parseLong(pdsArray[i]),
                Long.parseLong(mdsArray[i]));
        }

        var combat = combatService.newCombat(namesArray, getChannel(event));

        return  event.reply()
            .withEphemeral(true)
            .withContent(service.showInitiativeTable(combat));
    }
}
