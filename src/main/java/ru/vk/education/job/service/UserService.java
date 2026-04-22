package ru.vk.education.job.service;

import ru.vk.education.job.domain.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserService{
    private final List<User> listUsers;

    public UserService() {
        listUsers = new ArrayList<>();
    }

    public synchronized boolean containsUser(String name) {
        for (User user : listUsers) {
            if (user.hasName(name)) {
                return true;
            }
        }
        return false;
    }

    public synchronized User find(String name) {
        for (User user : listUsers) {
            if (user.hasName(name)) {
                return user;
            }
        }
        return null;
    }

    public synchronized void add(User user) {
        if (!containsUser(user.name())) {
            listUsers.add(user);
        }
    }

    public synchronized List<User> findAll() {
        return List.copyOf(listUsers);
    }

    public synchronized List<String> topSkillsList() {
        Map<String, Long> cntSkills = listUsers.stream()
                .flatMap(user -> Arrays.stream(user.skills()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Map.Entry<String, Long>> cntSkillsList = new ArrayList<>(cntSkills.entrySet());
        cntSkillsList.sort((a, b) -> {
            int param = Long.compare(b.getValue(), a.getValue());
            if (param != 0) {
                return param;
            }
            return a.getKey().compareTo(b.getKey());
        });
        return cntSkillsList.stream()
                .map(x -> x.getKey())
                .toList();
    }

    public synchronized String infoString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < listUsers.size(); i++) {
            out.append(listUsers.get(i).info());
            if (i != listUsers.size() - 1) {
                out.append("\n");
            }
        }
        return out.toString();
    }
}
