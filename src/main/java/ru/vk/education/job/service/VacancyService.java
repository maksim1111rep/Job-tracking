package ru.vk.education.job.service;

import ru.vk.education.job.domain.Match;
import ru.vk.education.job.domain.User;
import ru.vk.education.job.domain.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class VacancyService {
    private final List<Vacancy> listVacancy;

    public VacancyService() {
        listVacancy = new ArrayList<>();
    }

    public synchronized boolean isEmpty() {
        return listVacancy.isEmpty();
    }

    public synchronized boolean containsTitle(String title) {
        for (Vacancy vacancy : listVacancy) {
            if (vacancy.hasTitle(title)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void add(Vacancy vacancy) {
        if (!containsTitle(vacancy.title())) {
            listVacancy.add(vacancy);
        }
    }

    public synchronized List<Vacancy> bestVacanciesUser(User user) {
        int[][] vacancies = new int[listVacancy.size()][2];
        int ind = 0;
        for (Vacancy vacancy : listVacancy) {
            Match match = new Match(user, vacancy);
            int rating = match.rating();
            vacancies[ind][0] = rating;
            vacancies[ind][1] = ind;
            ind++;
        }
        Arrays.sort(vacancies, Comparator.comparingInt((int[] row) -> -row[0])
                .thenComparingInt(row -> row[1]));
        List<Vacancy> bestVacancies = new ArrayList<>();
        for (int[] vacancy : vacancies) {
            bestVacancies.add(listVacancy.get(vacancy[1]));
        }
        return bestVacancies;
    }

    public synchronized int countMatches(User user) {
        int cnt = 0;
        for (Vacancy vacancy : listVacancy) {
            Match match = new Match(user, vacancy);
            cnt += match.rating() > 0 ? 1 : 0;
        }
        return cnt;
    }

    public synchronized String getRequiredExp(int requiredExperience) {
        StringBuilder out = new StringBuilder();
        List<Vacancy> required = listVacancy.stream().
                filter(vacancy -> vacancy.hasSuchExperience(requiredExperience))
                .sorted(Comparator.comparing(Vacancy::title))
                .toList();
        for (int i = 0; i < required.size(); i++) {
            out.append(required.get(i).info());
            if (i != required.size() - 1) {
                out.append("\n");
            }
        }
        return out.toString();
    }

    public synchronized String infoString() {
        List<Vacancy> sortedVacancies = listVacancy.stream().sorted(Comparator.comparing(Vacancy::title)).toList();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < sortedVacancies.size(); i++) {
            out.append(sortedVacancies.get(i).info());
            if (i != sortedVacancies.size() - 1) {
                out.append("\n");
            }
        }
        return out.toString();
    }
}
