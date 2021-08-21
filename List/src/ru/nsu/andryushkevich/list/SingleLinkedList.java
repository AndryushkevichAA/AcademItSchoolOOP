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
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера списка " + size);
        }
    }

    private ListItem<T> getNodeByIndex(int index) {
        ListItem<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public T get(int index) {
        checkIndex(index);

        ListItem<T> node = getNodeByIndex(index);
        return node.getData();
    }

    public T set(int index, T value) {
        checkIndex(index);

        ListItem<T> node = getNodeByIndex(index);
        T oldValue = node.getData();

        node.setData(value);

        return oldValue;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> node = getNodeByIndex(index - 1);
        ListItem<T> removedNode = node.getNext();

        T removedValue = removedNode.getData();

        node.setNext(removedNode.getNext());
        size--;

        return removedValue;
    }

    public void addFirst(T value) {
        head = new ListItem<>(value, head);
        size++;
    }

    public void addByIndex(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера списка " + size);
        }

        if (index == 0) {
            addFirst(value);
        } else {
            ListItem<T> node = getNodeByIndex(index - 1);
            ListItem<T> newNode = new ListItem<>(value, node.getNext());

            node.setNext(newNode);
            size++;
        }
    }

    public boolean remove(T value) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), value)) {
            head = head.getNext();
            size--;

            return true;
        }

        for (ListItem<T> node = head, previousNode = null; node != null; previousNode = node, node = node.getNext()) {
            if (Objects.equals(node.getData(), value)) {
                previousNode.setNext(node.getNext());
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
        ListItem<T> node = head;
        ListItem<T> previousNode = null;

        while (node != null) {
            ListItem<T> nextNode = node.getNext();

            node.setNext(previousNode);

            previousNode = node;
            node = nextNode;
        }

        head = previousNode;
    }

    public SingleLinkedList<T> getCopy() {
        SingleLinkedList<T> listCopy = new SingleLinkedList<>();

        if (size == 0) {
            return listCopy;
        }

        listCopy.head = new ListItem<>(head.getData());
        listCopy.size = size;

        for (ListItem<T> node = head.getNext(), listCopyNode = listCopy.head; node != null;
             node = node.getNext(), listCopyNode = listCopyNode.getNext()) {
            listCopyNode.setNext(new ListItem<>(node.getData()));
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

        for (ListItem<T> node = head; node != null; node = node.getNext()) {
            stringBuilder.append(node.getData()).append(", ");
        }

        return stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]").toString();
    }
}