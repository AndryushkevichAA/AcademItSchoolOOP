package ru.nsu.andryushkevich.shape_main;

import ru.nsu.andryushkevich.circle.Circle;
import ru.nsu.andryushkevich.rectangle.Rectangle;
import ru.nsu.andryushkevich.shape.*;
import ru.nsu.andryushkevich.square.Square;
import ru.nsu.andryushkevich.triangle.Triangle;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static Shape findShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparingInt(shape -> (int) shape.getArea()));

        return shapes[shapes.length - 1];
    }

    public static Shape findShapeWithSecondPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparingInt(shape -> (int) shape.getPerimeter()));

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];
        shapes[0] = new Circle(20);
        shapes[1] = new Triangle(3, 6, 18, 20, 13, 17);
        shapes[2] = new Square(35);
        shapes[3] = new Rectangle(30, 55);
        shapes[4] = new Circle(7);
        shapes[5] = new Triangle(14, 28, 10, 5, 12, 6);
        shapes[6] = new Square(15);
        shapes[7] = new Rectangle(20, 10);

        System.out.println("Фигура с самой большой площадью: " + findShapeWithMaxArea(shapes).toString());

        System.out.println("Фигура имеющая второй по величине периметр: "
                + findShapeWithSecondPerimeter(shapes).toString());
    }
}