package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

public class JobListSchema implements CommandArgSchema {
    public ValidatedArgs validate(ParsedLine line) {
        ValidatedArgs validatedArgs = new ValidatedArgs();
        if (!line.args().isEmpty() || !line.partialOptions().isEmpty()) {
            throw new IllegalArgumentException("Unexpected arguments");
        }
        return validatedArgs;
    }
}
