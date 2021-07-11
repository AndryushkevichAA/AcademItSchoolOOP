package ru.nsu.andryushkevich.circle;

import ru.nsu.andryushkevich.shape.Shape;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius * 2;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Круг. Радиус: " + radius + " Площадь: " + getArea() + " Периметр: " + getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Double.hashCode(radius);

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

        Circle circle = (Circle) object;

        return radius == circle.radius;
    }
}