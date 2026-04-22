package ru.vk.education.job.cli;

import ru.vk.education.job.cli.commands.Command;

public class CommandRunner {
    LineParser lineParser;
    CommandParser commandParser;

    CommandRunner(CommandParser commandParser) {
        this.lineParser = new LineParser();
        this.commandParser = commandParser;
    }

    void runCommands(String commands) {
        if (commands == null || commands.isEmpty()) {
            return;
        }
        for (String line : commands.split("\n")) {
            ParsedLine parsedLine = lineParser.parse(line);
            Command command = commandParser.parse(parsedLine);
            if (command.name().equals("user") || command.name().equals("job")) {
                command.execute();
            }
        }
    }
}
