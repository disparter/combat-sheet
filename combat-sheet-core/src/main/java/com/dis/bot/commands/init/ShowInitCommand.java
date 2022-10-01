package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CombatService;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.ChannelGetter.getChannel;

@Component
public class ShowInitCommand implements SlashCommand {

    private final InitiativeService service;
    private final CombatService combatService;

    public ShowInitCommand(InitiativeService service, CombatService combatService){
        this.service = service;
        this.combatService = combatService;
    }

    @Override
    public String getName() {
        return "show-init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        var combat = combatService.getCurrentChannelActiveCombat(getChannel(event));
        return  event.reply()
            .withEphemeral(false)
            .withContent(service.showInitiativeTable(combat));
    }
}
