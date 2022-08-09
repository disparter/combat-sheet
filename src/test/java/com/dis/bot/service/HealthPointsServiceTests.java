package com.dis.bot.service;

import com.dis.bot.repository.Characters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthPointsServiceTests {

    public HealthPointsService service;
    public Characters characters;

    @BeforeEach
    public void setup(){
        characters = new Characters();
        service = new HealthPointsService(characters);
    }

    @Test
    public void test_setCurrentHP(){
        //Given
        String characterName = "personagem teste";
        Long hp = 100L;
        Long newHP = 30L;
        Long expected = 30L;
        characters.getOrCreate(characterName, hp, "membro teste");

        //When
        var result = service.setCurrentHP(characterName, newHP);

        //Then
        Assertions.assertEquals(expected, result.getCurrentHealthPoints());
    }

    @Test
    public void test_applyDamage(){
        //TODO Blue Mage to handle
    }

    @Test
    public void test_showHpTable(){
        //TODO Blue Mage to handle
    }

}
