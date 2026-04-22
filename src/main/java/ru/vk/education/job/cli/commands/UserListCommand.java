package ru.vk.education.job.cli.commands;

import ru.vk.education.job.cli.CommandResult;
import ru.vk.education.job.service.UserService;

public class UserListCommand implements Command {
    UserService userService;

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String name() {
        return "user-list";
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(userService.infoString(), false);
    }
}
