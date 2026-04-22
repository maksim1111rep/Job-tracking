package ru.vk.education.job.cli;

import java.util.List;
import java.util.Map;

public record ParsedLine(String name, Map<String, String> args, List<String> partialOptions) {

    @Override
    public Map<String, String> args() {
        return Map.copyOf(args);
    }

    @Override
    public List<String> partialOptions() {
        return List.copyOf(partialOptions);
    }
}
