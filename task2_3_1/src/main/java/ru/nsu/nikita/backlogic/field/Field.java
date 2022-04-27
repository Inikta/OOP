package ru.nsu.nikita.backlogic.field;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.tiles.Tile;
import ru.nsu.nikita.backlogic.tiles.TileType;

import java.util.ArrayList;
import java.util.List;

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

        for (int x = 0; x < verticalSize; x++) {
            field.add(new ArrayList<>());
            for (int y = 0; y < horizontalSize; y++) {
                field.get(x).add(new Tile(x, y, GRASS));
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
        return field.get(coordinates.getY()).get(coordinates.getY());
    }

    public void changeTile(Coordinates coordinates, TileType newType) throws Exception {
        getTile(coordinates).setType(newType);
    }

    public void changeRow(int row, TileType newType) throws Exception {
        for (Tile tile : field.get(row)) {
            changeTile(tile.getCoordinates(), newType);
        }
    }

    public void changeColumn(int column, TileType newType) throws Exception {
        for (int i = 0; i < verticalSize; i++) {
            changeTile(field.get(i).get(column).getCoordinates(), newType);
        }
    }

    public void surroundField() throws Exception {
        changeRow(0, OBSTACLE);
        changeRow(verticalSize - 1, OBSTACLE);
        changeColumn(0, OBSTACLE);
        changeColumn(horizontalSize - 1, OBSTACLE);
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
