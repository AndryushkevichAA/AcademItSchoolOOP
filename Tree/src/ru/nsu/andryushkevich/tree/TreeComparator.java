package ru.nsu.andryushkevich.tree;

import java.util.Comparator;
import java.util.Objects;

public class TreeComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }

        return Objects.compare(o1, o2, Comparator.naturalOrder());
    }
}