package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;
import ru.vk.education.job.cli.ValidatedArgs;
import ru.vk.education.job.service.MatchingService;
import ru.vk.education.job.service.UserService;
import ru.vk.education.job.service.VacancyService;

import java.util.List;

public class StatCommand implements Command {
    private final ValidatedArgs args;
    private final UserService userService;
    private final VacancyService vacancyService;
    private final MatchingService matchingService;

    public StatCommand(UserService userService, VacancyService vacancyService, MatchingService matchingService, ValidatedArgs args) {
        this.userService = userService;
        this.vacancyService = vacancyService;
        this.matchingService = matchingService;
        this.args = args;
    }

    private CommandResult expArg(int requiredExperience) {
        return new CommandResult(vacancyService.getRequiredExp(requiredExperience), false);
    }

    private CommandResult matchArg(int cnt) {
        return new CommandResult(matchingService.usersWithAtLeastMatches(cnt), false);
    }

    private CommandResult topSkillArg(int n) {
        List<String> topSkills = userService.topSkillsList();
        topSkills = topSkills.stream().limit(n).toList();
        topSkills = topSkills.stream().sorted().toList();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < Math.min(topSkills.size(), n); i++) {
            out.append(topSkills.get(i));
            if (i != Math.min(topSkills.size(), n) - 1) {
                out.append("\n");
            }
        }
        return new CommandResult(out.toString(), false);
    }

    @Override
    public String name() {
        return "stat";
    }

    @Override
    public CommandResult execute() {
        return switch (args.getString("type")) {
            case "exp" -> expArg(args.getInteger("value"));
            case "match" -> matchArg(args.getInteger("value"));
            case "top-skills" -> topSkillArg(args.getInteger("value"));
            default -> throw new IllegalArgumentException("Unknown argument");
        };
    }
}
