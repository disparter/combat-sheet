package com.dis.bot.commands.hp;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.HealthPointsService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOptionAsLong;

@Component
public class DamageCommand implements SlashCommand {

    private final HealthPointsService service;

    public DamageCommand(HealthPointsService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "damage";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long dmg = getEventOptionAsLong(event, "dmg");
        String characterName = getEventOption(event, "name");

        var character = service.applyDamage(characterName, dmg);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s current HP now is %d ", character.getName(), character.getCurrentHealthPoints()));
    }
}
