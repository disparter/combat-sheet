package com.dis.bot.commands.combat;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CombatService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;

@Component
public class EndCombatCommand implements SlashCommand {

    private final CombatService service;

    public EndCombatCommand(CombatService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "end-combat";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String id = getEventOption(event, "id");

        var combat = service.endCombat(id);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("Combat, %s was ended", combat.getId()));
    }
}
