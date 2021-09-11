package ru.nsu.andryushkevich.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_LENGTH = 10;
    private ArrayList<T>[] hashTable;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[DEFAULT_LENGTH];
    }

    public HashTable(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Длина массива хэш-таблицы должна быть больше или равна 0. Переданное значение: " + length);
        }

        //noinspection unchecked
        hashTable = (ArrayList<T>[]) new ArrayList[length];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % hashTable.length);
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

        return hashTable[index] != null && hashTable[index].contains(o);
    }

    private class HashTableIterator implements Iterator<T> {
        private int listCurrentIndex = 0;
        private int elementCurrentIndex = -1;
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
                throw new ConcurrentModificationException("Невозможно совершать изменения во время итерации");
            }

            while (hashTable[listCurrentIndex] == null || hashTable[listCurrentIndex].size() - 1 == elementCurrentIndex) {
                listCurrentIndex++;
                elementCurrentIndex = -1;
            }

            if (elementCurrentIndex < hashTable[listCurrentIndex].size() - 1) {
                elementCurrentIndex++;
            }

            count++;

            return hashTable[listCurrentIndex].get(elementCurrentIndex);
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
            return (T1[]) toArray();
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(hashTable, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        if (hashTable.length == 0) {
            hashTable = Arrays.copyOf(hashTable, 1);
        }

        if (contains(t)) {
            return false;
        }

        int index = getIndex(t);

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }

        size++;
        modCount++;

        return hashTable[index].add(t);
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }

        int index = getIndex(o);

        modCount++;
        size--;

        return hashTable[index].remove(o);
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

        boolean isRemoved = false;

        for (Object e : c) {
            if (remove(e)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCollection(c);

        boolean isRemoved = false;

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                isRemoved = list.retainAll(c);

                size -= list.size();
                modCount++;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size > 0) {
            modCount++;
        }

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                list.clear();
            }
        }

        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;

        for (ArrayList<T> arrayList : hashTable) {
            if (arrayList != null) {
                stringBuilder.append(i).append(" ").append(arrayList.toString()).append('\n');
            } else {
                stringBuilder.append(i).append(" null\n");
            }

            i++;
        }

        return stringBuilder.toString();
    }
}