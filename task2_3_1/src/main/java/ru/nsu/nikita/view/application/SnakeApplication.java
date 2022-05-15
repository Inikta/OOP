package ru.nsu.nikita.view.application;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.view.field_view.FieldViewSettingsContainer;
import ru.nsu.nikita.view.snake_view.SnakeViewSettingsContainer;

import java.io.IOException;
import java.util.Random;

public class SnakeApplication extends Application {

    private Scene mainScene;
    private Scene prefaceScene;
    private Scene gameScene;

    private BooleanProperty restartProperty = new SimpleBooleanProperty();

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader prefaceLoader = new FXMLLoader(getClass().getResource("PrefaceView.fxml"));
        prefaceScene = new Scene(prefaceLoader.load(), 1024, 800);
        mainScene = prefaceScene;

        PrefaceView prefaceView = prefaceLoader.getController();

        stage.setScene(mainScene);

        stage.setScene(mainScene);
        stage.show();

        prefaceView.readyProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                initializeGame(stage, prefaceView);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeGame(Stage stage, PrefaceView prefaceView) {
        Field field = new Field(prefaceView.getxSizeProperty(), prefaceView.getySizeProperty());
        GameData gameData = new GameData(
                new FieldViewSettingsContainer(
                        field,
                        0, 0,
                        30, 30, 1),
                0,
                prefaceView.getGoalProperty(),
                field.getFreeTiles().get(new Random().nextInt(field.getFreeTiles().size())).getCoordinates(),
                new SnakeViewSettingsContainer(
                        0, 0,
                        30, 30,
                        1, Color.ORANGE),
                new SnakeViewSettingsContainer(
                        0, 0,
                        30, 30,
                        1, Color.DARKORANGE));

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));

        gameScene = null;
        try {
            gameScene = new Scene(gameLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameView gameView = gameLoader.getController();
        gameView.manualInitialization(gameData);

        gameView.restartPropertyProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                prefaceView.onRestart();
                stage.setScene(prefaceScene);
                stage.show();
            }
        });

        stage.setScene(gameScene);
        stage.show();
    }

    /*
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
    }*/
}