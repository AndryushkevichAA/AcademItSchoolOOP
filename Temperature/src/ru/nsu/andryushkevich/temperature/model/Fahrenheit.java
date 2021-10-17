package ru.nsu.andryushkevich.temperature.model;

import ru.nsu.andryushkevich.temperature.interfaces.TemperatureConverter;

public class Fahrenheit implements TemperatureConverter {
    @Override
    public double convertToCelsius(double degree) {
        return (degree - 32) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double degree) {
        return degree * 1.8 + 32;
    }

    @Override
    public String toString() {
        return "Градус Фаренгейта";
    }
}