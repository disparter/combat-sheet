package com.dis.bot.service.age;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhysicalHitResult {
    boolean isHit;
    String targetName;
    Long pd;
    Long totalHitValue;
    Long attackBonus;
    Long roll;
}
