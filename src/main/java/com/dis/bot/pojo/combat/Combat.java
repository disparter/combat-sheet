package com.dis.bot.pojo.combat;

import com.dis.bot.pojo.character.RPGCharacter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
public class Combat {
    UUID id;
    Collection<RPGCharacter> characters;
    int round;
    boolean active;
    String channel;
    LocalDateTime start;
    LocalDateTime end;
}
