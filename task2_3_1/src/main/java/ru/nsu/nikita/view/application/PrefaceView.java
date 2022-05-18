package ru.nsu.nikita.view.application;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Locale;

public class PrefaceView extends Parent {

    @FXML
    public VBox root;

    @FXML
    public CheckBox randomWalls;
    @FXML
    public CheckBox surroundField;
    @FXML
    public CheckBox randomFruits;
    @FXML
    public CheckBox infiniteGame;
    @FXML
    private CheckBox lethalSelfCrash;

    @FXML
    private TextField xSizeTextField;
    @FXML
    private TextField ySizeTextField;
    @FXML
    private TextField goalTextField;

    @FXML
    private CheckBox difficultyIncrease;
    @FXML
    private TextField initDifficulty;

    @FXML
    private Button startButton;

    private Scene gameScene;

    public IntegerProperty xSizeProperty;
    public IntegerProperty ySizeProperty;
    public IntegerProperty goalProperty;

    public BooleanProperty readyProperty;

    public BooleanProperty surroundFieldProperty;
    public BooleanProperty randomWallsProperty;
    public BooleanProperty randomFruitsProperty;
    public BooleanProperty infiniteGameProperty;
    public BooleanProperty lethalSelfCrashProperty;

    public BooleanProperty difficultyIncreaseProperty;
    public IntegerProperty initDifficultyProperty;

    private boolean xSizeReady;
    private boolean ySizeReady;
    private boolean goalReady;

    private String defaultTextFieldStyle;
    private String defaultButtonStyle;


    @FXML
    public void initialize() {
        xSizeProperty = new SimpleIntegerProperty(0);
        ySizeProperty = new SimpleIntegerProperty(0);
        goalProperty = new SimpleIntegerProperty(0);

        readyProperty = new SimpleBooleanProperty(false);

        surroundFieldProperty = new SimpleBooleanProperty(false);
        randomWallsProperty = new SimpleBooleanProperty(false);
        randomFruitsProperty = new SimpleBooleanProperty(false);
        infiniteGameProperty = new SimpleBooleanProperty(false);
        lethalSelfCrashProperty = new SimpleBooleanProperty(false);

        difficultyIncreaseProperty = new SimpleBooleanProperty(false);
        initDifficultyProperty = new SimpleIntegerProperty(0);

        xSizeReady = false;
        ySizeReady = false;
        goalReady = false;

        defaultTextFieldStyle = xSizeTextField.getStyle();
        defaultButtonStyle = startButton.getStyle();


    }

    public void onRestart() {
        readyProperty.set(false);
        xSizeTextField.setStyle(defaultTextFieldStyle);
        ySizeTextField.setStyle(defaultTextFieldStyle);
        goalTextField.setStyle(defaultTextFieldStyle);
        initDifficulty.setStyle(defaultTextFieldStyle);

        startButton.setStyle(defaultButtonStyle);
    }

    @FXML
    public void onRandomWallsSet() {
        randomWallsProperty.set(!randomWallsProperty.getValue());
    }

    @FXML
    public void onSurroundFieldset() {
        surroundFieldProperty.set(!surroundFieldProperty.getValue());
    }

    @FXML
    public void onRandomFruitsSet() {
        randomFruitsProperty.set(!randomFruitsProperty.getValue());
    }

    @FXML
    public void onInfiniteGameSet() {
        infiniteGameProperty.set(!infiniteGameProperty.getValue());
    }

    @FXML
    public void onDifficultyIncreaseSet() {
        difficultyIncreaseProperty.set(!difficultyIncreaseProperty.getValue());
    }

    @FXML
    public void onLethalSelfCrashSet() {
        lethalSelfCrashProperty.set(!lethalSelfCrashProperty.getValue());
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

    @FXML
    public void onInitDifficultyInput() {
        onTextInput(initDifficulty, initDifficultyProperty);
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
            startButton.setStyle("-fx-background-color: rgba(144,238,144,0.75)");
        }
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
