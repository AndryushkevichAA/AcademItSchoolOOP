package ru.nsu.andryushkevich.tree_main;

import ru.nsu.andryushkevich.tree.Tree;

public class Main {
    public static void main(String[] args) {
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


        System.out.println("Узлы в порядке обхода дерева в ширину: " + tree.toStringByWidth());
        System.out.println("Узлы в порядке обхода дерева в глубину: " + tree.toStringByDepth());

        System.out.println("Печать дерева с помощью обхода в глубину с рекурсией: ");
        tree.printTree();

        System.out.println("Размер дерева: " + tree.getSize());

        Integer data1 = 9;
        System.out.println("Результат поиска узла со значением " + data1 + ": ");
        System.out.println(tree.getNode(data1));

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