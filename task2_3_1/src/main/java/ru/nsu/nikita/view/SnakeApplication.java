package ru.nsu.nikita.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.snake.Direction;
import ru.nsu.nikita.backlogic.snake.Snake;
import ru.nsu.nikita.backlogic.tiles.Tile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static ru.nsu.nikita.backlogic.snake.Direction.*;
import static ru.nsu.nikita.backlogic.snake.Direction.RIGHT;
import static ru.nsu.nikita.backlogic.tiles.TileType.GRASS;
import static ru.nsu.nikita.backlogic.tiles.TileType.OBSTACLE;

public class SnakeApplication extends Application {

    private Pane fieldPane;

    private Label scoreLabel;

    private Field fieldData;

    private Snake snake;
    private Rectangle snakeHead;

    private double currentX = 0.0;
    private double currentY = 0.0;
    private double tileHeight = 30;
    private double tileWidth = 30;
    private double padding = 5;

    private Group root;
    private Scene mainScene;

    private Property<Direction> direction = new SimpleObjectProperty<>(NONE);
    private final InputHandler inputHandler = new InputHandler();

    @Override
    public void start(Stage stage) throws IOException {
        root = new Group();
        mainScene = new Scene(root, 800, 800);
        stage.setTitle("Snake");
        stage.setScene(mainScene);

        initializeScene();
        initializeData();

        mainScene.setOnKeyPressed(inputHandler);
        mainScene.setOnKeyReleased(inputHandler);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        animationTimer.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void update() {
        updateSnake();
    }

    private void updateSnake() {
        if (inputHandler.getActiveKeys().contains(KeyCode.A)) {
            onLeftKeyPressed();
        } else if (inputHandler.getActiveKeys().contains(KeyCode.D)) {
            onRightKeyPressed();
        } else if (inputHandler.getActiveKeys().contains(KeyCode.S)) {
            onDownKeyPressed();
        } else if (inputHandler.getActiveKeys().contains(KeyCode.W)) {
            onUpKeyPressed();
        }

        snake.move(direction.getValue());

        fieldPane.getChildren().remove(snakeHead);
        snakeHead = new Rectangle(currentX + (tileWidth + padding) * snake.getHeadCoordinates().getX(),
                currentY + (tileHeight + padding) * snake.getHeadCoordinates().getY(),
                tileWidth,
                tileHeight);
        snakeHead.fillProperty().set(Color.ORANGE);
        fieldPane.getChildren().add(snakeHead);
    }

    public void initializeScene() {

        fieldPane = new Pane();
        fieldPane.prefHeight(600);
        fieldPane.prefWidth(600);

        scoreLabel = new Label("Score");

        root.getChildren().addAll(fieldPane, scoreLabel);
    }

    public void initializeData() {
        fieldData = new Field(10, 10);
        fieldData.surroundField();
        Tile snakeInit = fieldData.getFreeTiles().get(new Random().nextInt(fieldData.getFreeTiles().size()));

        snake = new Snake(
                snakeInit.getCoordinates().getX(),
                snakeInit.getCoordinates().getY(),
                fieldData);
        createField();
        createSnake();
    }

    private void createField() {

        for (List<Tile> row : fieldData.getField()) {
            for (Tile tile : row) {
                Rectangle newTile = new Rectangle(
                        currentX + (tileWidth + padding) * row.indexOf(tile),
                        currentY + (tileHeight + padding) * fieldData.getField().indexOf(row),
                        tileWidth,
                        tileHeight);
                newTile.fillProperty().set(Color.WHITESMOKE);
                if (tile.getType() == GRASS) {
                    newTile.fillProperty().set(Color.GREEN);
                } else if (tile.getType() == OBSTACLE) {
                    newTile.fillProperty().set(Color.DARKGRAY);
                }

                fieldPane.getChildren().add(newTile);
            }
        }
    }

    private void createSnake() {
        snakeHead = new Rectangle(
                currentX + (tileWidth + padding) * snake.getHeadCoordinates().getX(),
                currentY + (tileHeight + padding) * snake.getHeadCoordinates().getY(),
                tileWidth,
                tileHeight);
        snakeHead.fillProperty().set(Color.ORANGE);
        fieldPane.getChildren().add(snakeHead);
    }

    private void onUpKeyPressed() {
        direction.setValue(UP);
    }

    private void onDownKeyPressed() {
        direction.setValue(DOWN);
    }

    private void onLeftKeyPressed() {
        direction.setValue(LEFT);
    }

    private void onRightKeyPressed() {
        direction.setValue(RIGHT);
    }
}