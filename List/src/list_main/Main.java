package list_main;

import ru.nsu.andruyshkevich.list.SingleLinkedList;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addFirstItem(1);
        list.addFirstItem(2);
        list.addFirstItem(3);
        list.addFirstItem(4);

        System.out.println("Список: " + list);
        System.out.println("Размер списка: " + list.getSize());
        System.out.println("Значение первого элемента: " + list.getFirstValue());

        int index1 = 2;
        System.out.println("Значение элемента по индексу " + index1 + ": " + list.getValue(index1));

        int index2 = 0;
        System.out.println("Изменение значения элемента по индексу " + index2 + ". Старое значение: " + list.setValue(index2, 7)
                + ". Новое значение: " + list.getValue(index2));

        int index3 = 1;
        System.out.println("Удаление элемента со значением " + list.removeByIndex(index3) + " по индексу " + index3);
        System.out.println("Список после удаления: " + list);

        int index4 = 0;
        list.addByIndex(index4, 80);
        System.out.println("Список после вставки элемента по индексу " + index4 + ": " + list);

        Integer value = 1;
        boolean isRemoved = list.removeNodeByValue(value);

        if (isRemoved) {
            System.out.println("Элемент со значением " + value + " удален. Измененный список: " + list);
        } else {
            System.out.println("Элемент со значением " + value + " не найден.");
        }

        System.out.println("Удаление первого элемента со значением: " + list.removeFirstItem());
        System.out.println("Список после удаления: " + list);

        list.reverse();
        System.out.println("Развернутой список: " + list);

        SingleLinkedList<Integer> copiedList = new SingleLinkedList<>();
        copiedList.copy(list);
        System.out.println("Копия списка: " + copiedList);
    }
}