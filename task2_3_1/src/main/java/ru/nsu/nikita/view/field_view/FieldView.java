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

    private FieldViewSettingsContainer settingsContainer;
    private Random randomizer = new Random();

    private List<TileView> tileList;

    private boolean tastySpawned;

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

    public void spawnTasty() {
        tastySpawned = true;

        Coordinates tastyPlace = settingsContainer.getField().getFreeTiles().get(randomizer.nextInt(settingsContainer.getField().getFreeTiles().size())).getCoordinates();
        settingsContainer.getField().getTile(tastyPlace).setHasFood(true);
        addTasty(tastyPlace);
    }

    private void addTasty(Coordinates coordinates) {
        tileList.get(coordinates.getY() * settingsContainer.getField().getHorizontalSize() + coordinates.getX()).fillProperty().set(Color.BLUE);
    }

    private void removeTasty(Coordinates coordinates) {
        tileList.get(coordinates.getY() * settingsContainer.getField().getHorizontalSize() + coordinates.getX()).fillProperty().set(Color.GREEN);
    }


}
