package ru.nsu.andryushkevich.range_main;

import ru.nsu.andryushkevich.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начало диапазона: ");
        double from = scanner.nextDouble();

        System.out.print("Введите конец диапазона: ");
        double to = scanner.nextDouble();

        System.out.print("Введите число: ");
        double number = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.println("Начало диапазона: " + range.getFrom());
        System.out.println("Конец диапазона: " + range.getTo());
        System.out.println("Длина диапазона: " + range.getLength());

        if (range.isInside(number)) {
            System.out.printf("Число %f принадлежит диапазону%n", number);
        } else {
            System.out.printf("Число %f не принадлежит диапазону%n", number);
        }

        range.setFrom(2);
        range.setTo(10);

        System.out.println("Начало диапазона: " + range.getFrom());
        System.out.println("Конец диапазона: " + range.getTo());
        System.out.println("Длина диапазона: " + range.getLength());

        if (range.isInside(number)) {
            System.out.printf("Число %f принадлежит диапазону%n", number);
        } else {
            System.out.printf("Число %f не принадлежит диапазону%n", number);
        }
    }
}