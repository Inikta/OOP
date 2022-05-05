package ru.nsu.nikita.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.nikita.backlogic.field.Field;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

import ru.nsu.nikita.backlogic.snake.Direction;
import ru.nsu.nikita.backlogic.snake.SnakeHead;
import ru.nsu.nikita.view.field_view.FieldView;
import ru.nsu.nikita.view.snake_view.SnakeHeadView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SnakeApplication extends Application {

    private Scene mainScene;
    private Group root = new Group();



    //Game part
    private Label scoreLabel;
    private Label scoreCounter;

    private FieldView fieldView;
    private SnakeHeadView snakeHead;

    private AnimationTimer animationTimer;

    private SnakeHead snakeHeadData;
    private Field fieldData;

    private Set<KeyCode> activeKeys = new HashSet<>();
    //private InputHandler inputHandler = new InputHandler();
    private EventHandler<KeyEvent> inputHandler;
    private Direction currentDirection = NONE;
    private Direction lastDirection = NONE;

    @Override
    public void start(Stage stage) throws IOException {

        //Scene Initialization
        mainScene = new Scene(root, 800, 800);
        stage.setScene(mainScene);
        //Game loop


        animationTimer.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeSettings() {

    }

    private void initializeGame() {
        fieldData = new Field(10, 10);
        //fieldData.surroundField();
        snakeHeadData = new SnakeHead(
                fieldData.getFreeTiles().get(new Random().nextInt(fieldData.getFreeTiles().size())).getCoordinates(),
                fieldData);

        fieldView = new FieldView(fieldData, 0, 0, 5, 30, 30);
        snakeHead = new SnakeHeadView(snakeHeadData, 0, 0, fieldView.getPad(), fieldView.getTileWidth(), fieldView.getTileHeight(), Color.ORANGE);

        root.getChildren().addAll(fieldView, snakeHead);
    }

    private void initializeGameLoop() {
        animationTimer = new AnimationTimer() {
            int workFrame = 10;
            int spawnRate = 1000;

            @Override
            public void handle(long now) {
                inputManager(now, workFrame);
                fieldView.update(snakeHeadData, now, spawnRate);
            }
        };
    }

    private void initializeEventHandler() {
        inputHandler = event -> {
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

        mainScene.setOnKeyPressed(inputHandler);
        mainScene.setOnKeyReleased(inputHandler);
    }

    private void inputManager(double now, int workFrame) {
        switch (currentDirection) {
            case LEFT -> snakeHeadData.move(LEFT);
            case RIGHT -> snakeHeadData.move(RIGHT);
            case UP -> snakeHeadData.move(UP);
            case DOWN -> snakeHeadData.move(DOWN);
            default -> snakeHeadData.move(lastDirection);
        }

        snakeHead.update(snakeHeadData);
    }
}