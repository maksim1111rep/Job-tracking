package ru.vk.education.job.domain;

import java.util.Arrays;
import java.util.List;

public class User {
    private final String name;
    private final String[] skills;
    private final int experience;

    public User(String name, List<String> skills, int experience) {
        this.name = name;
        this.skills = skills.toArray(new String[0]);
        Arrays.sort(this.skills);
        this.experience = experience;
    }

    public boolean hasName(String name) {
        return name.equals(this.name);
    }

    public boolean hasSkill(String skill) {
        for (String s : skills) {
            if (s.equals(skill)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSuchExp(int experience) {
        return this.experience >= experience;
    }

    public String name() {
        return this.name;
    }

    public String[] skills() {
        return Arrays.copyOf(skills, skills.length);
    }

    public String info() {
        StringBuilder out = new StringBuilder();
        out.append(name).append(" ");
        int i = 0;
        for (String skill : skills) {
            out.append(skill).append((i == skills.length - 1) ? " " : ",");
            i++;
        }
        out.append(experience);
        return out.toString();
    }
}
