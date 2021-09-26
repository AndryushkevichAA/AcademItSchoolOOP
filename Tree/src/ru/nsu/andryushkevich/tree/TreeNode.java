package ru.nsu.andryushkevich.tree;

class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private final T data;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("Значение узла: ").append(data).append(". ");

        if (left != null) {
            stringBuilder.append("Левый ребёнок: ").append(left.data).append(". ");
        }

        if (right != null) {
            stringBuilder.append("Правый ребёнок: ").append(right.data).append(".");
        }

        return stringBuilder.append(System.lineSeparator()).toString();
    }
}