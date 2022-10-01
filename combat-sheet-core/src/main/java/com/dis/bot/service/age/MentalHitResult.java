package com.dis.bot.service.age;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentalHitResult {
    boolean isHit;
    String targetName;
    Long md;
    Long totalHitValue;
    Long attackBonus;
    Long roll;
}
