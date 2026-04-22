package ru.vk.education.job.cli.commands;

import ru.vk.education.job.exceptions.UserNotFoundException;
import ru.vk.education.job.cli.CommandResult;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.service.UserService;
import ru.vk.education.job.service.VacancyService;

import java.util.List;

public class SuggestCommand implements Command {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final String username;

    public SuggestCommand(UserService userService, VacancyService vacancyService, String username) {
        this.userService = userService;
        this.vacancyService = vacancyService;
        this.username = username;
    }

    @Override
    public String name() {
        return "suggest";
    }

    @Override
    public CommandResult execute() {
        User user = userService.find(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        List<Vacancy> suitableVacancies = vacancyService.bestVacanciesUser(user);
        StringBuilder out = new StringBuilder();
        if (suitableVacancies.size() == 1) {
            out.append(suitableVacancies.get(0).info());
        } else if (suitableVacancies.size() >= 2) {
            out.append(suitableVacancies.get(0).info()).append("\n");
            out.append(suitableVacancies.get(1).info());
        }
        return new CommandResult(out.toString(), false);
    }
}
