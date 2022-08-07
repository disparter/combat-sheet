package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class IniCommand implements SlashCommand {

    private final Characters characters;

    public IniCommand(Characters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "ini";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long bonus = event.getOption("initiative-bonus")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .get();

        var memberName = event.getInteraction().getMember().get().getUsername();
        var character = characters.rollD20InitiativeFromMemberWithBonus(memberName, bonus);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative total as %d ", character.getName(), character.getInitiative()));
    }
}
