package ru.nsu.andryushkevich.array_list_home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> getFileLinesList(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            ArrayList<String> fileLines = new ArrayList<>();

            while (scanner.hasNext()) {
                fileLines.add(scanner.nextLine());
            }

            return fileLines;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);

                i--;
            }
        }
    }

    public static ArrayList<Integer> getNotRepeatingNumbersList(ArrayList<Integer> numbers) {
        ArrayList<Integer> notRepeatingNumbers = new ArrayList<>(numbers);

        for (int i = 0; i < notRepeatingNumbers.size(); i++) {
            for (int j = i + 1; j < notRepeatingNumbers.size(); j++) {
                if (notRepeatingNumbers.get(i).equals(notRepeatingNumbers.get(j))) {
                    notRepeatingNumbers.remove(j);

                    j--;
                }
            }
        }

        return notRepeatingNumbers;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("ArrayListHome/src/ru/nsu/andryushkevich/array_list_home/input.txt");

        System.out.println("Список строк файла: " + getFileLinesList(file));

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(2, 3, 3, 4, 5, 6, 5, 7));

        removeEvenNumbers(numbers1);
        System.out.println("Список нечетных чисел: " + numbers1);

        ArrayList<Integer> notRepeatingNumbers = getNotRepeatingNumbersList(numbers2);
        System.out.println("Список с неповторяющимися числами" + notRepeatingNumbers);
    }
}