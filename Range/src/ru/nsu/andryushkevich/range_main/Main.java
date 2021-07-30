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

        Range range1 = new Range(10, 50);
        Range range2 = new Range(30, 100);

        Range intersectionRange = range1.getIntersection(range2);
        Range[] unionRange = range1.getUnion(range2);
        Range[] differenceRange = range1.getDifference(range2);

        if (intersectionRange == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазон пересечения: " + intersectionRange);
        }

        System.out.println("Диапазон объединения: " + Arrays.toString(unionRange));
        System.out.println("Диапазон разности: " + Arrays.toString(differenceRange));
    }
}