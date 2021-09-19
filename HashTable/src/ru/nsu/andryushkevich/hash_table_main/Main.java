package ru.nsu.andryushkevich.hash_table_main;

import ru.nsu.andryushkevich.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, -9, 100, 8));
        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(0, 15, 100, 23));

        HashTable<Integer> hashTable = new HashTable<>(7);
        hashTable.add(25);
        hashTable.add(null);
        hashTable.addAll(numbers1);
        hashTable.addAll(numbers2);

        System.out.println(hashTable);
        System.out.println("Количество элементов хэш-таблицы: " + hashTable.size());

        if (hashTable.isEmpty()) {
            System.out.println("Хэш-таблица пуста");
        } else {
            System.out.println("Хэш-таблица содержит элементы");
        }

        Integer searchNumber = 15;
        if (hashTable.contains(searchNumber)) {
            System.out.println("Хэш-таблица содержит элемент " + searchNumber);
        } else {
            System.out.println("Элемент " + searchNumber + " в хэш-таблице не найден");
        }

        System.out.print("Проверка итератора: ");
        for (Integer number : hashTable) {
            System.out.print(number + " ");
        }
        System.out.println();

        System.out.println("Получение массива с помощью метода toArray() : " + Arrays.toString(hashTable.toArray()));
        System.out.println("Получение массива с помощью метода toArray(T1[] а) : " + Arrays.toString(hashTable.toArray(new Integer[5])));

        Integer removedNumber = 1;
        if (hashTable.remove(removedNumber)) {
            System.out.println("Хэш-таблица после удаления элемента " + removedNumber + ":");
            System.out.println(hashTable);
        } else {
            System.out.println("Удаление невозможно. Элемент " + removedNumber + " не найден");
        }

        if (hashTable.containsAll(numbers2)) {
            System.out.println("Хэш-таблица содержит список numbers2");
        } else {
            System.out.println("Хэш-таблица не содержит список numbers2");
        }

        ArrayList<Integer> numbers3 = new ArrayList<>(Arrays.asList(null, 100, 67));

        if (hashTable.removeAll(numbers3)) {
            System.out.println("Хэш-таблица после удаления списка numbers3:");
            System.out.println(hashTable);
        } else {
            System.out.println("Удаление списка невозможно");
        }

        ArrayList<Integer> numbers4 = new ArrayList<>(Arrays.asList(12, 15, 90, 8, 0));

        if (hashTable.retainAll(numbers4)) {
            System.out.println("Пересечение хэш-таблицы со списком numbers4:");
            System.out.println(hashTable);
        } else {
            System.out.println("Пересечение хэш-таблицы со списком numbers4 не обнаружено");
        }

        hashTable.clear();
        System.out.println("Хэш-таблица после очищения:");
        System.out.println(hashTable);
    }
}