package com.dis.bots.test.functional.steps;

import com.dis.bots.test.functional.config.ScenarioContext;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

public class CommandSteps implements En {

    @Autowired
    public CommandSteps(ScenarioContext context) {

        Given("I have prepared a discord command to create a new character {string} with hp {int}",
                (String characterName, Integer hp) -> {

                context.put("characterName", characterName);
                context.put("hp", hp);

        });

    }
}
