package ru.nsu.nikita.view.application;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.snake.SnakeHead;
import ru.nsu.nikita.view.field_view.FieldViewSettingsContainer;
import ru.nsu.nikita.view.snake_view.SnakeViewSettingsContainer;

import java.io.IOException;
import java.util.Random;

public class SnakeApplication extends Application {

    private Scene mainScene;
    private Scene prefaceScene;
    private Scene gameScene;

    private BooleanProperty restartProperty = new SimpleBooleanProperty();

    private BooleanProperty surroundFieldProperty = new SimpleBooleanProperty();
    private BooleanProperty randomWallsProperty = new SimpleBooleanProperty();
    private BooleanProperty randomFruitsProperty = new SimpleBooleanProperty();
    private BooleanProperty infiniteGameProperty = new SimpleBooleanProperty();
    private BooleanProperty lethalSelfCrashProperty = new SimpleBooleanProperty();
    private BooleanProperty difficultyIncreaseProperty = new SimpleBooleanProperty();

    private IntegerProperty xSizeProperty = new SimpleIntegerProperty();
    private IntegerProperty ySizeProperty = new SimpleIntegerProperty();
    private IntegerProperty goalProperty = new SimpleIntegerProperty();
    private IntegerProperty initDifficultyProperty = new SimpleIntegerProperty();


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader prefaceLoader = new FXMLLoader(getClass().getResource("PrefaceView.fxml"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        prefaceScene = new Scene(prefaceLoader.load());
        mainScene = prefaceScene;

        PrefaceView prefaceView = prefaceLoader.getController();

        stage.setScene(mainScene);

        stage.setScene(mainScene);
        stage.show();

        prefaceView.readyProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                try {
                    initializeGame(stage, prefaceView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        prefaceView.xSizeProperty.addListener((observable, oldValue, newValue) -> xSizeProperty.setValue(newValue));
        prefaceView.ySizeProperty.addListener((observable, oldValue, newValue) -> ySizeProperty.setValue(newValue));
        prefaceView.goalProperty.addListener((observable, oldValue, newValue) -> goalProperty.setValue(newValue));
        prefaceView.initDifficultyProperty.addListener((observable, oldValue, newValue) -> initDifficultyProperty.setValue(newValue));

        prefaceView.surroundFieldProperty.addListener((observable, oldValue, newValue) -> surroundFieldProperty.set(newValue));
        prefaceView.randomWallsProperty.addListener((observable, oldValue, newValue) -> randomWallsProperty.set(newValue));
        prefaceView.randomFruitsProperty.addListener((observable, oldValue, newValue) -> randomFruitsProperty.set(newValue));
        prefaceView.infiniteGameProperty.addListener((observable, oldValue, newValue) -> infiniteGameProperty.set(newValue));
        prefaceView.difficultyIncreaseProperty.addListener((observable, oldValue, newValue) -> difficultyIncreaseProperty.set(newValue));
        prefaceView.lethalSelfCrashProperty.addListener((observable, oldValue, newValue) -> lethalSelfCrashProperty.set(newValue));
    }

    public static void main(String[] args) {
        launch();
    }

    private Field fieldMaker() {
        Field field = new Field(xSizeProperty.get(), ySizeProperty.get());

        if (surroundFieldProperty.get()) {
            field.surroundField();
        }

        if (randomWallsProperty.get()) {
            field.makeRandomWalls();
        }

        if (randomFruitsProperty.get()) {
            field.setRandomTasties(true);
        }

        if (infiniteGameProperty.get()) {
            goalProperty.setValue(Integer.MAX_VALUE);
        }

        return field;
    }

    private SnakeHead snakeMaker(Field field) {
        SnakeHead snakeHead = new SnakeHead(
                field.getFreeTiles().get(new Random().nextInt(field.getFreeTiles().size())).getCoordinates(),
                field);

        if (lethalSelfCrashProperty.get()) {
            snakeHead.setSelfCrash(true);
        }

        return snakeHead;

    }

    private double calculateTileXSize(Stage stage, Field field) {
        return (stage.getWidth() * 0.91 - (field.getHorizontalSize() - 1)) / field.getHorizontalSize();
    }

    private double calculateTileYSize(Stage stage, Field field) {
        return (stage.getHeight() * 0.99 - (field.getVerticalSize() - 1)) / field.getVerticalSize();
    }

    private void initializeGame(Stage stage, PrefaceView prefaceView) throws IOException {
        Field field = fieldMaker();
        SnakeHead snakeHead = snakeMaker(field);
        GameData gameData = new GameData(
                new FieldViewSettingsContainer(
                        field,
                        stage.getWidth() / 100, stage.getHeight() / 100,
                        calculateTileXSize(stage, field), calculateTileYSize(stage, field), 1),
                0,
                goalProperty.getValue(),
                snakeHead,
                new SnakeViewSettingsContainer(
                        stage.getWidth() / 100, stage.getHeight() / 100,
                        calculateTileXSize(stage, field), calculateTileYSize(stage, field),
                        1, Color.ORANGE),
                new SnakeViewSettingsContainer(
                        stage.getWidth() / 100, stage.getHeight() / 100,
                        calculateTileXSize(stage, field), calculateTileYSize(stage, field),
                        1, Color.DARKORANGE),
                initDifficultyProperty.getValue());

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));

        gameScene = null;
        try {
            gameScene = new Scene(gameLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameView gameView = gameLoader.getController();
        gameView.manualInitialization(gameData);

        gameView.getRoot().setPrefWidth(stage.getWidth() * 0.92);
        gameView.getRoot().setPrefHeight(stage.getHeight());

        gameView.setDifficultyIncreaseProperty(difficultyIncreaseProperty.get());

        gameView.restartPropertyProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                prefaceView.onRestart();
                stage.setScene(prefaceScene);
                stage.show();
            }
        });

        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(gameScene);
        stage.show();
    }
}