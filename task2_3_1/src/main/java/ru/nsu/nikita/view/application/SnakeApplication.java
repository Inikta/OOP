package ru.nsu.nikita.view.application;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    public BooleanProperty surroundFieldProperty = new SimpleBooleanProperty();
    public BooleanProperty randomWallsProperty = new SimpleBooleanProperty();
    public BooleanProperty randomFruitsProperty = new SimpleBooleanProperty();
    public BooleanProperty infiniteGameProperty = new SimpleBooleanProperty();
    public BooleanProperty lethalSelfCrashProperty = new SimpleBooleanProperty();
    public BooleanProperty difficultyIncreaseProperty = new SimpleBooleanProperty();

    public IntegerProperty xSizeProperty = new SimpleIntegerProperty();
    public IntegerProperty ySizeProperty = new SimpleIntegerProperty();
    public IntegerProperty goalProperty = new SimpleIntegerProperty();
    public IntegerProperty initDifficultyProperty = new SimpleIntegerProperty();


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader prefaceLoader = new FXMLLoader(getClass().getResource("PrefaceView.fxml"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        //Set preface scene as the initial scene and show it
        prefaceScene = new Scene(prefaceLoader.load());
        mainScene = prefaceScene;

        PrefaceView prefaceView = prefaceLoader.getController();

        stage.setScene(mainScene);
        stage.setScene(mainScene);
        stage.show();

        //Add event listener for parameters of the game.
        //Initialize and start the game, if all necessary are set and Start pressed.
        prefaceView.readyProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                try {
                    initializeGame(stage, prefaceView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Add listeners for all game settings interface controls.
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

    /**
     * Create field according to settings from preface.
     * @return field
     */
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

    /**
     * Create snake according to settings from preface.
     * Sets initial location of the snake as a random GRASS tile.
     * @param field field of this snake
     * @return snake head
     */
    private SnakeHead snakeMaker(Field field) {
        SnakeHead snakeHead = new SnakeHead(
                field.getFreeTiles().get(new Random().nextInt(field.getFreeTiles().size())).getCoordinates(),
                field);

        if (lethalSelfCrashProperty.get()) {
            snakeHead.setSelfCrash(true);
        }

        return snakeHead;

    }

    /**
     * Calculate horizontal size for tile according to the application window size and field sizes.
     * @param stage current window
     * @param field field
     * @return horizontal size
     */
    private double calculateTileXSize(Stage stage, Field field) {
        return (stage.getWidth() * 0.9 - (field.getHorizontalSize() - 1)) / field.getHorizontalSize();
    }

    /**
     * Calculate vertical size for tile according to the application window size and field sizes.
     * @param stage current window
     * @param field field
     * @return vertical size
     */
    private double calculateTileYSize(Stage stage, Field field) {
        return (stage.getHeight() * 0.95 - (field.getVerticalSize() - 1)) / field.getVerticalSize();
    }

    /**
     * Initialize and start the game with parameters assigned in preface.
     * @param stage main application window
     * @param prefaceView controller of the preface, used only for event listener in case of game restart.
     * @throws IOException if an error occurs during loading FXML markup
     */
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

        gameView.difficultyIncreaseProperty.set(difficultyIncreaseProperty.get());

        gameView.restartProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                prefaceView.onRestart();
                stage.setScene(prefaceScene);
                stage.show();
            }
        });

        gameView.winQuitProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stage.close();
            }
        });

        gameView.loseQuitProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stage.close();
            }
        });

        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(gameScene);
        stage.show();
    }
}