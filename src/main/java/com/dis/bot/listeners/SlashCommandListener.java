package com.dis.bot.listeners;

import com.dis.bot.commands.SlashCommand;
import com.dis.bot.exception.CombatSheetGenericException;
import com.dis.bot.exception.InvalidActiveCombatFoundException;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.channel.GuildChannel;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Component
public class SlashCommandListener {

    private final Collection<SlashCommand> commands;
    private final GatewayDiscordClient client;

    public SlashCommandListener(List<SlashCommand> slashCommands, GatewayDiscordClient client) {
        commands = slashCommands;
        this.client = client;
        client.on(ChatInputInteractionEvent.class, this::handle).subscribe();
    }

    public Mono<Void> handle(ChatInputInteractionEvent event) {
        //Convert our list to a flux that we can iterate through
        return Flux.fromIterable(commands)
                //Filter out all commands that don't match the name this event is for
                .filter(command -> command.getName().equals(event.getCommandName()))
                //Get the first (and only) item in the flux that matches our filter
                .next()
                //Have our command class handle all logic related to its specific command.
                .flatMap(command -> {
                    try {
                        return command.handle(event);
                    }catch (InvalidActiveCombatFoundException e){
                        return event.reply()
                                .withEphemeral(false)
                                .withContent(InvalidActiveCombatFoundException.getFormattedMessage());
                    }catch (CombatSheetGenericException e){
                        return event.reply()
                                .withEphemeral(false)
                                .withContent(e.getLocalizedMessage());
                    }
                });
    }
}
