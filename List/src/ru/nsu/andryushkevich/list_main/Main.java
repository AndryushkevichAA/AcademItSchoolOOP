package ru.nsu.andryushkevich.list_main;

import ru.nsu.andryushkevich.list.SingleLinkedList;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        System.out.println("Список: " + list);
        System.out.println("Размер списка: " + list.getSize());
        System.out.println("Значение первого элемента: " + list.getFirst());

        int index1 = 2;
        System.out.println("Значение элемента по индексу " + index1 + ": " + list.get(index1));

        int index2 = 0;
        System.out.println("Изменение значения элемента по индексу " + index2 + ". Старое значение: " + list.set(index2, 7)
                + ". Новое значение: " + list.get(index2));

        int index3 = 1;
        System.out.println("Удаление элемента со значением " + list.removeByIndex(index3) + " по индексу " + index3);
        System.out.println("Список после удаления: " + list);

        int index4 = 2;
        list.addByIndex(index4, 80);
        System.out.println("Список после вставки элемента по индексу " + index4 + ": " + list);

        Integer data = null;
        boolean isRemoved = list.remove(data);

        if (isRemoved) {
            System.out.println("Элемент со значением " + data + " удален. Измененный список: " + list);
        } else {
            System.out.println("Элемент со значением " + data + " не найден.");
        }

        System.out.println("Удаление первого элемента со значением: " + list.removeFirst());
        System.out.println("Список после удаления: " + list);

        list.reverse();
        System.out.println("Развернутой список: " + list);

        SingleLinkedList<Integer> copiedList = list.getCopy();
        System.out.println("Копия списка: " + copiedList);
    }
}