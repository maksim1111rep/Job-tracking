package ru.vk.education.job;

import ru.vk.education.job.cli.CliApplication;
import ru.vk.education.job.service.FileService;
import ru.vk.education.job.service.MatchingService;
import ru.vk.education.job.service.UserService;
import ru.vk.education.job.service.VacancyService;

import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserService userService = new UserService();
        VacancyService vacancyService = new VacancyService();
        MatchingService matchingService = new MatchingService(userService, vacancyService);
        Path path = Paths.get("history.txt");
        FileService fileService = new FileService(path);
        CliApplication cliApplication = new CliApplication(userService, vacancyService, matchingService, fileService);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(
                new BestOfferTask(userService, vacancyService),
                10,
                60,
                TimeUnit.SECONDS
        );
        try {
            cliApplication.run();
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}