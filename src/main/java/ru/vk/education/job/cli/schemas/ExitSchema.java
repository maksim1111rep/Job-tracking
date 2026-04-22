package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

public class ExitSchema implements CommandArgSchema {
    @Override
    public ValidatedArgs validate(ParsedLine line) {
        if (!line.partialOptions().isEmpty() || !line.args().isEmpty()) {
            throw new IllegalArgumentException("Unexpected arguments");
        }
        return new ValidatedArgs();
    }
}
