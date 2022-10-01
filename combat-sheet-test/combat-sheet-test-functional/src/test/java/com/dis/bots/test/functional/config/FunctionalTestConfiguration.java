package com.dis.bots.test.functional.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com/dis/bot/test/functional"})
@Import(ScenarioStateConfiguration.class)
public class FunctionalTestConfiguration {

    public static void bootstrapFunctionalTests() {
    }

    public static void teardownFunctionalTests() {
    }
}
