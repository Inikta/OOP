package ru.nsu.nikita.view.screens_controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class WinningScreenViewController {
    @FXML
    private Button restartButton;

    @FXML
    private Button quitButton;

    @FXML
    private Pane winPane;

    public SimpleBooleanProperty restartProperty;
    public SimpleBooleanProperty quitProperty;

    public void manualInit() {
        restartProperty = new SimpleBooleanProperty(false);
        quitProperty = new SimpleBooleanProperty(false);
    }

    @FXML
    public void onRestartPressed() {
        restartProperty.setValue(true);
    }

    @FXML
    public void onQuitPressed() {
        quitProperty.set(true);
    }
}
