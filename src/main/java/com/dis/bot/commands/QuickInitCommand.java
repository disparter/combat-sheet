package com.dis.bot.commands;

import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class QuickInitCommand implements SlashCommand {

    private final Characters characters;

    public QuickInitCommand(Characters characters){
        this.characters = characters;
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
            .get();

        var memberName = event.getInteraction().getMember().get().getUsername();
        var character = characters.setInitiativeFromMember(memberName, initiative);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative set as %d ", character.getName(), character.getInitiative()));
    }
}
