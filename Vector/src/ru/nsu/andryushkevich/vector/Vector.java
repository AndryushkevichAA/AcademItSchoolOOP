package ru.nsu.andryushkevich.vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private Double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("размерность должна быть больше 0");
        }

        this.n = n;

        components = new Double[n];
        Arrays.fill(components, 0.0);
    }

    public Vector(Vector vector) {
        this(vector.n, vector.components);
    }

    public Vector(Double[] components) {
        this.components = Arrays.copyOf(components, components.length);
        n = components.length;
    }

    public Vector(int n, Double[] components) {
        this(n);
        this.components = Arrays.copyOf(components, n);

        if (components.length < n) {
            Arrays.fill(this.components, components.length, n, 0.0);
        }
    }

    public int getSize() {
        return n;
    }

    private static Vector getOperationResult(Vector vector1, Vector vector2, int operationIndex) {
        int maxSize = Math.max(vector1.n, vector2.n);

        Vector vector1Copy = new Vector(maxSize, vector1.components);
        Vector vector2Copy = new Vector(maxSize, vector2.components);

        vector1.n = maxSize;

        if (operationIndex == 1) {
            for (int i = 0; i < vector1.n; i++) {
                vector1Copy.components[i] += vector2Copy.components[i];
            }
        }

        if (operationIndex == 2) {
            for (int i = 0; i < vector1.n; i++) {
                vector1Copy.components[i] -= vector2Copy.components[i];
            }
        }

        if (operationIndex == 3) {
            for (int i = 0; i < vector1.n; i++) {
                vector1Copy.components[i] *= vector2Copy.components[i];
            }
        }

        return vector1Copy;
    }

    // Прибавление вектора к вектору
    public Vector add(Vector vector) {
        Vector resultVector = getOperationResult(this, vector, 1);
        components = Arrays.copyOf(resultVector.components, resultVector.n);

        return this;
    }

    //сложение двух векторов
    public static Vector add(Vector vector1, Vector vector2) {
        return new Vector(vector1).add(vector2);
    }

    // Вычитание вектора из вектора
    public Vector subtract(Vector vector) {
        Vector resultVector = getOperationResult(this, vector, 2);
        components = Arrays.copyOf(resultVector.components, resultVector.n);

        return this;
    }

    // Вычитание двух векторов
    public static Vector subtract(Vector vector1, Vector vector2) {
        return new Vector(vector1).subtract(vector2);
    }

    // Умножение вектора на скаляр
    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < n; i++) {
            components[i] *= scalar;
        }

        return this;
    }

    // Разворот вектора
    public Vector expand() {
        return this.multiplyByScalar(-1);
    }

    // Скалярное умножение векторов
    public static Vector multiply(Vector vector1, Vector vector2) {
        return getOperationResult(vector1, vector2, 3);
    }

    //Получение длины вектора
    public double getLength() {
        double result = 0;

        for (int i = 0; i < n; i++) {
            result += components[i] * components[i];
        }

        return Math.sqrt(result);
    }

    //Получение компоненты по индексу
    public double getComponent(int index) {
        return components[index];
    }

    //Установка компоненты по индексу
    public Vector insertComponent(double component, int index) {
        components[index] = component;

        return this;
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

        return n == vector.n && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + n;
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    @Override
    public String toString() {
        String companentsLine = Arrays.toString(components);

        return "{" + companentsLine.substring(1, companentsLine.length() - 1) + "}";
    }
}