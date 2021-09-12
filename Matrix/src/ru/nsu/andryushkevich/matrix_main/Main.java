package ru.nsu.andryushkevich.matrix_main;

import ru.nsu.andryushkevich.matrix.Matrix;
import ru.nsu.andryushkevich.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] numbers1 = {23.5, 10.0, 34.5};
        double[] numbers2 = {13.5, 15.0, 30.5, 2.1};
        double[] numbers3 = {18.9, 5.0, 2.5};

        double[][] arrays = {numbers1, numbers2, numbers3};

        Vector[] vectors = {
                new Vector(3),
                new Vector(numbers1),
                new Vector(3, numbers2),
                new Vector(numbers3)
        };

        double[][] matrix = {
                {1, 20, 3},
                {5, 26, 7},
                {9, 10, 31}
        };

        Matrix matrix1 = new Matrix(arrays);
        Matrix matrix2 = new Matrix(vectors);
        Matrix matrix3 = new Matrix(3, 3);
        Matrix matrix4 = new Matrix(new Matrix(matrix));

        System.out.println("Матрица размера " + matrix1.getRowsCount() + " х " + matrix1.getColumnsCount() + " : " + matrix1);
        System.out.println("Матрица размера " + matrix2.getRowsCount() + " х " + matrix2.getColumnsCount() + " : " + matrix2);
        System.out.println("Матрица нулей размера " + matrix3.getRowsCount() + " х " + matrix3.getColumnsCount() + " : " + matrix3);

        Vector row = new Vector(numbers3);
        matrix2.setRow(2, row);
        System.out.println("После вставки строки " + row + " Матрица 2: " + matrix2);

        int rowIndex = 0;
        System.out.println("Вектор-строка по индексу " + rowIndex + ": " + matrix1.getRow(rowIndex));

        int columnIndex = 2;
        System.out.println("Вектор-столбец по индексу " + columnIndex + ": " + matrix1.getColumn(columnIndex));

        matrix1.transpose();
        System.out.println("Транспонирование матрицы: " + matrix1);

        int scalar = 2;
        matrix1.multiplyByScalar(scalar);
        System.out.print(matrix1 + " * " + scalar + " = ");
        System.out.println(matrix1.multiplyByScalar(scalar));

        matrix1.add(matrix2);
        System.out.println("Матрица 1 после прибавления матрицы 2 " + matrix1);

        matrix2.subtract(matrix1);
        System.out.println("Матрица 2 после вычитания матрицы 1 " + matrix2);

        Matrix sumMatrix = Matrix.getSum(matrix3, matrix4);
        System.out.println("Сумма матриц 3 и 4: " + sumMatrix);

        Matrix differenceMatrix = Matrix.getDifference(matrix3, matrix4);
        System.out.println("Разность матриц 3 и 4: " + differenceMatrix);

        double determinant = matrix4.getDeterminant();
        System.out.println("Определитель матрицы 4 = " + determinant);

        Vector vector = new Vector(numbers1);
        Vector multiplyByVectorResult = matrix4.multiplyByVector(vector);
        System.out.println(matrix4 + " * " + vector + " = " + multiplyByVectorResult);

        Matrix product = Matrix.getProduct(matrix1, matrix4);
        System.out.println(matrix1 + " * " + matrix4 + " = " + product);
    }
}