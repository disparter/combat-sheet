package com.dis.bot.commands.effect;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.pojo.combat.Combat;
import com.dis.bot.service.CombatService;
import com.dis.bot.service.EffectService;
import com.dis.bot.service.HealthPointsService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.ChannelGetter.getChannel;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOption;
import static com.dis.bot.tool.EventOptionNameGetter.getEventOptionAsLong;

@Component
public class SimpleEffectCommand implements SlashCommand {

    private final EffectService service;
    private final CombatService combatService;

    public SimpleEffectCommand(EffectService service, CombatService combatService){
        this.service = service;
        this.combatService = combatService;
    }

    @Override
    public String getName() {
        return "effect-on";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        Long duration = getEventOptionAsLong(event, "duration");
        String characterName = getEventOption(event, "name");
        String effect = getEventOption(event, "effect");
        var combat = combatService.getCurrentChannelActiveCombat(getChannel(event));


        var character = service.applyEffect(characterName, effect, duration, combat.getRound());

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s is now under the effect of [%s] for %d rounds ",
                    character.getName(), effect, duration));
    }
}
