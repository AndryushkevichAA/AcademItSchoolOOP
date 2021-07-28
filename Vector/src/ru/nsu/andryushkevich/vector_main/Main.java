package ru.nsu.andryushkevich.vector_main;

import ru.nsu.andryushkevich.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] numbers1 = {23.5, 10.0, 34.5};
        double[] numbers2 = {13.5, 15.0, 30.5, 2.1};

        Vector vector1 = new Vector(4);
        Vector vector2 = new Vector(numbers1);
        Vector vector3 = new Vector(5, numbers2);
        Vector vector4 = new Vector(vector3);
        Vector vector5 = new Vector(3, numbers2);

        System.out.println("Вектор 1: " + vector1 + " Размерность: " + vector1.getSize()
                + ". Длина: " + vector1.getLength());
        System.out.println("Вектор 2: " + vector2 + " Размерность: " + vector2.getSize()
                + ". Длина: " + vector2.getLength());
        System.out.println("Вектор 3: " + vector3 + " Размерность: " + vector3.getSize()
                + ". Длина: " + vector3.getLength());
        System.out.println("Вектор 4: " + vector4 + " Размерность: " + vector4.getSize()
                + ". Длина: " + vector4.getLength());
        System.out.println("Вектор 5: " + vector5 + " Размерность: " + vector5.getSize()
                + ". Длина: " + vector5.getLength());

        vector2.add(vector3);
        System.out.println("Вектор 2 после прибавления к нему вектора 3 станет таким: " + vector2
                + " Размерность: " + vector2.getSize() + ". Длина: " + vector2.getLength());

        vector2.subtract(vector5);
        System.out.println("Вектор 2 после вычитания из него вектора 5 станет таким: " + vector2);

        System.out.println("Получение компонента по индексу: " + vector2.getComponent(1));

        vector3.setComponent(2, 1.0);
        System.out.println("Установка компонента по индексу: " + vector3);

        Vector sumVector = Vector.getSum(vector5, vector4);
        System.out.println(vector5 + " + " + vector4 + " = Суммарный вектор: " + sumVector);

        Vector differenceVector = Vector.getDifference(vector4, vector2);
        System.out.println(vector4 + " - " + vector2 + " = Вектор разности: " + differenceVector);

        vector5.multiplyByScalar(10.0);
        System.out.print(vector5 + " * " + 10 + " = ");
        System.out.println(vector5.multiplyByScalar(10.0));

        System.out.println("Разворот вектора: " + vector5.reverse());

        System.out.println("Скалярное произведение " + vector4 + " и " + vector5 + " = "
                + Vector.getScalarProduct(vector4, vector5));
    }
}