package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;
import ru.vk.education.job.service.FileService;

public class HistoryCommand implements Command {
    private final FileService fileService;

    public HistoryCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public String name() {
        return "history";
    }
    @Override
    public CommandResult execute() {
        return new CommandResult(fileService.commands(), false);
    }
}
