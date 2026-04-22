package ru.vk.education.job.service;

import ru.vk.education.job.cli.*;

import java.io.*;
import java.nio.file.*;

public class FileService {
    private final Path path;

    public FileService(Path path) {
        this.path = path;
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException x) {
                System.err.format("Error while creating a file: %s%n", x);
                throw new UncheckedIOException(x);
            }
        }
    }

    public void addCommand(String command) {
        try {
            FileWriter writer = new FileWriter(path.toFile(), true);
            writer.write(command + "\n");
            writer.close();
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such file%n", path);
        } catch (IOException x) {
            System.err.format("Error while adding command to file: %s%n", x);
        }
    }

    public String commands() {
        StringBuilder out = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
            String line;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                if (!first) {
                    out.append("\n");
                }
                out.append(line);
                first = false;
            }

            return out.toString();
        } catch (IOException x) {
            System.err.format("Error while reading a file: %s%n", x);
        }
        return out.toString();
    }
}
