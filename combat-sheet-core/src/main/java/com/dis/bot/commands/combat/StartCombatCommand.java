package com.dis.bot.commands.combat;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.CombatService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.ChannelGetter.getChannel;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;

@Component
public class StartCombatCommand implements SlashCommand {

    private final CombatService service;

    public StartCombatCommand(CombatService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "start-combat";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String characterNames = getEventOption(event, "names");
        var namesArray = characterNames.split(",");

        var combat = service.newCombat(namesArray, getChannel(event));

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("Combat, %s was created", combat.getId()));
    }
}
