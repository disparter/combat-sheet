package com.dis.bot.tool;

import java.util.Random;

public class D20Roll {

    public static Long rollD20WithBonus(Long bonus){
        return bonus + (long)new Random().nextInt(20);
    }

}
