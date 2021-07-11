package ru.nsu.andryushkevich.triangle;

import ru.nsu.andryushkevich.shape.Shape;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double side1 = Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
        double side2 = Math.sqrt((Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2)));
        double side3 = Math.sqrt((Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2)));
        double semiPerimeter = getPerimeter() / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - side1) * (semiPerimeter - side2) * (semiPerimeter - side3));
    }

    @Override
    public double getPerimeter() {
        double side1 = Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
        double side2 = Math.sqrt((Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2)));
        double side3 = Math.sqrt((Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2)));

        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Треугольник. Ширина: " + getWidth() + " Высота: " + getHeight()
                + " Площадь: " + getArea() + " Периметр: " + getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) object;

        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2
                && x3 == triangle.x3 && y3 == triangle.y3;
    }
}