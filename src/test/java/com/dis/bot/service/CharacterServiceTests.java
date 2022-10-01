package com.dis.bot.service;

import com.dis.bot.repository.Characters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterServiceTests {

    public CharacterService service;

    @BeforeEach
    public void setup(){
        service = new CharacterService(new Characters());
    }

    @Test
    public void shouldHandleCreateTest(){
        String characterName = "personagem teste";
        Long initiative = 0L;
        Assertions.assertDoesNotThrow(() -> service.create(characterName, initiative));
    }


}
