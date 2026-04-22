package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;

public class ExitCommand implements Command {
    @Override
    public String name() {
        return "exit";
    }
    @Override
    public CommandResult execute() {
        return new CommandResult(null, true);
    }
}
