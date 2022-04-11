package ru.nsu.nikita.backlogic.field;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.tiles.Tile;
import ru.nsu.nikita.backlogic.tiles.TileType;
import static ru.nsu.nikita.backlogic.tiles.TileType.*;

public class Field {
    private int horizontalSize;
    private int verticalSize;

    private Tile[][] field;

    public Field(int horizontal, int vertical) {
        horizontalSize = horizontal;
        verticalSize = vertical;

        field = new Tile[verticalSize][horizontalSize];
        createField();
    }

    private void createField() {
        int x = 0;
        int y = 0;
        for (Tile[] row : field) {
            for (Tile tile : row) {
                tile = new Tile(x++, y, GRASS);
                setNeighbors(tile);
            }
            x %= horizontalSize;
            y++;
        }
    }

    private void setNeighbors(Tile tile) {
        int x = tile.getCoordinates().getX();
        int y = tile.getCoordinates().getY();

        if (x == 0) {
            tile.setLeftNeighbor(field[y][horizontalSize - 1].getCoordinates());
            tile.setRightNeighbor(field[y][x + 1].getCoordinates());
        } else if (x == horizontalSize - 1) {
            tile.setLeftNeighbor(field[y][x - 1].getCoordinates());
            tile.setRightNeighbor(field[y][0].getCoordinates());
        } else {
            tile.setLeftNeighbor(field[y][x - 1].getCoordinates());
            tile.setRightNeighbor(field[y][x + 1].getCoordinates());
        }

        if (y == 0) {
            tile.setUpperNeighbor(field[verticalSize - 1][x].getCoordinates());
            tile.setDownNeighbor(field[y + 1][x].getCoordinates());
        } else if (y == verticalSize - 1) {
            tile.setUpperNeighbor(field[y - 1][x].getCoordinates());
            tile.setDownNeighbor(field[0][x].getCoordinates());
        } else {
            tile.setUpperNeighbor(field[y - 1][x].getCoordinates());
            tile.setDownNeighbor(field[y + 1][x].getCoordinates());
        }
    }

    public Tile getTile(Coordinates coordinates) {
        return field[coordinates.getY()][coordinates.getY()];
    }

    public void changeTile(Coordinates coordinates, TileType newType) throws Exception {
        getTile(coordinates).setType(newType);
    }

    public void changeRow(int row, TileType newType) throws Exception {
        for (Tile tile : field[row]) {
            changeTile(tile.getCoordinates(), newType);
        }
    }

    public void changeColumn(int column, TileType newType) throws Exception {
        for (int i = 0; i < verticalSize; i++) {
            changeTile(field[i][column].getCoordinates(), newType);
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

    public Tile[][] getField() {
        return field;
    }
}
