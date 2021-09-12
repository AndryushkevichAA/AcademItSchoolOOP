package ru.nsu.andryushkevich.matrix;

import ru.nsu.andryushkevich.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть больше 0. Переданные размеры: "
                    + rowsCount + "х" + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(double[][] arrays) {
        if (arrays.length == 0) {
            throw new IllegalArgumentException("Количество строк переданного двумерного массива должно быть больше 0");
        }

        int maxRowLength = 0;

        for (double[] row : arrays) {
            if (maxRowLength < row.length) {
                maxRowLength = row.length;
            }
        }

        if (maxRowLength == 0) {
            throw new IllegalArgumentException("Количество столбцов переданного двумерного массива должно быть больше 0");
        }

        rows = new Vector[arrays.length];

        for (int i = 0; i < arrays.length; i++) {
            rows[i] = new Vector(maxRowLength, arrays[i]);
        }
    }

    public Matrix(Vector[] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива векторов должен быть больше 0");
        }

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
    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    private void checkRowIndex(int index) {
        int rowsCount = getRowsCount();

        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше " + rowsCount
                    + " Переданное значение: " + index);
        }
    }

    // Получение вектора-строки по индексу
    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    // Задание вектора-строки по индексу
    public void setRow(int index, Vector row) {
        checkRowIndex(index);

        int columnsCount = getColumnsCount();
        int rowSize = row.getSize();

        if (rowSize != columnsCount) {
            throw new IllegalArgumentException("Размер переданного вектора должен быть равен " + columnsCount
                    + ". Размер переданного вектора: " + rowSize);
        }

        rows[index] = new Vector(columnsCount);
        rows[index].add(row);
    }

    // Получение вектора-столбца по индексу
    public Vector getColumn(int index) {
        int columnsCount = getColumnsCount();

        if (index < 0 || index >= columnsCount) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен 0 и меньше " + columnsCount
                    + " Переданное значение: " + index);
        }

        int rowsCount = getRowsCount();
        Vector column = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    // Транспонирование матрицы
    public void transpose() {
        Vector[] transposedMatrixRows = new Vector[getColumnsCount()];

        for (int i = 0; i < transposedMatrixRows.length; i++) {
            transposedMatrixRows[i] = getColumn(i);
        }

        rows = transposedMatrixRows;
    }

    // Умножение на скаляр
    public Matrix multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }

        return this;
    }

    private static double getMinor(Matrix matrix, int index) {
        int size = matrix.rows.length - 1;
        Matrix resultMatrix = new Matrix(size, size);

        for (int i = 0, resultMatrixI = 0; i <= size; i++) {
            for (int j = 0, resultMatrixJ = 0; j <= size; j++) {
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

    // Определитель матрицы
    public double getDeterminant() {
        int columnsCount = getColumnsCount();
        int rowsCount = getRowsCount();

        if (rowsCount != columnsCount) {
            throw new UnsupportedOperationException("Количество строк должно быть равно количеству столбцов. Размеры матрицы:"
                    + rowsCount + "x" + columnsCount);
        }

        if (rowsCount == 1) {
            return rows[0].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < rowsCount; i++) {
            determinant += Math.pow(-1, i) * rows[i].getComponent(0) * getMinor(this, i);
        }

        return determinant;
    }

    // Умножение на вектор
    public Vector multiplyByVector(Vector vector) {
        int columnsCount = getColumnsCount();
        int vectorSize = vector.getSize();

        if (columnsCount != vectorSize) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть равно размеру вектора. Количество столбцов: "
                    + columnsCount + ". Размер вектора: " + vectorSize);
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double resultVectorElement = 0;

            for (int j = 0; j < columnsCount; j++) {
                resultVectorElement += rows[i].getComponent(j) * vector.getComponent(j);
            }

            resultVector.setComponent(i, resultVectorElement);
        }

        return resultVector;
    }

    private void checkMatrixSizesAreEqual(Matrix matrix) {
        int columnsCount = getColumnsCount();
        int rowsCount = getRowsCount();
        int matrixColumnsCount = matrix.getColumnsCount();
        int matrixRowsCount = matrix.getRowsCount();

        if (rowsCount != matrixRowsCount || columnsCount != matrixColumnsCount) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры. Размер первой матрицы: "
                    + rowsCount + "x" + columnsCount + " Размер второй матрицы: "
                    + matrixRowsCount + "x" + matrixColumnsCount);
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
        matrix1.checkMatrixSizesAreEqual(matrix2);

        return new Matrix(matrix1).add(matrix2);
    }

    // Разность матриц
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkMatrixSizesAreEqual(matrix2);

        return new Matrix(matrix1).subtract(matrix2);
    }

    // Умножение матриц
    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int matrix1ColumnsCount = matrix1.getColumnsCount();
        int matrix2RowsCount = matrix2.getRowsCount();

        if (matrix1ColumnsCount != matrix2RowsCount) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы и количество строк второй матрицы должны быть равны. " +
                    "Количество столбцов первой матрицы: " + matrix1ColumnsCount +
                    " Количество строк второй матрицы: " + matrix2RowsCount);
        }

        int matrix1RowsCount = matrix1.getRowsCount();
        int matrix2ColumnsCount = matrix2.getColumnsCount();

        Matrix resultMatrix = new Matrix(matrix1RowsCount, matrix2ColumnsCount);

        for (int i = 0; i < matrix1RowsCount; i++) {
            for (int j = 0; j < matrix2ColumnsCount; j++) {
                double resultMatrixElement = 0;

                for (int k = 0; k < matrix2.rows.length; k++) {
                    resultMatrixElement += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                resultMatrix.rows[i].setComponent(j, resultMatrixElement);
            }
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Arrays.toString(rows)).setCharAt(0, '{');
        stringBuilder.setCharAt(stringBuilder.length() - 1, '}');

        return stringBuilder.toString();
    }
}