package ru.vk.education.job.domain;

import java.util.Arrays;
import java.util.List;

public class Vacancy {
    private final String title, company;
    private final String[] tags;
    private final int experience;

    public Vacancy(String title, String company, List<String> tags, int experience) {
        this.title = title;
        this.company = company;
        this.tags = tags.toArray(new String[0]);
        Arrays.sort(this.tags);
        this.experience = experience;
    }

    public String title() {
        return title;
    }

    public boolean hasTitle(String title) {
        return this.title.equals(title);
    }

    public int countComplicance(User user) {
        int cnt = 0;
        for (String skill : tags) {
            if (user.hasSkill(skill)) {
                cnt++;
            }
        }
        return cnt;
    }

    int experience() {
        return this.experience;
    }

    public boolean hasSuchExperience(int experience) {
        return this.experience >= experience;
    }

    public String info() {
        return title + " at " + company;
    }
}
