package ru.nsu.andryushkevich.temperature.interfaces;

public interface TemperatureView {
    void start();

    void setConvertedTemperature(double convertedTemperature);

    void addTemperatureViewListener(TemperatureViewListener temperatureViewListener);

    void removeTemperatureViewListener(TemperatureViewListener temperatureViewListener);

    void addTemperatureConverter(TemperatureConverter converter);
}