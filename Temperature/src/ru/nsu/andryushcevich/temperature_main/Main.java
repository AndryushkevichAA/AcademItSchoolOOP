package ru.nsu.andryushcevich.temperature_main;

import ru.nsu.andryushkevich.temperature.controller.TemperatureController;
import ru.nsu.andryushkevich.temperature.interfaces.TemperatureConverter;
import ru.nsu.andryushkevich.temperature.interfaces.TemperatureView;
import ru.nsu.andryushkevich.temperature.model.Celsius;
import ru.nsu.andryushkevich.temperature.model.Fahrenheit;
import ru.nsu.andryushkevich.temperature.model.Kelvin;
import ru.nsu.andryushkevich.temperature.view.TemperatureGui;

public class Main {
    public static void main(String[] args) {
        TemperatureView temperatureView = new TemperatureGui();

        TemperatureConverter temperatureConverter1 = new Celsius();
        temperatureView.addTemperatureConverter(temperatureConverter1);

        TemperatureController temperatureController1 = new TemperatureController(temperatureConverter1, temperatureView);
        temperatureView.addTemperatureViewListener(temperatureController1);

        TemperatureConverter temperatureConverter2 = new Fahrenheit();
        temperatureView.addTemperatureConverter(temperatureConverter2);

        TemperatureController temperatureController2 = new TemperatureController(temperatureConverter2, temperatureView);
        temperatureView.addTemperatureViewListener(temperatureController2);

        TemperatureConverter temperatureConverter3 = new Kelvin();
        temperatureView.addTemperatureConverter(temperatureConverter3);

        TemperatureController temperatureController3 = new TemperatureController(temperatureConverter3, temperatureView);
        temperatureView.addTemperatureViewListener(temperatureController3);

        temperatureView.start();
    }
}