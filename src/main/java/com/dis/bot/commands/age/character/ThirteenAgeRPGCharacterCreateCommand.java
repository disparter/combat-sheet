package com.dis.bot.commands.age.character;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.repository.age.AgeCharacters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOptionAsLong;
import static com.dis.bot.tool.MemberNameGetter.getUsername;

@Component
public class ThirteenAgeRPGCharacterCreateCommand implements SlashCommand {

    private final AgeCharacters characters;

    public ThirteenAgeRPGCharacterCreateCommand(AgeCharacters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "age-character-create";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String memberName = getUsername(event);

        String characterName = getEventOption(event, "name");
        Long hp = getEventOptionAsLong(event, "hp");
        Long ac = getEventOptionAsLong(event, "ac");
        Long pd = getEventOptionAsLong(event, "pd");
        Long md = getEventOptionAsLong(event, "md");

        var character = characters.create(memberName, characterName, hp, ac, pd, md);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("13th Age RPG Character, %s was created with %d HP, %d AC, %d PD, %d MD",
                    character.getName(),
                    character.getHealthPoints(),
                    character.getArmorClass(),
                    character.getPhysicalDefense(),
                    character.getMentalDefense()));
    }
}
