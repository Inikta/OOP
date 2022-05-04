package ru.nsu.nikita.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.nikita.backlogic.field.Field;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

import ru.nsu.nikita.backlogic.snake.Direction;
import ru.nsu.nikita.backlogic.snake.Snake;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class SnakeApplication extends Application {

    private Scene mainScene;
    private Group root = new Group();

    private Label scoreLabel;
    private Label scoreCounter;

    private FieldView fieldView;
    private SnakeRectangle snakeHead;

    private AnimationTimer animationTimer;

    private Snake snakeData;
    private Field fieldData;

    private HashSet<String> input;
    private InputHandler inputHandler = new InputHandler();
    private Direction lastDirection = NONE;

    @Override
    public void start(Stage stage) throws IOException {
        //Data Initialization
        fieldData = new Field(10, 10);
        //fieldData.surroundField();
        snakeData = new Snake(
                fieldData.getFreeTiles().get(new Random().nextInt(fieldData.getFreeTiles().size())).getCoordinates().getX(),
                fieldData.getFreeTiles().get(new Random().nextInt(fieldData.getFreeTiles().size())).getCoordinates().getY(),
                fieldData);

        //Scene Initialization
        mainScene = new Scene(root, 800, 800);
        fieldView = new FieldView(fieldData, 0, 0, 5, 30, 30);
        snakeHead = new SnakeRectangle(snakeData, 0, 0, fieldView.getPad(), fieldView.getTileWidth(), fieldView.getTileHeight(), Color.ORANGE);

        root.getChildren().addAll(fieldView, snakeHead);
        stage.setScene(mainScene);

        //Key events listener
        //prepareActionHandlers();
        mainScene.setOnKeyPressed(inputHandler);
        mainScene.setOnKeyReleased(inputHandler);

        //Game loop
        animationTimer = new AnimationTimer() {
            int workFrame = 10;
            int frame = 0;
            int spawnRate = 1000;

            @Override
            public void handle(long now) {
                inputManager(now, workFrame);
                fieldView.update(snakeData, now, spawnRate);
            }
        };

        animationTimer.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void inputManager(double now, int workFrame) {
        if (inputHandler.getActiveKeys().contains(KeyCode.A)) {
            snakeData.move(LEFT);
            lastDirection = LEFT;
        } else if (inputHandler.getActiveKeys().contains(KeyCode.D)) {
            snakeData.move(RIGHT);
            lastDirection = RIGHT;
        } else if (inputHandler.getActiveKeys().contains(KeyCode.W)) {
            snakeData.move(UP);
            lastDirection = UP;
        } else if (inputHandler.getActiveKeys().contains(KeyCode.S)) {
            snakeData.move(DOWN);
            lastDirection = DOWN;
        } else {
            snakeData.move(lastDirection);
        }

        if (now % workFrame == 0) {
            snakeHead.update(snakeData);
        }
    }
}