package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

public class UserListSchema implements CommandArgSchema {
    @Override
    public ValidatedArgs validate(ParsedLine line) {
        if (!line.args().isEmpty() || !line.partialOptions().isEmpty()) {
            throw new IllegalArgumentException("Unexpected arguments");
        }
        return new ValidatedArgs();
    }
}
