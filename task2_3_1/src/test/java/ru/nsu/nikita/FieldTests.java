package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.Tile;

import static ru.nsu.nikita.backlogic.tiles.TileType.*;

public class FieldTests {

    Field field;

    public void initF(int sizeX, int sizeY) {
        field = new Field(sizeX, sizeY);
    }

    @Test
    public void getTileTest() {
        initF(10, 10);
        Coordinates coordinates = new Coordinates(5, 5);
        Tile tile = field.getFieldMatrix().get(coordinates.getY()).get(coordinates.getX());
        Assertions.assertEquals(tile, field.getTile(coordinates));

        coordinates = new Coordinates(10, 5);
        Coordinates finalCoordinates = coordinates;
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> field.getTile(finalCoordinates));
    }

    @Test
    public void changeTileTest() {
        initF(10, 10);
        Coordinates coordinates = new Coordinates(5, 5);
        field.changeTile(coordinates, OBSTACLE);
        Assertions.assertEquals(OBSTACLE, field.getTile(coordinates).getType());
    }

    @Test
    public void changeRowTest() {
        initF(10, 10);
        Coordinates coordinates = new Coordinates(0, 5);
        field.changeRow(5, OBSTACLE);
        for (int i = 0; i < 10; i++) {
            coordinates.setX(i);
            Assertions.assertEquals(OBSTACLE, field.getTile(coordinates).getType());
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> field.changeRow(-1, OBSTACLE));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> field.changeRow(10, OBSTACLE));
    }

    @Test
    public void changeColumnTest() {
        initF(10, 10);
        Coordinates coordinates = new Coordinates(5, 0);
        field.changeColumn(5, OBSTACLE);
        for (int i = 0; i < 10; i++) {
            coordinates.setY(i);
            Assertions.assertEquals(OBSTACLE, field.getTile(coordinates).getType());
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> field.changeColumn(-1, OBSTACLE));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> field.changeColumn(10, OBSTACLE));
    }

    @Test
    public void surroundFieldTest() {
        initF(10, 10);
        Coordinates coordinates = new Coordinates(0, 0);
        field.surroundField();
        for (int i = 0; i < 10; i++) {
            coordinates.setX(i);
            Assertions.assertEquals(OBSTACLE, field.getTile(coordinates).getType());
        }

        coordinates.setXY(0,9);
        for (int i = 0; i < 10; i++) {
            coordinates.setX(i);
            Assertions.assertEquals(OBSTACLE, field.getTile(coordinates).getType());
        }

        coordinates.setXY(0, 0);
        for (int i = 0; i < 10; i++) {
            coordinates.setY(i);
            Assertions.assertEquals(OBSTACLE, field.getTile(coordinates).getType());
        }

        coordinates.setXY(9, 0);
        for (int i = 0; i < 10; i++) {
            coordinates.setY(i);
            Assertions.assertEquals(OBSTACLE, field.getTile(coordinates).getType());
        }
    }

    @Test
    public void getFreeTilesTest() {
        initF(10, 10);
        Coordinates coordinates = new Coordinates(5, 5);
        field.changeTile(coordinates, OBSTACLE);
        coordinates.setXY(2, 7);
        field.changeTile(coordinates, OBSTACLE);
        coordinates.setXY(6, 8);
        field.changeTile(coordinates, OBSTACLE);
        coordinates.setXY(0, 1);
        field.changeTile(coordinates, OBSTACLE);

        field.getFreeTiles();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((j == 0 & i == 1) || (j == 2 & i == 7) || (j == 5 & i == 5) || (j == 6 & i == 8)) {
                    Assertions.assertFalse(field.getFreeTiles().contains(field.getTile(new Coordinates(j, i))));
                } else {
                    Assertions.assertTrue(field.getFreeTiles().contains(field.getTile(new Coordinates(j, i))));
                }
            }
        }
    }
}
