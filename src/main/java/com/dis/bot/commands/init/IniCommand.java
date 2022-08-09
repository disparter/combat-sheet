package com.dis.bot.commands.init;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.service.InitiativeService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.dis.bot.tool.MemberNameGetter.getUsername;

@Component
public class IniCommand implements SlashCommand {

    private final InitiativeService service;

    public IniCommand(InitiativeService service){
        this.service = service;
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
                .orElseThrow();

        var memberName = getUsername(event);
        var character = service.rollD20InitiativeFromMemberWithBonus(memberName, bonus);

        return  event.reply()
            .withEphemeral(false)
            .withContent(String.format("%s initiative total as %d ", character.getName(), character.getInitiative()));
    }
}
