package ru.nsu.andryushkevich.temperature.view;

import ru.nsu.andryushkevich.temperature.interfaces.TemperatureConverter;
import ru.nsu.andryushkevich.temperature.interfaces.TemperatureView;
import ru.nsu.andryushkevich.temperature.interfaces.TemperatureViewListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TemperatureGui implements TemperatureView {
    private final ArrayList<TemperatureViewListener> listeners = new ArrayList<>();

    private final JFrame frame = new JFrame("Преобразователь температур");
    private final JButton convertButton = new JButton("Перевести");
    private final JTextField entryTextField = new JTextField(15);
    private final JLabel inputRequestLabel = new JLabel("Введите значение температуры: ");
    private final JLabel resultLabel = new JLabel();
    private final JComboBox<TemperatureConverter> scalesComboBox1 = new JComboBox<>();
    private final JComboBox<TemperatureConverter> scalesComboBox2 = new JComboBox<>();

    private void createFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createContent() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints scalesComboBox1Constraints = new GridBagConstraints();
        scalesComboBox1Constraints.gridx = 0;
        scalesComboBox1Constraints.gridy = 0;
        scalesComboBox1Constraints.anchor = GridBagConstraints.CENTER;
        scalesComboBox1Constraints.weightx = 1.0;
        scalesComboBox1Constraints.insets = new Insets(-130, 0, -40, 0);
        panel.add(scalesComboBox1, scalesComboBox1Constraints);

        GridBagConstraints scalesComboBox2Constraints = new GridBagConstraints();
        scalesComboBox2Constraints.gridx = 2;
        scalesComboBox2Constraints.gridy = 0;
        scalesComboBox2Constraints.anchor = GridBagConstraints.CENTER;
        scalesComboBox2Constraints.weightx = 1.0;
        scalesComboBox2Constraints.insets = new Insets(-130, 0, -40, 10);
        panel.add(scalesComboBox2, scalesComboBox2Constraints);

        GridBagConstraints inputRequestLabelConstraints = new GridBagConstraints();
        inputRequestLabelConstraints.gridx = 0;
        inputRequestLabelConstraints.gridy = 1;
        inputRequestLabelConstraints.gridwidth = 3;
        inputRequestLabelConstraints.anchor = GridBagConstraints.WEST;
        inputRequestLabelConstraints.insets = new Insets(-40, 30, -40, 0);
        panel.add(inputRequestLabel, inputRequestLabelConstraints);

        GridBagConstraints entryTextFieldConstraints = new GridBagConstraints();
        entryTextFieldConstraints.gridx = 0;
        entryTextFieldConstraints.gridy = 2;
        entryTextFieldConstraints.anchor = GridBagConstraints.CENTER;
        entryTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        entryTextFieldConstraints.weightx = 1.0;
        entryTextFieldConstraints.insets = new Insets(30, 30, -40, 0);
        panel.add(entryTextField, entryTextFieldConstraints);

        GridBagConstraints convertButtonConstraints = new GridBagConstraints();
        convertButtonConstraints.gridx = 1;
        convertButtonConstraints.gridy = 2;
        convertButtonConstraints.anchor = GridBagConstraints.CENTER;
        convertButtonConstraints.weightx = 1.0;
        convertButtonConstraints.insets = new Insets(30, 20, -40, 10);
        panel.add(convertButton, convertButtonConstraints);

        GridBagConstraints resultLabelConstraints = new GridBagConstraints();
        resultLabelConstraints.gridx = 2;
        resultLabelConstraints.gridy = 2;
        resultLabelConstraints.anchor = GridBagConstraints.CENTER;
        resultLabelConstraints.weightx = 1.0;
        resultLabelConstraints.insets = new Insets(30, 0, -40, 0);
        panel.add(resultLabel, resultLabelConstraints);

        frame.setContentPane(panel);
    }

    private void initializeEvents() {
        convertButton.addActionListener(e -> {
            try {
                double temperature = Double.parseDouble(entryTextField.getText());

                resultLabel.setForeground(Color.BLACK);

                for (TemperatureViewListener listener : listeners) {
                    listener.convertTemperature(temperature);
                }

                TemperatureConverter converter1 = (TemperatureConverter) scalesComboBox1.getSelectedItem();
                TemperatureConverter converter2 = (TemperatureConverter) scalesComboBox2.getSelectedItem();

                resultLabel.setText(String.valueOf(converter2.convertFromCelsius(converter1.convertToCelsius(temperature))));
            } catch (NumberFormatException ex) {
                resultLabel.setForeground(Color.RED);
                resultLabel.setText("Температура должна быть числом!");
            }
        });
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            createFrame();
            createContent();
            initializeEvents();
        });
    }

    @Override
    public void setConvertedTemperature(double convertedTemperature) {
        resultLabel.setText(Double.toString(convertedTemperature));
    }

    @Override
    public void addTemperatureViewListener(TemperatureViewListener listener) {
        if (!listeners.add(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeTemperatureViewListener(TemperatureViewListener Listener) {
        listeners.remove(Listener);
    }

    @Override
    public void addTemperatureConverter(TemperatureConverter converter) {
        scalesComboBox1.addItem(converter);
        scalesComboBox2.addItem(converter);
    }
}