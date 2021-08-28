package ru.nsu.andryushkevich.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    private void checkListIsEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Элемента не существует. Пустой список");
        }
    }

    public T getFirst() {
        checkListIsEmpty();

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше " + size
                    + " Переданое значение: " + index);
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    public T get(int index) {
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);
        return item.getData();
    }

    public T set(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);
        T oldData = item.getData();

        item.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> item = getItemByIndex(index - 1);
        ListItem<T> removedItem = item.getNext();

        item.setNext(removedItem.getNext());
        size--;

        return removedItem.getData();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void addByIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше или равен " + size
                    + " Переданое значение: " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);

        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        size++;
    }

    public boolean remove(T data) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            removeFirst();

            return true;
        }

        for (ListItem<T> item = head.getNext(), previousItem = head; item != null; previousItem = item, item = item.getNext()) {
            if (Objects.equals(item.getData(), data)) {
                previousItem.setNext(item.getNext());
                size--;

                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        checkListIsEmpty();

        T firstData = head.getData();

        head = head.getNext();
        size--;

        return firstData;
    }

    public void reverse() {
        ListItem<T> item = head;
        ListItem<T> previousItem = null;

        while (item != null) {
            ListItem<T> nextItem = item.getNext();

            item.setNext(previousItem);

            previousItem = item;
            item = nextItem;
        }

        head = previousItem;
    }

    public SingleLinkedList<T> getCopy() {
        SingleLinkedList<T> listCopy = new SingleLinkedList<>();

        if (size == 0) {
            return listCopy;
        }

        listCopy.head = new ListItem<>(head.getData());
        listCopy.size = size;

        for (ListItem<T> item = head.getNext(), listCopyItem = listCopy.head; item != null;
             item = item.getNext(), listCopyItem = listCopyItem.getNext()) {
            listCopyItem.setNext(new ListItem<>(item.getData()));
        }

        return listCopy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            stringBuilder.append(item.getData()).append(", ");
        }

        return stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]").toString();
    }
}