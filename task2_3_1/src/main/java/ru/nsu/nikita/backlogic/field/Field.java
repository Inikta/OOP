package ru.nsu.nikita.backlogic.field;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.tiles.Tile;
import ru.nsu.nikita.backlogic.tiles.TileFactory;
import ru.nsu.nikita.backlogic.tiles.TileTypes;
import static ru.nsu.nikita.backlogic.tiles.TileTypes.*;

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
                tile.setType(GRASS);
                tile.getCoordinates().setXY(x++, y);
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

    public void changeTile(Coordinates coordinates, TileTypes newType) throws Exception {
        field[coordinates.getY()][coordinates.getX()].setType(newType);
    }

    public void changeRow(int row, TileTypes newType) throws Exception {
        for (Tile tile : field[row]) {
            changeTile(tile.getCoordinates(), newType);
        }
    }

    public void changeColumn(int column, TileTypes newType) throws Exception {
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
}
