package ru.nsu.andryushkevich.temperature.model;

import ru.nsu.andryushkevich.temperature.interfaces.TemperatureConverter;

public class Kelvin implements TemperatureConverter {
    @Override
    public double convertToCelsius(double degree) {
        return degree - 273.15;
    }

    @Override
    public double convertFromCelsius(double degree) {
        return degree + 273.15;
    }

    @Override
    public String toString() {
        return "Кельвин";
    }
}