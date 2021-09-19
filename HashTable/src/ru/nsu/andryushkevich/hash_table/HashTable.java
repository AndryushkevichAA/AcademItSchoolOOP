package ru.nsu.andryushkevich.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_LENGTH = 10;

    private final ArrayList<T>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<T>[]) new ArrayList[DEFAULT_LENGTH];
    }

    public HashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Длина массива хэш-таблицы должна быть больше 0. Переданное значение: " + arrayLength);
        }

        //noinspection unchecked
        lists = (ArrayList<T>[]) new ArrayList[arrayLength];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
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
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    private class HashTableIterator implements Iterator<T> {
        private int listIndex = 0;
        private int elementIndex = -1;
        private int count = 0;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Следующего элемента не существует");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Совершено изменение во время итерации");
            }

            while (lists[listIndex] == null || lists[listIndex].size() - 1 == elementIndex) {
                listIndex++;
                elementIndex = -1;
            }

            if (elementIndex < lists[listIndex].size() - 1) {
                elementIndex++;
            }

            count++;

            return lists[listIndex].get(elementIndex);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (Object e : this) {
            array[i] = e;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        size++;
        modCount++;

        return lists[index].add(item);
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        modCount++;
        size--;

        return lists[index] != null && lists[index].remove(o);
    }

    private void checkCollection(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Ссылкой переданной коллекции является null");
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
        checkCollection(c);

        if (c.isEmpty()) {
            return false;
        }

        boolean isAdded = false;

        for (T e : c) {
            if (add(e)) {
                isAdded = true;
            }
        }

        return isAdded;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkCollection(c);

        if (c.isEmpty()) {
            return false;
        }

        int changesCount = 0;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                int listInitialSize = list.size();

                if (list.removeAll(c)) {
                    changesCount++;

                    size -= listInitialSize - list.size();
                }
            }
        }

        boolean isRemoved = changesCount > 0;

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCollection(c);

        int changesCount = 0;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                int listInitialSize = list.size();

                if (list.retainAll(c)) {
                    changesCount++;

                    size -= listInitialSize - list.size();
                }
            }
        }

        boolean isRemoved = changesCount > 0;

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<T> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;

        for (ArrayList<T> list : lists) {
            stringBuilder.append(i);

            if (list != null) {
                stringBuilder.append(" ").append(list.toString());
            } else {
                stringBuilder.append(" null");
            }

            stringBuilder.append(System.lineSeparator());

            i++;
        }

        return stringBuilder.toString();
    }
}