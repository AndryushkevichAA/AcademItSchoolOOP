package ru.nsu.andryushkevich.shape_main;

import ru.nsu.andryushkevich.shape.*;

import java.util.Arrays;

public class Main {
    public static Shape findShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape findShapeWithSecondPerimeter(Shape[] shapes) {
        if (shapes.length < 2) {
            return null;
        }

        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static String getInformationAboutShape(Shape shape) {
        if (shape == null) {
            return "фигура не существует";
        }

        return shape.toString();
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(20),
                new Triangle(3, 6, 18, 20, 13, 17),
                new Square(35),
                new Rectangle(30, 55),
                new Circle(7),
                new Triangle(14, 28, 10, 5, 12, 6),
                new Square(15),
                new Rectangle(20, 10)
        };

        System.out.println("Фигура с самой большой площадью: "
                + getInformationAboutShape(findShapeWithMaxArea(shapes)));
        System.out.println("Фигура имеющая второй по величине периметр: "
                + getInformationAboutShape(findShapeWithSecondPerimeter(shapes)));
    }
}