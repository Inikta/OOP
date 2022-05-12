package ru.nsu.nikita.view.application;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import ru.nsu.nikita.backlogic.snake.Direction;
import ru.nsu.nikita.backlogic.snake.SnakeHead;
import ru.nsu.nikita.view.field_view.FieldView;
import ru.nsu.nikita.view.snake_view.SnakeHeadView;

import java.util.HashSet;
import java.util.Set;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

public class GameView {

    @FXML
    private Pane root;

    @FXML
    private Pane fieldPane;

    @FXML
    private Label currentScoreLabel;

    @FXML
    private Label goalScoreLabel;

    @FXML
    private Button restartButton;

    private SnakeHeadView snakeHeadView;
    private FieldView fieldView;

    private GameData gameData;

    private Set<KeyCode> activeKeys = new HashSet<>();
    private Direction currentDirection = NONE;
    private Direction lastDirection = NONE;
    private AnimationTimer animationTimer;


    public void manualInitialization(GameData gameData) {
        this.gameData = gameData;
        snakeHeadView = new SnakeHeadView(
                new SnakeHead(gameData.getInitSnakeHeadCoordinates(), gameData.getFieldViewSettingsContainer().getField()),
                gameData.getSnakeHeadViewSettingsContainer(),
                gameData.getSnakeBodyViewSettingsContainer());
        fieldView = new FieldView(gameData.getFieldViewSettingsContainer());

        fieldPane.getChildren().add(fieldView);
        fieldPane.getChildren().add(snakeHeadView);

        initializeEventHandler();
        initializeGameLoop();

        animationTimer.start();
    }

    public void update(long now) {
        int workFrame = 10;
        int spawnRate = 1000;

        inputUpdate();
        fieldView.update(snakeHeadView.getSnakeHead(), now, spawnRate);
    }

    private void updateCurrentScore() {

    }

    private void initializeGameLoop() {
        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                update(now);
            }
        };
    }

    private void inputUpdate() {
        switch (currentDirection) {
            case LEFT -> snakeHeadView.getSnakeHead().move(LEFT);
            case RIGHT -> snakeHeadView.getSnakeHead().move(RIGHT);
            case UP -> snakeHeadView.getSnakeHead().move(UP);
            case DOWN -> snakeHeadView.getSnakeHead().move(DOWN);
            default -> snakeHeadView.getSnakeHead().move(lastDirection);
        }

        snakeHeadView.update();
    }

    private void initializeEventHandler() {
        EventHandler<KeyEvent> inputHandler = event -> {
            if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
                activeKeys.add(event.getCode());
                if (activeKeys.contains(KeyCode.A)) {
                    currentDirection = LEFT;
                    lastDirection = LEFT;
                } else if (activeKeys.contains(KeyCode.D)) {
                    currentDirection = RIGHT;
                    lastDirection = RIGHT;
                } else if (activeKeys.contains(KeyCode.W)) {
                    currentDirection = UP;
                    lastDirection = UP;
                } else if (activeKeys.contains(KeyCode.S)) {
                    currentDirection = DOWN;
                    lastDirection = DOWN;
                } else {
                    currentDirection = lastDirection;
                }
            } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
                activeKeys.remove(event.getCode());
            } else {
                currentDirection = lastDirection;
            }
        };

        root.getScene().setOnKeyPressed(inputHandler);
        root.getScene().setOnKeyReleased(inputHandler);
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Pane getFieldPane() {
        return fieldPane;
    }

    public void setFieldPane(Pane fieldPane) {
        this.fieldPane = fieldPane;
    }

    public Label getCurrentScoreLabel() {
        return currentScoreLabel;
    }

    public void setCurrentScoreLabel(Label currentScoreLabel) {
        this.currentScoreLabel = currentScoreLabel;
    }

    public Label getGoalScoreLabel() {
        return goalScoreLabel;
    }

    public void setGoalScoreLabel(Label goalScoreLabel) {
        this.goalScoreLabel = goalScoreLabel;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public void setRestartButton(Button restartButton) {
        this.restartButton = restartButton;
    }

    public SnakeHeadView getSnakeHeadView() {
        return snakeHeadView;
    }

    public void setSnakeHeadView(SnakeHeadView snakeHeadView) {
        this.snakeHeadView = snakeHeadView;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    /*public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }*/
}
