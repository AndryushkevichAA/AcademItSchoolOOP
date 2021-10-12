package ru.nsu.andryushkevich.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public Tree() {
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void addWithComparator(T data) {
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

    public void add(T data) {
        if (comparator != null) {
            addWithComparator(data);
            return;
        }

        if (!(data instanceof Comparable)) {
            throw new ClassCastException("Тип переданного значения должен реализовывать интерфейс Comparable. " +
                    "Переданое значение: " + data);
        }

        TreeNode<T> newNode = new TreeNode<>(data);

        if (size == 0) {
            root = newNode;
            size++;

            return;
        }

        TreeNode<T> node = root;

        while (true) {
            //noinspection unchecked
            Comparable<T> nodeData = (Comparable<T>) node.getData();

            int comparisonResult = nodeData.compareTo(data);

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

        if (comparator != null) {
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

        while (node != null) {
            //noinspection unchecked
            Comparable<T> nodeData = (Comparable<T>) node.getData();

            int comparisonResult = nodeData.compareTo(data);

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

    private static <T> TreeNode<T> getNext(TreeNode<T> node) {
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

        if (comparator != null) {
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
        } else {
            while (true) {
                if (removedNode == null) {
                    return false;
                }

                //noinspection unchecked
                Comparable<T> removedNodeData = (Comparable<T>) removedNode.getData();

                int comparisonResult = removedNodeData.compareTo(data);

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
            return;
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
            return;
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

    private static <T> void visitNodes(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visitNodes(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitNodes(node.getRight(), consumer);
        }
    }

    public void visitByDepthWithRecursion(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        visitNodes(root, consumer);
    }

    private static void printSpace(int spacesNumber) {
        for (int i = 0; i < spacesNumber; ++i) {
            System.out.print(" ");
        }
    }

    private static <T> void printNodes(TreeNode<T> node, int level) {
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
            return;
        }

        System.out.println();

        printNodes(root, 0);

        System.out.println();
    }
}