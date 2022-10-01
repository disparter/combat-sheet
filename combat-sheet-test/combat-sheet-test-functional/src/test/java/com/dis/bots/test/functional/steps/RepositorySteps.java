package com.dis.bots.test.functional.steps;

import com.dis.bots.test.functional.config.ScenarioContext;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class RepositorySteps implements En {

    @Autowired
    public RepositorySteps(ScenarioContext context) {
        Then("the character is created", () -> {
            var expectedName = (String) context.get("characterName");
            var responseName = (String) context.get("responseCharacterName");

            var expectedHp = (Integer) context.get("characterHp");
            var responseHp = (Integer) context.get("responseCharacterHp");

            assertEquals(responseName, expectedName);
            assertEquals(responseHp, expectedHp);
        });

    }
}
