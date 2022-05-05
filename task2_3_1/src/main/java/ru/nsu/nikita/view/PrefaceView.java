package ru.nsu.nikita.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrefaceView {

    private Scene scene;

    @FXML
    private VBox root;
    @FXML
    private DoubleProperty sceneWidth;
    @FXML
    private DoubleProperty sceneHeight;

    @FXML
    private Label xSizeLabel;
    @FXML
    private Label ySizeLabel;
    @FXML
    private Label goalLabel;

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

    @FXML
    public void onXSizeTextInput() {

    }

    @FXML
    public void onYSizeTextInput() {

    }

    @FXML
    public void onGoalTextInput() {

    }

    @FXML
    public void onStartButtonPressed() {

    }

}
