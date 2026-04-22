package ru.vk.education.job.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatedArgs {
    private final Map<String, Object> values = new HashMap<>();

    public void put(String key, Object value) {
        values.put(key, value);
    }

    public String getString(String key) {
        return (String)values.get(key);
    }

    public Integer getInteger(String key) {
        return (Integer)values.get(key);
    }

    @SuppressWarnings("unchecked")
    public List<String> getListString(String key) {
        return (List<String>) values.get(key);
    }
}
