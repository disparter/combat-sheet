package com.dis.bot.pojo.combat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Effect {
    String description;
    long duration;
    boolean active;
    long startRound;

    public void moveRound(long round){
        if(round - startRound == duration){
            this.active = false;
        }
    }
}
