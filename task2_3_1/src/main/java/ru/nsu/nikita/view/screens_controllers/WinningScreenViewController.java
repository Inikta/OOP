package ru.nsu.nikita.view.screens_controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    private SimpleBooleanProperty restartProperty;

    @FXML
    public void initialize() {
        restartProperty = new SimpleBooleanProperty(false);
    }

    @FXML
    public void onRestartPressed() {
        restartProperty.set(true);
    }

    @FXML
    public void onQuitPressed() {
    }

    public boolean isRestartProperty() {
        return restartProperty.get();
    }

    public SimpleBooleanProperty restartPropertyProperty() {
        return restartProperty;
    }

    public void setRestartProperty(boolean restartProperty) {
        this.restartProperty.set(restartProperty);
    }
}
