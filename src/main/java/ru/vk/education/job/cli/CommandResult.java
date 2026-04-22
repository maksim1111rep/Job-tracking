package ru.vk.education.job.cli;

public class CommandResult {
    private final String output;
    private final boolean exit;

    public CommandResult(String output, boolean exit) {
        this.output = output;
        this.exit = exit;
    }

    String output() {
        return output;
    }

    boolean shouldExit() {
        return exit;
    }
}
