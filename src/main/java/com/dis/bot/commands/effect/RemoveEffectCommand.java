package com.dis.bot.commands.effect;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.EffectService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOptionAsLong;

@Component
public class RemoveEffectCommand implements SlashCommand {

    private final EffectService service;

    public RemoveEffectCommand(EffectService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "remove-effect-on";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String characterName = getEventOption(event, "name");
        String effect = getEventOption(event, "effect");

        var character = service.removeEffect(characterName, effect);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s is no more under the effect of [%s]",
                    character.getName(), effect));
    }
}
