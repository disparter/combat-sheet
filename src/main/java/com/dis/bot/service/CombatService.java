package com.dis.bot.service;

import com.dis.bot.pojo.combat.Combat;
import com.dis.bot.repository.Characters;
import com.dis.bot.repository.Combats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class CombatService {

    private final Characters characters;
    private final Combats combats;

    public CombatService(Characters characters, Combats combats){
        this.characters = characters;
        this.combats = combats;
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

        combats.create(combat);
        log.info("Combat [{}] was created", combat.getId());

        return combat;
    }

    public Combat endCombat(String id) {
        var combat = combats.get(id);
        return endCombat(combat);
    }

    public Combat endCombat(Combat combat){
        combat.setEnd(LocalDateTime.now());
        combat.setActive(false);
        log.info("Combat [{}] was ended", combat.getId());
        return combat;
    }

    public Combat nextRound(String id) {
        var combat = combats.get(id);
        combat.nextRound();
        log.info("Combat [{}] has moved to next round [{}]", combat.getId(), combat.getRound());
        return combat;
    }

    public Combat nextRoundForChannel(String channel) {
        var combat = combats.getCurrentCombat(channel);
        combat.nextRound();
        log.info("Combat [{}] has moved to next round [{}]", combat.getId(), combat.getRound());
        return combat;

    }

    public Combat getCurrentChannelActiveCombat(String channel) {
        return combats.getCurrentCombat(channel);
    }
}
