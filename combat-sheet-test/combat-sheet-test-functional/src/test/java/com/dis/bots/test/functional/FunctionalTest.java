package com.dis.bots.test.functional;

import com.dis.bots.test.functional.config.FunctionalTestConfiguration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/dis/bot/test/functional"},
        tags = "not @ignore",
        plugin = {
                "pretty",
                "html:build/reports/functionalTest/index.html",
                "json:build/reports/functionalTest/cucumber.json",
                "usage:build/reports/functionalTest/usage.json",
                "junit:build/reports/functionalTest/junit.xml"
        }
)
@ContextConfiguration(classes = FunctionalTestConfiguration.class)
public class FunctionalTest {

    @BeforeClass
    public static void setupFunctionalTests() {
        FunctionalTestConfiguration.bootstrapFunctionalTests();
    }

    @AfterClass
    public static void tearDownFunctionalTests() {
        FunctionalTestConfiguration.teardownFunctionalTests();
    }

}
