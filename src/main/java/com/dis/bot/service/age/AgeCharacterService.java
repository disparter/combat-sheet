package com.dis.bot.service.age;

import com.dis.bot.repository.inmemory.age.AgeCharacters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AgeCharacterService {

    private final AgeCharacters characters;

    public AgeCharacterService(AgeCharacters characters){
        this.characters = characters;
    }

    public void create(String name, Long initiative, Long hp, Long ac, Long pd, Long md) {
        var character = characters.create(name, initiative, hp, ac, pd, md);
        log.info("13th Age Character [{}] was created", character.getName());
    }
}
