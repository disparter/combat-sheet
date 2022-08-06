package com.dis.bot.commands;

import com.dis.bot.repository.Characters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CharacterCreateCommand implements SlashCommand {

    private final Characters characters;

    public CharacterCreateCommand(Characters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "character-create";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String memberName = event.getInteraction().getMember().get().getUsername();

        String characterName = event.getOption("name")
            .flatMap(ApplicationCommandInteractionOption::getValue)
            .map(ApplicationCommandInteractionOptionValue::asString)
            .get();

        Long hp = event.getOption("hp")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .get();

        var character = characters.getOrCreate(characterName, hp, memberName);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("Character, %s was created with %d HP", character.getName(), character.getHealthPoints()));
    }
}
