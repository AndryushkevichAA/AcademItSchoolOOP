package ru.nsu.andryushkevich.matrix;

import ru.nsu.andryushkevich.vector.Vector;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Matrix {
    private final Vector[] rows;

    public Matrix(int rowsNumber, int columnsNumber) {
        rows = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            rows[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(double[][] arrays) {
        rows = new Vector[arrays.length];

        int maxRowLength = 0;

        for (double[] row : arrays) {
            if (maxRowLength < row.length) {
                maxRowLength = row.length;
            }
        }

        for (int i = 0; i < arrays.length; i++) {
            rows[i] = new Vector(maxRowLength, arrays[i]);
        }
    }

    public Matrix(Vector[] rows) {
        int maxVectorsSize = 0;

        for (Vector row : rows) {
            if (maxVectorsSize < row.getSize()) {
                maxVectorsSize = row.getSize();
            }
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxVectorsSize);
            this.rows[i].add(rows[i]);
        }
    }

    // Получение размеров матрицы
    public int getRowsNumber() {
        return rows.length;
    }

    public int getColumnsNumber() {
        if (getRowsNumber() == 0) {
            return 0;
        }

        return rows[0].getSize();
    }

    private void checkMatrixIsEmpty() {
        if (rows.length == 0) {
            throw new NoSuchElementException("Невозможно найти элемент. Пустая матрица");
        }
    }

    private void checkColumnIndex(int index) {
        int columnSize = getRowsNumber();

        if (index < 0 || index >= columnSize) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше " + columnSize
                    + " Переданое значение: " + index);
        }
    }

    // Получение вектора-строки по индексу
    public Vector getRow(int index) {
        checkMatrixIsEmpty();
        checkColumnIndex(index);

        return rows[index];
    }

    // Задание вектора-строки по индексу
    public void setRow(int index, Vector row) {
        checkColumnIndex(index);

        rows[index] = new Vector(getColumnsNumber());
        rows[index].add(row);
    }

    // Получение вектора-столбца по индексу
    public Vector getColumn(int index) {
        checkMatrixIsEmpty();

        int rowSize = getColumnsNumber();

        if (index < 0 || index >= rowSize) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше " + rowSize
                    + " Переданое значение: " + index);
        }

        int columnSize = getRowsNumber();
        Vector column = new Vector(columnSize);

        for (int i = 0; i < columnSize; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    // Транспонирование матрицы
    public Matrix transpose() {
        int columnsNumber = getColumnsNumber();

        Matrix temp = new Matrix(columnsNumber, getRowsNumber());

        for (int i = 0; i < columnsNumber; i++) {
            Vector column = getColumn(i);
            temp.rows[i] = column;
        }

        return temp;
    }

    // Умножение на скаляр
    public Matrix multiplyByScalar(double scalar) {
        checkMatrixIsEmpty();

        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }

        return this;
    }

    // Определитель матрицы
    private static double getMinor(Matrix matrix, int index) {
        int size = matrix.rows.length - 1;
        Matrix resultMatrix = new Matrix(size, size);

        for (int i = 0, resultMatrixI = 0; i < size + 1; i++) {
            for (int j = 0, resultMatrixJ = 0; j < size + 1; j++) {
                if (i != index && j != 0) {
                    resultMatrix.rows[resultMatrixI].setComponent(resultMatrixJ, matrix.rows[i].getComponent(j));

                    resultMatrixJ++;

                    if (resultMatrixJ == size) {
                        resultMatrixJ = 0;
                        resultMatrixI++;
                    }
                }
            }
        }

        return resultMatrix.getDeterminant();
    }

    public double getDeterminant() {
        int columnsNumber = getColumnsNumber();
        int rowsNumber = getRowsNumber();

        if (rowsNumber != columnsNumber) {
            throw new IllegalArgumentException("Количество строк должно быть равно количеству столбцов. Размеры матрицы:"
                    + rowsNumber + "x" + columnsNumber);
        }

        if (rowsNumber == 1) {
            return rows[0].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < rowsNumber; i++) {
            determinant += Math.pow(-1, i) * rows[i].getComponent(0) * getMinor(this, i);
        }

        return determinant;
    }

    // Умножение на вектор
    public Vector multiplyByVector(Vector vector) {
        int columnsNumber = getColumnsNumber();
        int vectorSize = vector.getSize();

        if (columnsNumber != vectorSize) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть равно размеру вектора. Количество столбцов: "
                    + columnsNumber + ". Размер вектора: " + vectorSize);
        }

        Vector resultVector = new Vector(rows.length);
        double resultVectorElement = 0;

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                resultVectorElement += rows[i].getComponent(j) * vector.getComponent(j);
            }

            resultVector.setComponent(i, resultVectorElement);
            resultVectorElement = 0;
        }

        return resultVector;
    }

    private void checkMatrixSizesAreEqual(Matrix matrix) {
        int columnsNumber = getColumnsNumber();
        int rowsNumber = getRowsNumber();
        int matrixColumnsNumber = matrix.getColumnsNumber();
        int matrixRowsNumber = matrix.getRowsNumber();

        if (rowsNumber != matrixRowsNumber || columnsNumber != matrixColumnsNumber) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры. Размер первой матрицы: "
                    + rowsNumber + "x" + columnsNumber + " Размер второй матрицы: "
                    + matrixRowsNumber + "x" + matrixColumnsNumber);
        }
    }

    // Сложение матриц
    public Matrix add(Matrix matrix) {
        checkMatrixSizesAreEqual(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }

        return this;
    }

    // Вычитание матриц
    public Matrix subtract(Matrix matrix) {
        checkMatrixSizesAreEqual(matrix);

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }

        return this;
    }

    // Сумма матриц
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        return new Matrix(matrix1).add(matrix2);
    }

    // Разность матриц
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        return new Matrix(matrix1).subtract(matrix2);
    }

    // Умножение матриц
    public static Matrix getMultiplyResult(Matrix matrix1, Matrix matrix2) {
        int matrix1columnsNumber = matrix1.getColumnsNumber();
        int matrix2rowsNumber = matrix2.getRowsNumber();

        if (matrix1columnsNumber != matrix2rowsNumber) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы и количество строк второй матрицы должны быть равны. " +
                    "Количество столбцов первой матрицы: " + matrix1columnsNumber +
                    " Количество строк второй матрицы: " + matrix2rowsNumber);
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowsNumber(), matrix2.getColumnsNumber());
        double resultMatrixElement = 0;

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.rows[i].getSize(); j++) {
                for (int k = 0; k < matrix2.rows.length; k++) {
                    resultMatrixElement += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                resultMatrix.rows[i].setComponent(j, resultMatrixElement);
                resultMatrixElement = 0;
            }
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        String vectorsLine = Arrays.toString(rows);

        return "{" + vectorsLine.substring(1, vectorsLine.length() - 1) + "}";
    }
}