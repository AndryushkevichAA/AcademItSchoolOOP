package ru.nsu.andryushkevich.array_list_main;

import ru.nsu.andryushkevich.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> lines1 = new ArrayList<>(5);
        lines1.add("Строка1");
        lines1.add("Строка2");
        lines1.add("Строка3");
        lines1.add("Строка4");
        lines1.add("Строка5");
        lines1.add("Строка6");

        ArrayList<String> lines2 = new ArrayList<>();
        lines2.add("Строка1");
        lines2.add("Строка2");
        lines2.add("Строка3");

        ArrayList<String> lines3 = new ArrayList<>(3);
        lines3.add("Название1");
        lines3.add("Название2");
        lines3.add("Название3");

        System.out.println("Размер списка: " + lines1.size());

        if (lines1.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Список содержит элементы");
        }

        String searchLine = "Строка5";
        if (lines1.contains(searchLine)) {
            System.out.println("Список содержит элемент " + searchLine);
        } else {
            System.out.println("Элемент " + searchLine + " в списке не найден");
        }

        System.out.print("Проверка итератора: ");
        for (String line : lines1) {
            System.out.print(line + " ");
        }
        System.out.println();

        System.out.println("Получение массива с помощью метода toArray() : " + Arrays.toString(lines1.toArray()));
        System.out.println("Получение массива с помощью метода toArray(T1[] а) : " + Arrays.toString(lines1.toArray(new String[10])));

        String removedLine = "Строка5";
        if (lines1.remove(removedLine)) {
            System.out.println("Элемент " + removedLine + " удален. Список после удаления: " + lines1);
        } else {
            System.out.println("Удаление невозможно. Элемент " + removedLine + "не найден");
        }

        if (lines1.containsAll(lines2)) {
            System.out.println("Список lines1 содержит список lines2");
        } else {
            System.out.println("Список lines1 не содержит список lines2");
        }

        if (lines1.addAll(lines2)) {
            System.out.println("Список lines1 после добавления списка lines2: " + lines1);
        } else {
            System.out.println("Добавление списка невозможно");
        }

        int index1 = 1;
        if (lines2.addAll(index1, lines3)) {
            System.out.println("Список lines2 после добавления списка lines3 по индексу " + index1 + ": " + lines2);
        } else {
            System.out.println("Добавление списка невозможно");
        }

        if (lines2.removeAll(lines3)) {
            System.out.println("Список lines2 после удаления списка lines3: " + lines2);
        } else {
            System.out.println("Удаление списка невозможно");
        }

        if (lines1.retainAll(lines2)) {
            System.out.println("Пересечение списков lines1 и lines2: " + lines2);
        } else {
            System.out.println("Списки не пересекаются");
        }

        lines2.clear();
        System.out.println("Очищенный список lines2: " + lines2);

        int index2 = 2;
        System.out.println("Элемент по индексу " + index2 + ": " + lines1.get(index2));

        int index3 = 3;
        String item1 = "Строка4";
        String oldItem = lines1.set(index3, item1);
        System.out.println("Список lines1 после вставки элемента " + item1 + " по индексу " + index2 + " вместо элемента "
                + oldItem + ": " + lines1);

        int index4 = 4;
        String item2 = "Строка5";
        lines1.add(index4, item2);
        System.out.println("Список lines1 после добавления элемента " + item2 + " по индексу " + index4 + ": " + lines1);

        int index5 = 5;
        String removedItem = lines1.remove(index5);
        System.out.println("Список lines1 после удаления элемента " + removedItem + " по индексу " + index5 + ": " + lines1);

        String item3 = "Строка3";
        int item3FirstIndex = lines1.indexOf(item3);
        if (item3FirstIndex != -1) {
            System.out.println("Индекс первого вхождения элемента " + item3 + ": " + item3FirstIndex);
        } else {
            System.out.println("Элемент " + item3 + " не найден");
        }

        int item3LastIndex = lines1.lastIndexOf(item3);
        if (item3LastIndex != -1) {
            System.out.println("Индекс последнего вхождения элемента " + item3 + ": " + item3LastIndex);
        } else {
            System.out.println("Элемент " + item3 + " не найден");
        }


        System.out.println("Начальная вместимость списка lines1: " + lines1.getCapacity());
        int minCapacity = 15;
        lines1.ensureCapacity(minCapacity);
        System.out.println("Установка минимальной вместимости " + minCapacity + ". Вместимость списка lines1: "
                + lines1.getCapacity());

        lines1.trimToSize();
        System.out.println("Уменьшение вместимости до количества элементов " + lines1.size() + ". Вместимость списка lines1: "
                + lines1.getCapacity());
    }
}