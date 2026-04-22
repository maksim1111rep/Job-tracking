package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;
import ru.vk.education.job.service.VacancyService;

public class JobListCommand implements Command {
    VacancyService vacancyService;

    public JobListCommand(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @Override
    public String name() {
        return "job-list";
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(vacancyService.infoString(), false);
    }
}
