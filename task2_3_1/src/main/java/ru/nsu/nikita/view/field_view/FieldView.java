package ru.nsu.nikita.view.field_view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.snake.SnakeHead;
import ru.nsu.nikita.backlogic.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldView extends Pane {

    private final FieldViewSettingsContainer settingsContainer;
    private final Random randomizer = new Random();

    private final List<TileView> tileList;

    private boolean tastySpawned;

    /**
     * FieldView constructor with settings.
     *
     * @param settingsContainer settings container
     */
    public FieldView(FieldViewSettingsContainer settingsContainer) {
        tastySpawned = false;
        this.settingsContainer = settingsContainer;
        tileList = new ArrayList<>();
        for (List<Tile> row : settingsContainer.getField().getFieldMatrix()) {
            for (Tile tile : row) {
                tileList.add(
                        new TileView(
                                tile.getCoordinates(),
                                tile.getType(),
                                settingsContainer));
            }
        }
        this.getChildren().addAll(tileList);
    }

    /**
     * Update field state
     *
     * @param snakeHead snake head for the snake head position
     * @param now       current timestamp of animation timer
     * @param spawnRate number responsible for food spawning rate
     */
    public void update(SnakeHead snakeHead, double now, int spawnRate) {

        if (settingsContainer.getField().getTile(snakeHead.getCoordinates()).isHasFood()) {
            settingsContainer.getField().getTile(snakeHead.getCoordinates()).setHasFood(false);
            removeTasty(snakeHead.getCoordinates());
            tastySpawned = false;
        }

        if (settingsContainer.getField().isRandomTasties()) {
            if (now % spawnRate == 0) {
                spawnTasty();
            }
        } else {
            if (!tastySpawned) {
                spawnTasty();
            }
        }
    }

    /**
     * Spawn food on random free tile.
     */
    public void spawnTasty() {
        tastySpawned = true;

        Coordinates tastyPlace = settingsContainer.getField().getFreeTiles().get(randomizer.nextInt(settingsContainer.getField().getFreeTiles().size())).getCoordinates();
        settingsContainer.getField().getTile(tastyPlace).setHasFood(true);
        addTasty(tastyPlace);
    }

    /**
     * Draw food on coordinates on the field
     *
     * @param coordinates coordinates of the tile
     */
    private void addTasty(Coordinates coordinates) {
        tileList.get(coordinates.getY() * settingsContainer.getField().getHorizontalSize() + coordinates.getX()).fillProperty().set(Color.BLUE);
    }

    /**
     * Remove food on coordinates on the field
     *
     * @param coordinates coordinates of the tile
     */
    private void removeTasty(Coordinates coordinates) {
        tileList.get(coordinates.getY() * settingsContainer.getField().getHorizontalSize() + coordinates.getX()).fillProperty().set(Color.GREEN);
    }
}
