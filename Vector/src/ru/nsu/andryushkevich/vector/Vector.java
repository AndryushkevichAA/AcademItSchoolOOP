package ru.nsu.andryushkevich.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("размерность не должна быть равна " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.components.length, vector.components);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("размерность не должна быть равна " + components.length);
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("размерность не должна быть равна " + size);
        }

        this.components = Arrays.copyOf(components, size);
    }

    public int getSize() {
        return components.length;
    }

    // Прибавление вектора к вектору
    public Vector add(Vector vector) {
        double[] copyVectorComponents = vector.components;

        if (components.length > vector.components.length) {
            copyVectorComponents = Arrays.copyOf(vector.components, components.length);
        }

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < components.length; i++) {
            components[i] += copyVectorComponents[i];
        }

        return this;
    }

    //Получение суммы двух векторов
    public static Vector getSum(Vector vector1, Vector vector2) {
        return new Vector(vector1).add(vector2);
    }

    // Вычитание вектора из вектора
    public Vector subtract(Vector vector) {
        double[] copyVectorComponents = vector.components;

        if (components.length > vector.components.length) {
            copyVectorComponents = Arrays.copyOf(vector.components, components.length);
        }

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < components.length; i++) {
            components[i] -= copyVectorComponents[i];
        }

        return this;
    }

    // Получение разности двух векторов
    public static Vector getDifference(Vector vector1, Vector vector2) {
        return new Vector(vector1).subtract(vector2);
    }

    // Умножение вектора на скаляр
    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }

        return this;
    }

    // Разворот вектора
    public Vector revers() {
        return multiplyByScalar(-1);
    }

    // Скалярное умножение векторов
    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double[] copyVector1Components = vector1.components;
        double[] copyVector2Components = vector2.components;

        if (vector1.components.length > vector2.components.length) {
            copyVector2Components = Arrays.copyOf(vector2.components, vector1.components.length);
        }

        if (vector1.components.length < vector2.components.length) {
            copyVector1Components = Arrays.copyOf(vector1.components, vector2.components.length);
        }

        double scalarProduct = 0;

        for (int i = 0; i < copyVector1Components.length; i++) {
            scalarProduct += copyVector1Components[i] * copyVector2Components[i];
        }

        return scalarProduct;
    }

    //Получение длины вектора
    public double getLength() {
        double componentsSquareSum = 0;

        for (double component : components) {
            componentsSquareSum += component * component;
        }

        return Math.sqrt(componentsSquareSum);
    }

    //Получение компоненты по индексу
    public double getComponent(int index) {
        return components[index];
    }

    //Установка компоненты по индексу
    public void setComponent(int index, double component) {
        components[index] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) object;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    @Override
    public String toString() {
        String componentsLine = Arrays.toString(components);

        return "{" + componentsLine.substring(1, componentsLine.length() - 1) + "}";
    }
}