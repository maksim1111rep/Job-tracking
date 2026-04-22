package ru.vk.education.job.domain;

public class Match {
    final private User user;
    final private Vacancy vacancy;

    public Match(User user, Vacancy vacancy) {
        this.user = user;
        this.vacancy = vacancy;
    }

    public int rating() {
        int rating = vacancy.countComplicance(user);
        if (user.hasSuchExp(vacancy.experience())) {
            rating *= 2;
        }
        return rating;
    }
}
