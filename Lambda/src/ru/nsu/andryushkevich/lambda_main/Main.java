package ru.nsu.andryushkevich.lambda_main;

import ru.nsu.andryushkevich.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Максим", 28),
                new Person("Андрей", 18),
                new Person("Иван", 17),
                new Person("Елена", 43),
                new Person("Иван", 38),
                new Person("Ольга", 13),
                new Person("Артём", 10),
                new Person("Артём", 28)
        );

        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String uniqueNames = uniqueNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNames);

        List<Person> personsUnder18 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());

        personsUnder18.stream()
                .mapToInt(Person::getAge)
                .average()
                .ifPresent(averageAge -> System.out.println("Средний возраст людей до 18 лет: " + averageAge));

        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println("Имя: Средний возраст");
        averageAgeByName.forEach((name, averageAge) -> System.out.printf("%s: %s%n", name, averageAge));

        List<Person> personsFrom20To45 = persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((person1, person2) -> person2.getAge() - person1.getAge())
                .collect(Collectors.toList());

        System.out.println("Имена людей от 20 до 45 лет в порядке убывания возраста: ");
        personsFrom20To45.forEach(person -> System.out.println(person.getName()));
    }
}