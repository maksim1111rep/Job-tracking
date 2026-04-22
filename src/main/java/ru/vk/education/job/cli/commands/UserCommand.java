package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.service.UserService;

import java.util.List;

public class UserCommand implements Command {
    final private UserService userService;
    final private String name;
    final private List<String> skills;
    final private int experience;

    public UserCommand(UserService userService, String name, List<String> skills, int experience) {
        this.userService = userService;
        this.name = name;
        this.skills = skills;
        this.experience = experience;
    }

    @Override
    public String name() {
        return "user";
    }

    @Override
    public CommandResult execute() {
        userService.add(new User(name, skills, experience));
        return new CommandResult(null   , false);
    }
}
