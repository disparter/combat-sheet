package com.dis.bot.service;

import com.dis.bot.repository.inmemory.Characters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CharacterService {

    private final Characters characters;

    public CharacterService(Characters characters){
        this.characters = characters;
    }

    public void create(String name, Long initiative) {
        var character = characters.create(name, initiative);
        log.info("Character [{}] was created", character.getName());
    }
}
