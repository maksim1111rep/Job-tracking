package ru.vk.education.job.cli.schemas;

import ru.vk.education.job.cli.ParsedLine;
import ru.vk.education.job.cli.ValidatedArgs;

public interface CommandArgSchema {
    ValidatedArgs validate(ParsedLine line);
}
