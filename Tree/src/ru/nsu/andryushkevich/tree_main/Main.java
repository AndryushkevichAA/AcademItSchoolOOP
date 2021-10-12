package ru.nsu.andryushkevich.tree_main;

import ru.nsu.andryushkevich.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> treeWithComparator = new Tree<>(Integer::compareTo);
        treeWithComparator.add(7);
        treeWithComparator.add(2);
        treeWithComparator.add(9);
        treeWithComparator.add(1);
        treeWithComparator.add(6);
        treeWithComparator.add(5);
        treeWithComparator.add(8);
        treeWithComparator.add(0);
        treeWithComparator.add(19);
        treeWithComparator.add(19);

        System.out.println("Печать дерева с компаратором: ");
        treeWithComparator.printTree();

        Tree<Integer> tree = new Tree<>();
        tree.add(7);
        tree.add(2);
        tree.add(9);
        tree.add(1);
        tree.add(6);
        tree.add(5);
        tree.add(8);
        tree.add(0);
        tree.add(19);
        tree.add(19);

        System.out.println("Печать дерева без компаратора (тип реализует интерфейс Comparable): ");
        tree.printTree();

        System.out.println("Узлы в порядке обхода дерева в ширину: ");
        tree.visitByWidth(System.out::println);

        System.out.println("Узлы в порядке обхода дерева в глубину: ");
        tree.visitByDepth(System.out::println);

        System.out.println("Узлы в порядке обхода дерева в глубину с рекурсией: ");
        tree.visitByDepthWithRecursion(System.out::println);

        System.out.println("Размер дерева: " + tree.getSize());

        Integer data1 = 9;
        if (tree.contains(data1)) {
            System.out.println("Узел со значением " + data1 + " найден.");
        } else {
            System.out.println("Узел со значением " + data1 + " не найден.");
        }

        Integer data2 = 2;
        if (tree.remove(data2)) {
            System.out.println("Дерево после удаления узла со значением " + data2 + ": ");
            tree.printTree();
        } else {
            System.out.println("Узел со значением " + data2 + " не найден. Удаление невозможно.");
        }

        System.out.println("Размер дерева: " + tree.getSize());
    }
}