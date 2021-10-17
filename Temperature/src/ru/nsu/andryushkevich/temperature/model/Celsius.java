package ru.nsu.andryushkevich.temperature.model;

import ru.nsu.andryushkevich.temperature.interfaces.TemperatureConverter;

public class Celsius implements TemperatureConverter {
    @Override
    public double convertToCelsius(double degree) {
        return degree;
    }

    @Override
    public double convertFromCelsius(double degree) {
        return degree;
    }

    @Override
    public String toString() {
        return "Градус Цельсия";
    }
}