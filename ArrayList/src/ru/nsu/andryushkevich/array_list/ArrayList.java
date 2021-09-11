package ru.nsu.andryushkevich.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше или равна 0. Переданное значение: " + capacity);
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public int getCapacity() {
        return items.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Следующего элемента не существует");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Невозможно совершать изменения во время итерации");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void increaseCapacity() {
        int capacity = items.length;

        if (capacity == 0) {
            capacity = 1;
        }

        items = Arrays.copyOf(items, capacity * 2);
    }

    @Override
    public boolean add(T t) {
        add(size, t);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);

            return true;
        }

        return false;
    }

    private void checkCollection(Collection<?> c) {
        if (c == null) {
            throw new NoSuchElementException("Переданной коллекции не существует");
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkCollection(c);

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше или равен " + size
                    + ". Переданное значение: " + index);
        }

        checkCollection(c);

        int collectionSize = c.size();

        if (collectionSize == 0) {
            return false;
        }

        ensureCapacity(size + collectionSize);
        System.arraycopy(items, index, items, index + collectionSize, size - index);

        size += collectionSize;
        modCount++;

        int i = index;

        for (T e : c) {
            items[i] = e;
            i++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkCollection(c);

        boolean isRemoved = false;

        for (int i = 0; i < size; i++) {
            if (c.contains(items[i])) {
                remove(i);

                isRemoved = true;

                i--;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCollection(c);

        boolean isRemoved = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);

                isRemoved = true;

                i--;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                items[i] = null;
            }

            modCount++;
        }

        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше " + size
                    + ". Переданное значение: " + index);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);

        T oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше или равен " + size
                    + ". Переданное значение: " + index);
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index < size - 1) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;

        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        size--;
        modCount++;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (T element : this) {
            stringBuilder.append(element).append(", ");
        }

        return stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]").toString();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}