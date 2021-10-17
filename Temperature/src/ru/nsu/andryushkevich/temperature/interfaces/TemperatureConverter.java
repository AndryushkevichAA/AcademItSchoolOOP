package ru.nsu.andryushkevich.temperature.interfaces;

public interface TemperatureConverter {
    double convertToCelsius(double degree);

    double convertFromCelsius(double degree);
}