package ru.nsu.andryushkevich.tree;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;
    private final Comparator<T> comparator = new TreeComparator<>();

    public void add(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);

        if (size == 0) {
            root = newNode;
            size++;

            return;
        }

        TreeNode<T> node = root;

        while (true) {
            int comparisonResult = comparator.compare(node.getData(), data);

            if (comparisonResult > 0) {
                if (node.getLeft() == null) {
                    node.setLeft(newNode);
                    size++;

                    return;
                }

                node = node.getLeft();
                continue;
            }

            if (node.getRight() == null) {
                node.setRight(newNode);
                size++;

                return;
            }

            node = node.getRight();
        }
    }

    public boolean contains(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> node = root;

        while (node != null) {
            int comparisonResult = comparator.compare(node.getData(), data);

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
                node = node.getLeft();
                continue;
            }

            node = node.getRight();
        }

        return false;
    }

    private TreeNode<T> getNext(TreeNode<T> node) {
        if (node.getRight() == null) {
            return node.getLeft();
        }

        return node.getRight();
    }

    private void setNext(TreeNode<T> parent, TreeNode<T> node, TreeNode<T> next) {
        if (parent == null) {
            root = next;
            return;
        }

        if (parent.getRight() == node) {
            parent.setRight(next);
            return;
        }

        parent.setLeft(next);
    }

    public boolean remove(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> removedNode = root;
        TreeNode<T> parent = null;

        while (true) {
            if (removedNode == null) {
                return false;
            }

            int comparisonResult = comparator.compare(removedNode.getData(), data);

            if (comparisonResult == 0) {
                break;
            }

            parent = removedNode;

            if (comparisonResult > 0) {
                removedNode = removedNode.getLeft();
            } else {
                removedNode = removedNode.getRight();
            }
        }

        TreeNode<T> left = removedNode.getLeft();
        TreeNode<T> right = removedNode.getRight();

        if (left == null && right == null) {
            setNext(parent, removedNode, null);

            size--;
            return true;
        }

        if (left == null || right == null) {
            setNext(parent, removedNode, getNext(removedNode));

            size--;
            return true;
        }

        TreeNode<T> rightBranchMinNodeParent = removedNode;
        TreeNode<T> rightBranchMinNode = right;

        if (rightBranchMinNode.getLeft() == null) {
            setNext(removedNode, right, rightBranchMinNode.getRight());
        } else {
            while (rightBranchMinNode.getLeft() != null) {
                rightBranchMinNodeParent = rightBranchMinNode;
                rightBranchMinNode = rightBranchMinNode.getLeft();
            }

            setNext(rightBranchMinNodeParent, left, rightBranchMinNode.getRight());
        }

        rightBranchMinNode.setLeft(removedNode.getLeft());
        rightBranchMinNode.setRight(removedNode.getRight());

        setNext(parent, removedNode, rightBranchMinNode);

        size--;
        return true;
    }

    public int getSize() {
        return size;
    }

    public void visitByWidth(Consumer<T> consumer) {
        if (size == 0) {
            throw new NoSuchElementException("Пустое дерево.");
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        for (TreeNode<T> node = queue.poll(); node != null; node = queue.poll()) {
            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void visitByDepth(Consumer<T> consumer) {
        if (size == 0) {
            throw new NoSuchElementException("Пустое дерево.");
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.push(root);

        for (TreeNode<T> node = stack.poll(); node != null; node = stack.poll()) {
            consumer.accept(node.getData());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    private String getSpace(int spacesNumber) {
        return " ".repeat(Math.max(0, spacesNumber));
    }

    private void visitByDepthWithRecursion(TreeNode<T> node, int level, BiConsumer<String, T> consumer) {
        if (node.getRight() != null) {
            visitByDepthWithRecursion(node.getRight(), level + 1, consumer);
        }

        consumer.accept(getSpace(level * 4), node.getData());

        if (node.getLeft() != null) {
            visitByDepthWithRecursion(node.getLeft(), level + 1, consumer);
        }
    }

    public void printTree() {
        if (size == 0) {
            throw new NoSuchElementException("Пустое дерево. Отсутствуют узлы для печати");
        }

        System.out.println();

        visitByDepthWithRecursion(root, 0, (s, t) -> System.out.println(s + t));

        System.out.println();
    }
}