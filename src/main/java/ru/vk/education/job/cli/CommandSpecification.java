package ru.vk.education.job.cli;

import ru.vk.education.job.cli.schemas.CommandArgSchema;
import ru.vk.education.job.cli.commands.Command;

import java.util.function.Function;

public record CommandSpecification(
        CommandArgSchema schema,
        Function<ValidatedArgs, Command> factory
) {}
