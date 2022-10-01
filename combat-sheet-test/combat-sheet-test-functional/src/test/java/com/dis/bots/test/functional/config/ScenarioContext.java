package com.dis.bots.test.functional.config;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, Object> context = new HashMap<>();

    public void put(String key, Object value) {
        context.put(key, value);
    }

    public <T> T get(String key) {
        return (T) context.get(key);
    }

}
