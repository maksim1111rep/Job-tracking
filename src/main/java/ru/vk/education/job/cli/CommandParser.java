package ru.vk.education.job.cli;

import ru.vk.education.job.cli.schemas.*;
import ru.vk.education.job.cli.commands.*;
import ru.vk.education.job.service.FileService;
import ru.vk.education.job.service.MatchingService;
import ru.vk.education.job.service.UserService;
import ru.vk.education.job.service.VacancyService;

import java.util.*;
import java.util.function.Function;

public class CommandParser {
    private final Map<String, CommandSpecification> listCommandSpecifications;

    public CommandParser(UserService userService, VacancyService vacancyService, MatchingService matchingService, FileService fileService) {
        this.listCommandSpecifications = Map.of(
                "user", new CommandSpecification(
                        new UserSchema(), args -> new UserCommand(userService,
                        args.getString("name"), args.getListString("skills"),
                        args.getInteger("experience"))
                ),
                "user-list", new CommandSpecification(
                        new UserListSchema(), args -> new UserListCommand(userService)
                ),
                "job", new CommandSpecification(
                        new JobSchema(), args -> new JobCommand(vacancyService,
                        args.getString("title"),
                        args.getString("company"),
                        args.getListString("tags"),
                        args.getInteger("experience"))
                ),
                "job-list", new CommandSpecification(
                        new JobListSchema(), args -> new JobListCommand(vacancyService)
                ),
                "suggest", new CommandSpecification(
                        new SuggestSchema(), args -> new SuggestCommand(userService, vacancyService,
                        args.getString("username"))
                ),
                "exit", new CommandSpecification(
                        new ExitSchema(), args -> new ExitCommand()
                ),
                "history", new CommandSpecification(
                        new HistorySchema(), args -> new HistoryCommand(fileService)
                ),
                "stat", new CommandSpecification(
                        new StatSchema(), args -> new StatCommand(userService, vacancyService, matchingService, args)
                )
        );
    }

    public Command parse(ParsedLine line) {
        CommandSpecification commandSpecification = listCommandSpecifications.get(line.name());
        if (commandSpecification == null) {
            throw new IllegalArgumentException("Unknown command");
        }
        ValidatedArgs validatedArgs = commandSpecification.schema().validate(line);
        Function<ValidatedArgs, Command> function = commandSpecification.factory();
        return function.apply(validatedArgs);
    }
}
