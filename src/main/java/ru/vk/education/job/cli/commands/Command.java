package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;

public interface Command {
    String name();
    CommandResult execute();
}
