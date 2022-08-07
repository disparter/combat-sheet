package com.dis.bot.commands.hp;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SetHPCommand implements SlashCommand {

    private final Characters characters;

    public SetHPCommand(Characters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "set-hp";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long hp = event.getOption("hp")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asLong)
            .get();

        String characterName = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();


        var character = characters.setCurrentHP(characterName, hp);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s current HP set as %d ", character.getName(), character.getCurrentHealthPoints()));
    }
}
