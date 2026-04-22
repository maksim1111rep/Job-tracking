package ru.vk.education.job.service;

import ru.vk.education.job.domain.User;

import java.util.List;

public class MatchingService {
    private final UserService userService;
    private final VacancyService vacancyService;

    public MatchingService(UserService userService, VacancyService vacancyService) {
        this.userService = userService;
        this.vacancyService = vacancyService;
    }

    public String usersWithAtLeastMatches(int minMatches) {
        List<String> users = userService.findAll().stream()
                .filter(user -> vacancyService.countMatches(user) >= minMatches)
                .map(User::info)
                .toList();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            out.append(users.get(i));
            if (i != (users.size() - 1)) {
                out.append("\n");
            }
        }
        return out.toString();
    }
}
