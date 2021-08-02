package ru.nsu.andryushkevich.range_main;

import ru.nsu.andryushkevich.range.Range;

import java.util.Arrays;
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

        Range range1 = new Range(from, to);

        System.out.println("Начало диапазона: " + range1.getFrom());
        System.out.println("Конец диапазона: " + range1.getTo());
        System.out.println("Длина диапазона: " + range1.getLength());

        if (range1.isInside(number)) {
            System.out.printf("Число %f принадлежит диапазону%n", number);
        } else {
            System.out.printf("Число %f не принадлежит диапазону%n", number);
        }

        range1.setFrom(2);
        range1.setTo(10);

        System.out.println("Начало диапазона: " + range1.getFrom());
        System.out.println("Конец диапазона: " + range1.getTo());
        System.out.println("Длина диапазона: " + range1.getLength());

        if (range1.isInside(number)) {
            System.out.printf("Число %f принадлежит диапазону%n", number);
        } else {
            System.out.printf("Число %f не принадлежит диапазону%n", number);
        }

        Range range2 = new Range(10, 50);
        Range range3 = new Range(50, 120);

        Range intersectionRange = range2.getIntersection(range3);
        Range[] union = range2.getUnion(range3);
        Range[] difference = range2.getDifference(range3);

        if (intersectionRange == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазон пересечения: " + intersectionRange);
        }

        System.out.println("Объединение диапазонов: " + Arrays.toString(union));
        System.out.println("Разность диапазонов: " + Arrays.toString(difference));
    }
}