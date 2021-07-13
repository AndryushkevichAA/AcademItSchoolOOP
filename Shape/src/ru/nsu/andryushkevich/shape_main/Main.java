package ru.nsu.andryushkevich.shape_main;

import ru.nsu.andryushkevich.shape.*;

import java.util.Arrays;

public class Main {
    public static Shape findShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape findShapeWithSecondPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(20), new Triangle(3, 6, 18, 20, 13, 17),
                new Square(35), new Rectangle(30, 55), new Circle(7),
                new Triangle(14, 28, 10, 5, 12, 6), new Square(15),
                new Rectangle(20, 10)};

        System.out.println("Фигура с самой большой площадью: " + findShapeWithMaxArea(shapes));

        System.out.println("Фигура имеющая второй по величине периметр: " + findShapeWithSecondPerimeter(shapes));
    }
}