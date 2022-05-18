package ru.nsu.nikita.backlogic.field;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.tiles.Tile;
import ru.nsu.nikita.backlogic.tiles.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.nsu.nikita.backlogic.tiles.TileType.*;

public class Field {
    private final int horizontalSize;
    private final int verticalSize;
    private boolean randomTasties;

    private final List<List<Tile>> fieldMatrix;

    public Field(int horizontal, int vertical) {
        horizontalSize = horizontal;
        verticalSize = vertical;
        randomTasties = false;

        fieldMatrix = new ArrayList<>();
        createField();
    }

    private void createField() {

        for (int y = 0; y < verticalSize; y++) {
            fieldMatrix.add(new ArrayList<>());
            for (int x = 0; x < horizontalSize; x++) {
                fieldMatrix.get(y).add(new Tile(x, y, GRASS));
            }
        }

        for (List<Tile> row : fieldMatrix) {
            for (Tile tile : row) {
                setNeighbors(tile);
            }
        }

    }

    private void setNeighbors(Tile tile) {
        int x = tile.getCoordinates().getX();
        int y = tile.getCoordinates().getY();

        if (x == 0) {
            if (horizontalSize == 1) {
                tile.setRightNeighbor(fieldMatrix.get(y).get(x).getCoordinates());
                tile.setLeftNeighbor(fieldMatrix.get(y).get(x).getCoordinates());
            } else {
                tile.setRightNeighbor(fieldMatrix.get(y).get(x + 1).getCoordinates());
                tile.setLeftNeighbor(fieldMatrix.get(y).get(horizontalSize - 1).getCoordinates());
            }

        } else if (x == horizontalSize - 1) {
            tile.setLeftNeighbor(fieldMatrix.get(y).get(x - 1).getCoordinates());
            tile.setRightNeighbor(fieldMatrix.get(y).get(0).getCoordinates());
        } else {
            tile.setLeftNeighbor(fieldMatrix.get(y).get(x - 1).getCoordinates());
            tile.setRightNeighbor(fieldMatrix.get(y).get(x + 1).getCoordinates());
        }

        if (y == 0) {
            if (verticalSize == 1) {
                tile.setUpperNeighbor(fieldMatrix.get(y).get(x).getCoordinates());
                tile.setDownNeighbor(fieldMatrix.get(y).get(x).getCoordinates());
            } else {
                tile.setUpperNeighbor(fieldMatrix.get(verticalSize - 1).get(x).getCoordinates());
                tile.setDownNeighbor(fieldMatrix.get(y + 1).get(x).getCoordinates());
            }
        } else if (y == verticalSize - 1) {
            tile.setUpperNeighbor(fieldMatrix.get(y - 1).get(x).getCoordinates());
            tile.setDownNeighbor(fieldMatrix.get(0).get(x).getCoordinates());
        } else {
            tile.setUpperNeighbor(fieldMatrix.get(y - 1).get(x).getCoordinates());
            tile.setDownNeighbor(fieldMatrix.get(y + 1).get(x).getCoordinates());
        }
    }

    public Tile getTile(Coordinates coordinates) {
        return fieldMatrix.get(coordinates.getY()).get(coordinates.getX());
    }

    public void changeTile(Coordinates coordinates, TileType newType) {
        getTile(coordinates).setType(newType);
    }

    public void changeRow(int row, TileType newType) {
        for (Tile tile : fieldMatrix.get(row)) {
            changeTile(tile.getCoordinates(), newType);
        }
    }

    public void changeColumn(int column, TileType newType) {
        for (int i = 0; i < verticalSize; i++) {
            changeTile(fieldMatrix.get(i).get(column).getCoordinates(), newType);
        }
    }

    public void surroundField() {
        changeRow(0, OBSTACLE);
        changeRow(verticalSize - 1, OBSTACLE);
        changeColumn(0, OBSTACLE);
        changeColumn(horizontalSize - 1, OBSTACLE);
    }

    public void makeRandomWalls() {
        Random random = new Random();

        for (List<Tile> row : fieldMatrix) {
            for (Tile tile : row) {
                int num = random.nextInt(20);
                if (num % 15 == 0) {
                    tile.setType(OBSTACLE);
                }
            }
        }
    }

    public List<Tile> getFreeTiles() {
        List<Tile> freeTiles = new ArrayList<>();
        for (List<Tile> row : fieldMatrix) {
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

    public List<List<Tile>> getFieldMatrix() {
        return fieldMatrix;
    }

    public boolean isRandomTasties() {
        return randomTasties;
    }

    public void setRandomTasties(boolean randomTasties) {
        this.randomTasties = randomTasties;
    }
}
