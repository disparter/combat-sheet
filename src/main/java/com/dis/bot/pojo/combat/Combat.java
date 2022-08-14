package com.dis.bot.pojo.combat;

import com.dis.bot.pojo.character.RPGCharacter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class Combat {
    UUID id;
    Set<RPGCharacter> characters;
    int round;
    boolean active;
    String channel;
    LocalDateTime start;
    LocalDateTime end;

    public void nextRound(){
        setRound(this.getRound() + 1);
    }
    public void addCharacter(RPGCharacter character){
        this.getCharacters().add(character);
    }
}
