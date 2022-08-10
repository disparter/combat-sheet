package com.dis.bot.commands.dnd.character;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.repository.dnd.DndCharacters;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOptionAsLong;
import static com.dis.bot.tool.MemberNameGetter.getUsername;

@Component
public class DNDCharacterCreateCommand implements SlashCommand {

    private final DndCharacters characters;

    public DNDCharacterCreateCommand(DndCharacters characters){
        this.characters = characters;
    }

    @Override
    public String getName() {
        return "dnd-character-create";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String memberName = getUsername(event);

        String characterName = getEventOption(event, "name");
        Long hp = getEventOptionAsLong(event, "hp");
        Long ac = getEventOptionAsLong(event, "ac");

        var character = characters.create(memberName, characterName, hp, ac);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("DND Character, %s was created with %d HP and %d AC",
                    character.getName(),
                    character.getHealthPoints(),
                    character.getArmorClass()));
    }
}
