package com.dis.bots.test.functional.steps;

import com.dis.bot.listeners.SlashCommandListener;
import com.dis.bots.test.functional.config.ScenarioContext;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceSteps implements En {

    @Autowired
    public ServiceSteps(SlashCommandListener commandListener, ScenarioContext context) {

        When("I execute a discord command to create a new character", () -> {

        });

    }
}
