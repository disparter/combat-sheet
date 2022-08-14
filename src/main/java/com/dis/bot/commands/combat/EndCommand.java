package com.dis.bot.commands.combat;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CombatService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.ChannelGetter.getChannel;

@Component
public class EndCommand implements SlashCommand {

    private final CombatService service;

    public EndCommand(CombatService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "end";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        var combat = service.nextRoundForChannel(getChannel(event));
        service.endCombat(combat);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("Combat, %s was ended", combat.getId()));
    }
}
