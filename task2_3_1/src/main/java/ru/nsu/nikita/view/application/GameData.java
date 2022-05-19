package ru.nsu.nikita.view.application;

import ru.nsu.nikita.backlogic.snake.SnakeHead;
import ru.nsu.nikita.view.field_view.FieldViewSettingsContainer;
import ru.nsu.nikita.view.snake_view.SnakeViewSettingsContainer;

public class GameData {
    private FieldViewSettingsContainer fieldViewSettingsContainer;
    private Integer currentScore;
    private Integer goalScore;
    private SnakeHead snakeHead;
    private SnakeViewSettingsContainer snakeHeadViewSettingsContainer;
    private SnakeViewSettingsContainer snakeBodyViewSettingsContainer;
    private Integer initDifficulty;

    /**
     * Data structure with all settings needed for game initialization.
     * @param fieldViewSettingsContainer view settings for field
     * @param currentScore initial score
     * @param goalScore winning score
     * @param snakeHead snake head
     * @param snakeHeadViewSettingsContainer view settings for snake head
     * @param snakeBodyViewSettingsContainer view settings for snake body
     * @param initDifficulty initial difficulty parameter
     */
    public GameData(FieldViewSettingsContainer fieldViewSettingsContainer,
                    Integer currentScore,
                    Integer goalScore,
                    SnakeHead snakeHead,
                    SnakeViewSettingsContainer snakeHeadViewSettingsContainer,
                    SnakeViewSettingsContainer snakeBodyViewSettingsContainer,
                    Integer initDifficulty) {
        this.fieldViewSettingsContainer = fieldViewSettingsContainer;
        this.currentScore = currentScore;
        this.goalScore = goalScore;
        this.snakeHead = snakeHead;
        this.snakeHeadViewSettingsContainer = snakeHeadViewSettingsContainer;
        this.snakeBodyViewSettingsContainer = snakeBodyViewSettingsContainer;
        this.initDifficulty = initDifficulty;
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

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
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

    public Integer getInitDifficulty() {
        return initDifficulty;
    }

    public void setInitDifficulty(Integer initDifficulty) {
        this.initDifficulty = initDifficulty;
    }
}
