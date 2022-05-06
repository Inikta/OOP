package ru.nsu.nikita.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.css.ParsedValue;
import javafx.css.converter.StringConverter;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Locale;

public class PrefaceView {

    @FXML
    private VBox root;

    @FXML
    private TextField xSizeTextField;
    @FXML
    private TextField ySizeTextField;
    @FXML
    private TextField goalTextField;

    @FXML
    private Button startButton;

    public IntegerProperty xSizeProperty;
    public IntegerProperty ySizeProperty;
    public IntegerProperty goalProperty;

    public BooleanProperty readyProperty;

    private boolean xSizeReady = false;
    private boolean ySizeReady = false;
    private boolean goalReady = false;

    @FXML
    public void onXSizeTextInput() {
        xSizeReady = onTextInput(xSizeTextField, xSizeProperty);
    }

    @FXML
    public void onYSizeTextInput() {
        ySizeReady = onTextInput(ySizeTextField, ySizeProperty);
    }

    @FXML
    public void onGoalTextInput() {
        goalReady = onTextInput(goalTextField, goalProperty);
    }

    private boolean onTextInput(TextField textField, IntegerProperty parameter) {
        textField.setStyle("-fx-fill: DEFAULT");

        String input = textField.getText().toLowerCase(Locale.ROOT);
        boolean parameterReady = wrongInputHandler(input, textField, parameter, 1);
        if (parameterReady) {
            textField.setStyle("-fx-fill: LIGHTGREEN");
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void onStartButtonPressed() {
        if (xSizeReady & ySizeReady & goalReady) {
            readyProperty.set(true);
        }
    }

    private boolean wrongInputHandler(String input, TextField textField, IntegerProperty parameter, Integer minNumber) {
        Integer number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            textField.setStyle("-fx-fill: RED");
            parameter.set(-1);
            return false;
        }

        if (number < minNumber) {
            textField.setStyle("-fx-fill: RED");
            parameter.set(-1);
            return false;
        }

        parameter.set(number);
        return true;
    }

    public int getxSizeProperty() {
        return xSizeProperty.get();
    }

    public IntegerProperty xSizePropertyProperty() {
        return xSizeProperty;
    }

    public void setxSizeProperty(int xSizeProperty) {
        this.xSizeProperty.set(xSizeProperty);
    }

    public int getySizeProperty() {
        return ySizeProperty.get();
    }

    public IntegerProperty ySizePropertyProperty() {
        return ySizeProperty;
    }

    public void setySizeProperty(int ySizeProperty) {
        this.ySizeProperty.set(ySizeProperty);
    }

    public int getGoalProperty() {
        return goalProperty.get();
    }

    public IntegerProperty goalPropertyProperty() {
        return goalProperty;
    }

    public void setGoalProperty(int goalProperty) {
        this.goalProperty.set(goalProperty);
    }
}
