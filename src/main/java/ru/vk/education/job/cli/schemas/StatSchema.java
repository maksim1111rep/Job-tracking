package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

import java.util.List;

public class StatSchema implements CommandArgSchema {
    @Override
    public ValidatedArgs validate(ParsedLine line) {
        ValidatedArgs validatedArgs = new ValidatedArgs();
        List<String> partialOptions = line.partialOptions();
        if (partialOptions.isEmpty()) {
            throw new IllegalArgumentException("Missed arguments");
        }
        if (!line.args().isEmpty()) {
            throw new IllegalArgumentException("Unexpected arguments");
        }
        String type = partialOptions.get(0);
        switch (type) {
            case "--exp" -> validatedArgs.put("type", "exp");
            case "--match" -> validatedArgs.put("type", "match");
            case "--top-skills" -> validatedArgs.put("type", "top-skills");
            default -> throw new IllegalArgumentException("Unknow argument");
        }
        switch (type) {
            case "--exp", "--match", "--top-skills":
                if (partialOptions.size() == 1) {
                    throw new IllegalArgumentException("Missed argument value");
                } else if (partialOptions.size() > 2) {
                    throw new IllegalArgumentException("Wrong argument value format");
                }
                int value;
                try {
                    value = Integer.parseInt(partialOptions.get(1));
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid value (expected number)");
                }
                validatedArgs.put("value", value);
                break;
            default:
                throw new IllegalArgumentException("Unknow argument");
        }
        return validatedArgs;
    }
}
