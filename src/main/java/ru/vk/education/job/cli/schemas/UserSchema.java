package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

import java.util.*;

public class UserSchema implements CommandArgSchema {
    @Override
    public ValidatedArgs validate(ParsedLine line) {
        Set<String> required = Set.of("--skills", "--exp");
        Set<String> allowed = Set.of("--skills", "--exp");
        Map<String, String> args = line.args();
        List<String> partialOptions = line.partialOptions();
        if (partialOptions.size() != 1) {
            throw new IllegalArgumentException("Expected username");
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
        validatedArgs.put("name", partialOptions.get(0));
        List<String> skillsWithDuplicates = Arrays.asList(args.get("--skills").split(","));
        validatedArgs.put("skills", skillsWithDuplicates.stream().distinct().toList());
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
