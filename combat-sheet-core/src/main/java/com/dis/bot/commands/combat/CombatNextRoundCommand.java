package com.dis.bot.commands.combat;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CombatService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;

@Component
public class CombatNextRoundCommand implements SlashCommand {

    private final CombatService service;

    public CombatNextRoundCommand(CombatService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "combat-next-round";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String id = getEventOption(event, "id");

        var combat = service.nextRound(id);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("Combat, round is %d", combat.getRound()));
    }
}
