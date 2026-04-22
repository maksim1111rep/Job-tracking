package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.service.VacancyService;

import java.util.List;

public class JobCommand implements Command {
    final private VacancyService vacancyService;
    final private String title, company;
    final private List<String> tags;
    final private int experience;

    public JobCommand(VacancyService vacancyService, String title, String company, List<String> tags, int experience) {
        this.vacancyService = vacancyService;
        this.title = title;
        this.company = company;
        this.tags = tags;
        this.experience = experience;
    }

    @Override
    public String name() {
        return "job";
    }

    @Override
    public CommandResult execute() {
        vacancyService.add(new Vacancy(title, company, tags, experience));
        return new CommandResult(null, false);
    }
}