package ru.nsu.nikita.view.screens_controllers;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class LosingScreenViewController {
    @FXML
    private Button quitButton;

    @FXML
    private Button restartButton;

    @FXML
    private Label goalScoreLabel;

    @FXML
    private Label currentScoreLabel;

    @FXML
    private Pane losePane;

    private SimpleIntegerProperty currentScore;

    private Integer goalScore;

    private SimpleBooleanProperty restartProperty;

    public void manualInit(SimpleIntegerProperty currentScore, Integer goalScore) {
        this.currentScore = new SimpleIntegerProperty();
        this.currentScore.addListener((observable, oldValue, newValue) -> currentScore.set(newValue.intValue()));

        this.goalScore = goalScore;
        restartProperty = new SimpleBooleanProperty(false);

        currentScoreLabel.setText(currentScore.getValue().toString());
        goalScoreLabel.setText(goalScore.toString());
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

    public int getCurrentScore() {
        return currentScore.get();
    }

    public SimpleIntegerProperty currentScoreProperty() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore.set(currentScore);
    }
}
