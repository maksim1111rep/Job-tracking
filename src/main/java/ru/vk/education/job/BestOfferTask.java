package ru.vk.education.job;

import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;
import ru.vk.education.job.service.UserService;
import ru.vk.education.job.service.VacancyService;

import java.util.List;

public class BestOfferTask implements Runnable {
    private final UserService userService;
    private final VacancyService vacancyService;

    public BestOfferTask(UserService userService, VacancyService vacancyService) {
        this.userService = userService;
        this.vacancyService = vacancyService;
    }

    @Override
    public void run() {
        if (vacancyService.isEmpty()) {
            System.out.println("Пока не было добавлено никаких вакансий");
            return;
        }
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            System.out.println("Пока не было добавлено никаких пользователей");
            return;
        }
        StringBuilder out = new StringBuilder();
        for (User user : users) {
            List<Vacancy> vacancies = vacancyService.bestVacanciesUser(user);
            out.append(user.name()).append(", лучшее предложение — ").append(vacancies.get(0).info()).append("\n");
        }
        System.out.print(out);
    }
}
