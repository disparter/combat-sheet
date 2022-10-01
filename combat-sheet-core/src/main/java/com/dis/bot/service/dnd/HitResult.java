package com.dis.bot.service.dnd;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HitResult {
    boolean isHit;
    String targetName;
    Long ac;
    Long totalHitValue;
    Long attackBonus;
    Long roll;
}
