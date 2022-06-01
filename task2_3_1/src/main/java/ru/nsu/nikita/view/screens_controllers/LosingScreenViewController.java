package ru.nsu.nikita.view.screens_controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

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

    public Integer goalScore;

    public SimpleBooleanProperty restartProperty;
    public SimpleBooleanProperty quitProperty;

    /**
     * Initialization of losing screen
     * @param currentScore score of player after losing
     * @param goalScore score required to win
     */
    public void manualInit(SimpleIntegerProperty currentScore, Integer goalScore) {
        this.currentScore = new SimpleIntegerProperty();
        this.currentScore.addListener((observable, oldValue, newValue) -> currentScore.set(newValue.intValue()));

        this.goalScore = goalScore;
        restartProperty = new SimpleBooleanProperty(false);
        quitProperty = new SimpleBooleanProperty(false);

        currentScoreLabel.setText(currentScore.getValue().toString());
        goalScoreLabel.setText(goalScore.toString());
    }

    @FXML
    public void onRestartPressed() {
        restartProperty.set(true);
    }

    @FXML
    public void onQuitPressed() {quitProperty.set(true);}
}
