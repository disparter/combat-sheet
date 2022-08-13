package com.dis.bot.service;

import com.dis.bot.pojo.combat.Combat;
import com.dis.bot.repository.Characters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class CombatService {

    private final Characters characters;

    public CombatService(Characters characters){
        this.characters = characters;
    }

    public Combat newCombat(String[] charactersNames, String channel) {
        var combat = Combat.builder()
                .start(LocalDateTime.now())
                .active(true)
                .id(UUID.randomUUID())
                .round(0)
                .characters(characters.get(charactersNames))
                .channel(channel)
                .build();

        log.info("Combat [{}] was created", combat.getId());

        return combat;
    }
}
