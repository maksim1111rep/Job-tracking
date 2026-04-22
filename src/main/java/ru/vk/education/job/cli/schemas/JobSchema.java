package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

import java.util.*;

public class JobSchema implements CommandArgSchema {
    @Override
    public ValidatedArgs validate(ParsedLine line) {
        Set<String> required = Set.of("--company", "--tags", "--exp");
        Set<String> allowed = Set.of("--company", "--tags", "--exp");
        Map<String, String> args = line.args();
        List<String> partialOptions = line.partialOptions();
        if (partialOptions.size() != 1) {
            throw new IllegalArgumentException("Expected title");
        }
        if (!args.keySet().containsAll(required)) {
            throw new IllegalArgumentException("Missing required arguments");
        }
        Set<String> unexpected = new HashSet<>(args.keySet());
        unexpected.removeAll(allowed);
        if (!unexpected.isEmpty()) {
            throw new IllegalArgumentException("Unexpected arguments");
        }

        ValidatedArgs validatedArgs = new ValidatedArgs();
        validatedArgs.put("title", partialOptions.get(0));
        validatedArgs.put("company", args.get("--company"));
        validatedArgs.put("tags", Arrays.asList(args.get("--tags").split(",")));
        try {
            int experience = Integer.parseInt(args.get("--exp"));
            if (experience < 0) {
                throw new IllegalArgumentException("Illegal --exp value (experience cannot be less than 0)");
            }
            validatedArgs.put("experience", experience);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Illegal --exp value (expected integer number)");
        }
        return validatedArgs;
    }
}
