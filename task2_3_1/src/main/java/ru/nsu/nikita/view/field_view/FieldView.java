package ru.nsu.nikita.view.field_view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.snake.SnakeHead;
import ru.nsu.nikita.backlogic.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldView extends Pane {

    private Field field;
    private double tileWidth;
    private double tileHeight;
    private double padding;
    private Random randomizer = new Random();

    private List<TileView> tileList;

    public FieldView(Field field, double x, double y, double padding, double tileWidth, double tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.padding = padding;
        this.field = field;
        tileList = new ArrayList<>();
        for (List<Tile> row : field.getField()) {
            for (Tile tile : row) {
                tileList.add(new TileView(tile.getCoordinates(), x, y, padding, tileWidth, tileHeight, tile.getType()));
            }
        }
        this.getChildren().addAll(tileList);
    }

    public void update(SnakeHead snakeHead, double now, int spawnRate) {
        if (field.getTile(snakeHead.getCoordinates()).isHasFood()) {
            field.getTile(snakeHead.getCoordinates()).setHasFood(false);
            removeTasty(snakeHead.getCoordinates());
        }
        if (now % spawnRate == 0) {
            spawnTasty();
        }
    }

    public void spawnTasty() {
        Coordinates tastyPlace = field.getFreeTiles().get(randomizer.nextInt(field.getFreeTiles().size())).getCoordinates();
        field.getTile(tastyPlace).setHasFood(true);
        addTasty(tastyPlace);
    }

    private void addTasty(Coordinates coordinates) {
        tileList.get(coordinates.getY() * field.getHorizontalSize() + coordinates.getX()).fillProperty().set(Color.BLUE);
    }

    private void removeTasty(Coordinates coordinates) {
        tileList.get(coordinates.getY() * field.getHorizontalSize() + coordinates.getX()).fillProperty().set(Color.GREEN);
    }

    public Field getField() {
        return field;
    }

    public double getTileWidth() {
        return tileWidth;
    }

    public double getTileHeight() {
        return tileHeight;
    }

    public double getPad() {
        return padding;
    }
}
