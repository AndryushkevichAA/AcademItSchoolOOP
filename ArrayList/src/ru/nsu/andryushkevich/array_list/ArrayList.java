package ru.nsu.andryushkevich.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть > 0. Переданое значение: " + capacity);
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
        //noinspection unchecked
        T1[] newTypeItems = (T1[]) Arrays.copyOf(items, size, a.getClass());

        if (a.length < size) {
            return newTypeItems;
        }

        System.arraycopy(newTypeItems, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean add(T t) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = t;

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                if (i < size - 1) {
                    System.arraycopy(items, i + 1, items, i, size - i - 1);
                }

                size--;
                modCount++;

                return true;
            }
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

        if (c.size() == 0) {
            return false;
        }

        for (Object cElement : c) {
            if (!contains(cElement)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        checkCollection(c);

        if (c.size() == 0) {
            return false;
        }

        ensureCapacity(size + c.size());

        int i = size;

        for (T cElement : c) {
            items[i] = cElement;
            i++;
        }

        size += c.size();
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше или равен " + size
                    + ". Переданое значение: " + index);
        }

        checkCollection(c);

        int cSize = c.size();

        if (cSize == 0) {
            return false;
        }

        if (index == size) {
            return addAll(c);
        }

        ensureCapacity(size + cSize);
        System.arraycopy(items, index, items, index + cSize, size - index);

        size += cSize;
        modCount++;

        int i = index;

        for (T cElement : c) {
            items[i] = cElement;
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
                System.arraycopy(items, i + 1, items, i, size - i - 1);

                size--;
                modCount++;

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
                System.arraycopy(items, i + 1, items, i, size - i - 1);

                size--;
                modCount++;

                isRemoved = true;

                i--;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            modCount++;
        }

        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше " + size
                    + ". Переданое значение: " + index);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        T oldItem = items[index];
        items[index] = element;

        return oldItem;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index < size - 1) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;

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