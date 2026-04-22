package ru.vk.education.job.cli;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class LineParser {
    public ParsedLine parse(String line) {
        if (line == null) {
            throw new NullPointerException("Parsing null-string");
        }
        HashMap<String, String> args = new HashMap<>();
        String[] parts = line.split(" ", 2);
        String name = parts[0];
        String options = parts.length == 2 ? parts[1] : "";
        options = options.replaceAll("\\s{2,}", " ");
        List<String> partialOptions = new ArrayList<>();
        for (String option : options.split(" ")) {
            String[] optionArgs = option.split("=");
            if (optionArgs.length == 2) {
                args.put(optionArgs[0], optionArgs[1]);
            } else {
                partialOptions.add(optionArgs[0]);
            }
        }
        partialOptions = partialOptions.stream().filter(s -> !s.isBlank()).toList();
        return new ParsedLine(name, args, partialOptions);
    }
}
