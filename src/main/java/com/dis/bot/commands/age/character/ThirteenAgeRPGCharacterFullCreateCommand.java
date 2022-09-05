package com.dis.bot.commands.age.character;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.age.AgeCharacterService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOptionAsLong;
import static com.dis.bot.tool.MemberIdGetter.getMemberId;
import static com.dis.bot.tool.MemberNameGetter.getUsername;

@Component
public class ThirteenAgeRPGCharacterFullCreateCommand implements SlashCommand {

    private final AgeCharacterService service;

    public ThirteenAgeRPGCharacterFullCreateCommand(AgeCharacterService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "age-full-char-create";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String memberId = getMemberId(event);
        String memberName = getUsername(event);

        String characterName = getEventOption(event, "name");
        Long hp = getEventOptionAsLong(event, "hp");
        Long ac = getEventOptionAsLong(event, "ac");
        Long pd = getEventOptionAsLong(event, "pd");
        Long md = getEventOptionAsLong(event, "md");

        var character = service.fullCreate(memberId, memberName, characterName, hp, ac, pd, md);
        service.createInMemory(character);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("13th Age RPG Character, %s was started with %d HP, %d AC, %d PD, %d MD",
                    character.getName(),
                    character.getHp(),
                    character.getArmorClass(),
                    character.getPhysicalDefense(),
                    character.getMentalDefense()));
    }
}
