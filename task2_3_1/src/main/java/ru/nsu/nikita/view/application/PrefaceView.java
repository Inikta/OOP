package ru.nsu.nikita.view.application;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Locale;

public class PrefaceView extends Parent {

    @FXML
    public VBox root;

    @FXML
    private TextField xSizeTextField;
    @FXML
    private TextField ySizeTextField;
    @FXML
    private TextField goalTextField;

    @FXML
    private Button startButton;

    private Scene gameScene;

    public IntegerProperty xSizeProperty;
    public IntegerProperty ySizeProperty;
    public IntegerProperty goalProperty;

    public BooleanProperty readyProperty;

    private boolean xSizeReady;
    private boolean ySizeReady;
    private boolean goalReady;

    @FXML
    public void initialize() {
        xSizeProperty = new SimpleIntegerProperty(0);
        ySizeProperty = new SimpleIntegerProperty(0);
        goalProperty = new SimpleIntegerProperty(0);

        readyProperty = new SimpleBooleanProperty(false);

        xSizeReady = false;
        ySizeReady = false;
        goalReady = false;
    }

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
        textField.setStyle("-fx-background-color: DEFAULT");

        String input = textField.getText().toLowerCase(Locale.ROOT);
        boolean parameterReady = wrongInputHandler(input, textField, parameter, 1);
        if (parameterReady) {
            textField.setStyle("-fx-background-color: rgba(144,238,144,0.75)");
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
        startButton.setStyle("-fx-background-color: rgba(144,238,144,0.75)");


    }

    private boolean wrongInputHandler(String input, TextField textField, IntegerProperty parameter, Integer minNumber) {
        Integer number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            textField.setStyle("-fx-background-color: rgba(248,41,41,0.6)");
            parameter.set(-1);
            return false;
        }

        if (number < minNumber) {
            textField.setStyle("-fx-background-color: rgba(248,41,41,0.6)");
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
