package com.dis.bot.commands.dnd.ac;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.dnd.ArmorClassService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AttackCommand implements SlashCommand {

    private final ArmorClassService service;

    public AttackCommand(ArmorClassService service){
        this.service = service;
    }

    @Override
    public String getName() {
        return "attack";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String foeName = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElseThrow();

        Long attackBonus = event.getOption("bonus")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .orElseThrow();

        var result = service.hit(foeName, attackBonus);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s was hit %s. (Attack total score %d, [roll %d, bonus %d])",
                    result.getTargetName(),
                    result.isHit() ? "successfully" : "unsuccessfully",
                    result.getTotalHitValue(),
                    result.getRoll(),
                    result.getAttackBonus()));
    }
}
