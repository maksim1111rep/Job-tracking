package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

import java.util.List;
import java.util.Map;

public class SuggestSchema implements CommandArgSchema {
    @Override
    public ValidatedArgs validate(ParsedLine line) {
        ValidatedArgs validatedArgs = new ValidatedArgs();
        Map<String, String> args = line.args();
        if (!args.isEmpty()) {
            throw new IllegalArgumentException("Unexpected arguments");
        }
        List<String> partialOptions = line.partialOptions();
        if (partialOptions.size() != 1) {
            throw new IllegalArgumentException("Expected only username");
        }
        validatedArgs.put("username", partialOptions.get(0));
        return validatedArgs;
    }
}
