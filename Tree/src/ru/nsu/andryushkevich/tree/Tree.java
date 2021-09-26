package ru.nsu.andryushkevich.tree;

import java.util.*;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;

    private int getHashCode(T data) {
        if (data == null) {
            return 0;
        }

        return data.hashCode();
    }

    public void add(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);

        if (size == 0) {
            root = newNode;
            size++;

            return;
        }

        TreeNode<T> node = root;

        while (true) {
            if (getHashCode(data) < getHashCode(node.getData())) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                    continue;
                }

                node.setLeft(newNode);
                size++;

                return;
            }

            if (node.getRight() != null) {
                node = node.getRight();
                continue;
            }

            node.setRight(newNode);
            size++;

            return;
        }
    }

    public TreeNode<T> getNode(T data) {
        if (size == 0) {
            return null;
        }

        TreeNode<T> node = root;

        while (true) {
            if (Objects.equals(data, node.getData())) {
                return node;
            }

            if (getHashCode(data) < getHashCode(node.getData())) {
                if (node.getLeft() != null) {
                    node = node.getLeft();
                    continue;
                }

                return null;
            }

            if (node.getRight() != null) {
                node = node.getRight();
                continue;
            }

            return null;
        }
    }

    private TreeNode<T> getParent(TreeNode<T> node) {
        TreeNode<T> parent = null;
        TreeNode<T> currentNode = root;

        while (currentNode != node) {
            parent = currentNode;

            if (getHashCode(node.getData()) < getHashCode(currentNode.getData())) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return parent;
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

        TreeNode<T> removedNode = getNode(data);

        if (removedNode == null) {
            return false;
        }

        TreeNode<T> parent = getParent(removedNode);
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

        TreeNode<T> rightBranchMinNode = right;

        if (rightBranchMinNode.getLeft() == null) {
            setNext(removedNode, right, rightBranchMinNode.getRight());
        } else {
            while (rightBranchMinNode.getLeft() != null) {
                rightBranchMinNode = rightBranchMinNode.getLeft();
            }

            setNext(getParent(rightBranchMinNode), left, rightBranchMinNode.getRight());
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

    public String toStringByWidth() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder().append('[');

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        for (TreeNode<T> node = queue.poll(); node != null; node = queue.poll()) {
            stringBuilder.append(node.getData()).append(", ");

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }

        return stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]").toString();
    }

    public String toStringByDepth() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder().append('[');

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.push(root);

        for (TreeNode<T> node = stack.poll(); node != null; node = stack.poll()) {
            stringBuilder.append(node.getData()).append(", ");

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }

        return stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]").toString();
    }

    private void printSpace(int spacesNumber) {
        for (int i = 0; i < spacesNumber; ++i) {
            System.out.print(" ");
        }
    }

    private void printNodes(TreeNode<T> node, int level) {
        if (node.getRight() != null) {
            printNodes(node.getRight(), level + 1);
        }

        printSpace(level * 4);
        System.out.println(node.getData());

        if (node.getLeft() != null) {
            printNodes(node.getLeft(), level + 1);
        }
    }

    public void printTree() {
        if (size == 0) {
            throw new NoSuchElementException("Пустое дерево. Отсутствуют узлы для печати");
        }

        System.out.println();

        printNodes(root, 0);

        System.out.println();
    }
}