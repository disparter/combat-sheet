package com.dis.bot.commands;

import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class D20InitCommand implements SlashCommand {

    private final Characters characters;

    public D20InitCommand(Characters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "roll-init";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        var memberName = event.getInteraction().getMember().get().getUsername();
        var character = characters.rollD20InitiativeFromMember(memberName);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative rolled as %d ", character.getName(), character.getInitiative()));
    }
}
