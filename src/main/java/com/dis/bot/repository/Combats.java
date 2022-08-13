package com.dis.bot.repository;

import com.dis.bot.exception.CombatNotFoundException;
import com.dis.bot.pojo.combat.Combat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class Combats {

    final List<Combat> combats;


    public Combats() {
        this.combats = new ArrayList<>();
    }

    public Combat create(Combat combat){
        combats.add(combat);
        return combat;
    }

    public Combat get(String id){
        return combats.stream()
                .filter(combat -> combat.getId().equals(UUID.fromString(id)))
                .findFirst()
                .orElseThrow(() -> new CombatNotFoundException(id));
    }
}
