package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class QuickInitCommand implements SlashCommand {

    private final InitiativeService service;

    public QuickInitCommand(InitiativeService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long initiative = event.getOption("initiative")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asLong)
            .orElseThrow();

        var memberName = event.getInteraction().getMember().orElseThrow().getUsername();
        var character = service.setInitiativeFromMember(memberName, initiative);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative set as %d ", character.getName(), character.getInitiative()));
    }
}
