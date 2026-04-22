package ru.vk.education.job.cli;

import ru.vk.education.job.cli.commands.Command;
import ru.vk.education.job.exceptions.UserNotFoundException;
import ru.vk.education.job.service.FileService;
import ru.vk.education.job.service.MatchingService;
import ru.vk.education.job.service.UserService;
import ru.vk.education.job.service.VacancyService;

import java.util.Scanner;

public class CliApplication {
    private final FileService fileService;
    private final CommandParser commandParser;
    private final Scanner scanner;
    private final LineParser lineParser;

    public CliApplication(UserService userService, VacancyService vacancyService, MatchingService matchingService, FileService fileService) {
        this.fileService = fileService;
        this.commandParser = new CommandParser(userService, vacancyService, matchingService, fileService);
        this.scanner = new Scanner(System.in);
        this.lineParser = new LineParser();
    }

    public void run() {
        CommandRunner commandRunner = new CommandRunner(commandParser);
        commandRunner.runCommands(fileService.commands());
        while (true) {
            String line = scanner.nextLine();
            try {
                ParsedLine parsedLine = lineParser.parse(line);
                Command command = commandParser.parse(parsedLine);
                CommandResult commandResult = command.execute();
                if (commandResult.output() != null) {
                    System.out.println(commandResult.output());
                }
                if (commandResult.shouldExit()) {
                    return;
                }
                fileService.addCommand(line);
            } catch (IllegalArgumentException | UserNotFoundException e) {
                System.err.format("Error while executing a command: %s%n", e.getMessage());
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }
}
