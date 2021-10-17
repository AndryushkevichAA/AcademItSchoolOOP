package ru.nsu.andryushkevich.temperature.controller;

import ru.nsu.andryushkevich.temperature.interfaces.TemperatureConverter;
import ru.nsu.andryushkevich.temperature.interfaces.TemperatureView;
import ru.nsu.andryushkevich.temperature.interfaces.TemperatureViewListener;

public class TemperatureController implements TemperatureViewListener {
    private final TemperatureConverter temperatureConverter;
    private final TemperatureView temperatureView;

    public TemperatureController(TemperatureConverter temperatureConverter, TemperatureView temperatureView) {
        this.temperatureConverter = temperatureConverter;
        this.temperatureView = temperatureView;
    }

    @Override
    public void convertTemperature(double degree) {
        temperatureView.setConvertedTemperature(temperatureConverter.convertToCelsius(degree));
    }
}