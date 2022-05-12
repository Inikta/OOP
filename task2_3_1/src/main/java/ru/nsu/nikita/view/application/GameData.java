package ru.nsu.nikita.view.application;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.view.field_view.FieldViewSettingsContainer;
import ru.nsu.nikita.view.snake_view.SnakeViewSettingsContainer;

public class GameData {
    private FieldViewSettingsContainer fieldViewSettingsContainer;
    private Integer currentScore;
    private Integer goalScore;
    private Coordinates initSnakeHeadCoordinates;
    private SnakeViewSettingsContainer snakeHeadViewSettingsContainer;
    private SnakeViewSettingsContainer snakeBodyViewSettingsContainer;


    public GameData(FieldViewSettingsContainer fieldViewSettingsContainer,
                    Integer currentScore,
                    Integer goalScore,
                    Coordinates initSnakeHeadCoordinates,
                    SnakeViewSettingsContainer snakeHeadViewSettingsContainer,
                    SnakeViewSettingsContainer snakeBodyViewSettingsContainer) {
        this.fieldViewSettingsContainer = fieldViewSettingsContainer;
        this.currentScore = currentScore;
        this.goalScore = goalScore;
        this.initSnakeHeadCoordinates = initSnakeHeadCoordinates;
        this.snakeHeadViewSettingsContainer = snakeHeadViewSettingsContainer;
        this.snakeBodyViewSettingsContainer = snakeBodyViewSettingsContainer;
    }

    public FieldViewSettingsContainer getFieldViewSettingsContainer() {
        return fieldViewSettingsContainer;
    }

    public void setFieldViewSettingsContainer(FieldViewSettingsContainer fieldViewSettingsContainer) {
        this.fieldViewSettingsContainer = fieldViewSettingsContainer;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getGoalScore() {
        return goalScore;
    }

    public void setGoalScore(Integer goalScore) {
        this.goalScore = goalScore;
    }

    public Coordinates getInitSnakeHeadCoordinates() {
        return initSnakeHeadCoordinates;
    }

    public void setInitSnakeHeadCoordinates(Coordinates initSnakeHeadCoordinates) {
        this.initSnakeHeadCoordinates = initSnakeHeadCoordinates;
    }

    public SnakeViewSettingsContainer getSnakeHeadViewSettingsContainer() {
        return snakeHeadViewSettingsContainer;
    }

    public void setSnakeHeadViewSettingsContainer(SnakeViewSettingsContainer snakeHeadViewSettingsContainer) {
        this.snakeHeadViewSettingsContainer = snakeHeadViewSettingsContainer;
    }

    public SnakeViewSettingsContainer getSnakeBodyViewSettingsContainer() {
        return snakeBodyViewSettingsContainer;
    }

    public void setSnakeBodyViewSettingsContainer(SnakeViewSettingsContainer snakeBodyViewSettingsContainer) {
        this.snakeBodyViewSettingsContainer = snakeBodyViewSettingsContainer;
    }
}
