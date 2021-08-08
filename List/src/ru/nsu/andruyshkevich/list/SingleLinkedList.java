package ru.nsu.andruyshkevich.list;

public class SingleLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirstValue() {
        if (size == 0) {
            return null;
        }

        return head.getData();
    }

    private void throwIllegalIndexException(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размера списка " + size);
        }
    }

    private ListItem<T> getNodeByIndex(int index) {
        ListItem<T> node = head;

        for (int i = 1; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public T getValue(int index) {
        throwIllegalIndexException(index);

        ListItem<T> node = getNodeByIndex(index);
        return node.getData();
    }

    public T setValue(int index, T value) {
        throwIllegalIndexException(index);

        ListItem<T> node = getNodeByIndex(index);
        T oldValue = node.getData();

        node.setData(value);

        return oldValue;
    }

    public T removeByIndex(int index) {
        throwIllegalIndexException(index);

        if (index == 0) {
            return removeFirstItem();
        }

        ListItem<T> node = getNodeByIndex(index);
        ListItem<T> removedNode = node.getNext();

        T removedValue = removedNode.getData();

        node.setNext(removedNode.getNext());
        size--;

        return removedValue;
    }

    public void addFirstItem(T value) {
        head = new ListItem<>(value, head);
        size++;
    }

    public void addByIndex(int index, T value) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть > 0. Переданое значение: " + index);
        }

        if (index == 0) {
            addFirstItem(value);
        }

        if (index > 0) {
            ListItem<T> node = getNodeByIndex(index);
            ListItem<T> newNode = new ListItem<>(value, node.getNext());

            node.setNext(newNode);
            size++;
        }
    }

    public boolean removeNodeByValue(T value) {
        if (head.getData().equals(value)) {
            head = head.getNext();
            size--;

            return true;
        }

        for (ListItem<T> node = head, previousNode = null; node != null; previousNode = node, node = node.getNext()) {
            if (node.getData().equals(value)) {
                previousNode.setNext(node.getNext());
                size--;

                return true;
            }
        }

        return false;
    }

    public T removeFirstItem() {
        if (head == null) {
            return null;
        }

        T firstValue = head.getData();

        head = head.getNext();
        size--;

        return firstValue;
    }

    public void reverse() {
        ListItem<T> node = head;
        ListItem<T> previousNode = null;

        while (node != null) {
            ListItem<T> nexNode = node.getNext();

            node.setNext(previousNode);

            previousNode = node;
            node = nexNode;
        }

        head = previousNode;
    }

    public void copy(SingleLinkedList<T> list) {
        int i = 0;

        for (ListItem<T> node = list.head; node != null; node = node.getNext()) {
            addByIndex(i, node.getData());

            i++;
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "( )";
        }

        StringBuilder line = new StringBuilder();

        for (ListItem<T> node = head; node != null; node = node.getNext()) {
            line.append(node.getData()).append(", ");
        }

        return "(" + line.delete(line.length() - 2, line.length()) + ")";
    }
}