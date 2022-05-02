package ru.nsu.nikita.backlogic.field;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.tiles.Tile;
import ru.nsu.nikita.backlogic.tiles.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ru.nsu.nikita.backlogic.tiles.TileType.*;

public class Field {
    private final int horizontalSize;
    private final int verticalSize;

    private final List<List<Tile>> field;

    public Field(int horizontal, int vertical) {
        horizontalSize = horizontal;
        verticalSize = vertical;

        field = new ArrayList<>();
        createField();
    }

    private void createField() {

        for (int y = 0; y < verticalSize; y++) {
            field.add(new ArrayList<>());
            for (int x = 0; x < horizontalSize; x++) {
                field.get(y).add(new Tile(x, y, GRASS));
            }
        }

        for (List<Tile> row : field) {
            for (Tile tile : row) {
                setNeighbors(tile);
            }
        }

    }

    private void setNeighbors(Tile tile) {
        int x = tile.getCoordinates().getX();
        int y = tile.getCoordinates().getY();

        if (x == 0) {
            tile.setLeftNeighbor(field.get(y).get(horizontalSize - 1).getCoordinates());
            tile.setRightNeighbor(field.get(y).get(x + 1).getCoordinates());
        } else if (x == horizontalSize - 1) {
            tile.setLeftNeighbor(field.get(y).get(x - 1).getCoordinates());
            tile.setRightNeighbor(field.get(y).get(0).getCoordinates());
        } else {
            tile.setLeftNeighbor(field.get(y).get(x - 1).getCoordinates());
            tile.setRightNeighbor(field.get(y).get(x + 1).getCoordinates());
        }

        if (y == 0) {
            tile.setUpperNeighbor(field.get(verticalSize - 1).get(x).getCoordinates());
            tile.setDownNeighbor(field.get(y + 1).get(x).getCoordinates());
        } else if (y == verticalSize - 1) {
            tile.setUpperNeighbor(field.get(y - 1).get(x).getCoordinates());
            tile.setDownNeighbor(field.get(0).get(x).getCoordinates());
        } else {
            tile.setUpperNeighbor(field.get(y - 1).get(x).getCoordinates());
            tile.setDownNeighbor(field.get(y + 1).get(x).getCoordinates());
        }
    }

    public Tile getTile(Coordinates coordinates) {
        return field.get(coordinates.getY()).get(coordinates.getX());
    }

    public void changeTile(Coordinates coordinates, TileType newType) {
        getTile(coordinates).setType(newType);
    }

    public void changeRow(int row, TileType newType) {
        for (Tile tile : field.get(row)) {
            changeTile(tile.getCoordinates(), newType);
        }
    }

    public void changeColumn(int column, TileType newType) {
        for (int i = 0; i < verticalSize; i++) {
            changeTile(field.get(i).get(column).getCoordinates(), newType);
        }
    }

    public void surroundField() {
        changeRow(0, OBSTACLE);
        changeRow(verticalSize - 1, OBSTACLE);
        changeColumn(0, OBSTACLE);
        changeColumn(horizontalSize - 1, OBSTACLE);
    }

    public List<Tile> getFreeTiles() {
        List<Tile> freeTiles = new ArrayList<>();
        for (List<Tile> row : field) {
            for (Tile tile : row) {
                if (tile.getType() == GRASS) {
                    freeTiles.add(tile);
                }
            }
        }

        return freeTiles;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public List<List<Tile>> getField() {
        return field;
    }
}
