package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.snake.SnakeHead;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

public class SnakeHeadTests {

    Field field;
    SnakeHead snakeHead;

    public void initSH(int sizeX, int sizeY, Coordinates coordinates) {
        field = new Field(sizeX, sizeY);
        snakeHead = new SnakeHead(coordinates, field);
    }

    @Test
    public void snakeMoveTest() {
        Coordinates up = new Coordinates(5, 4);
        Coordinates down = new Coordinates(5, 6);
        Coordinates left = new Coordinates(4, 5);
        Coordinates right = new Coordinates(6, 5);

        initSH(10, 10, new Coordinates(5, 5));
        snakeHead.move(UP);
        Assertions.assertEquals(up.getX(), snakeHead.getCoordinates().getX());
        Assertions.assertEquals(up.getY(), snakeHead.getCoordinates().getY());

        initSH(10, 10, new Coordinates(5, 5));
        snakeHead.move(DOWN);
        Assertions.assertEquals(down.getX(), snakeHead.getCoordinates().getX());
        Assertions.assertEquals(down.getY(), snakeHead.getCoordinates().getY());

        initSH(10, 10, new Coordinates(5, 5));
        snakeHead.move(LEFT);
        Assertions.assertEquals(left.getX(), snakeHead.getCoordinates().getX());
        Assertions.assertEquals(left.getY(), snakeHead.getCoordinates().getY());

        initSH(10, 10, new Coordinates(5, 5));
        snakeHead.move(RIGHT);
        Assertions.assertEquals(right.getX(), snakeHead.getCoordinates().getX());
        Assertions.assertEquals(right.getY(), snakeHead.getCoordinates().getY());
    }

    @Test
    public void snakeGrowTest() {
        initSH(10, 10, new Coordinates(5, 5));
        snakeHead.grow();
        Assertions.assertEquals(1, snakeHead.getLength());
        Assertions.assertEquals(snakeHead.getTail().size(), snakeHead.getLength());
        Assertions.assertEquals(snakeHead.getTail().getFirst(), snakeHead.getNextPart());

        snakeHead.move(UP);
        Assertions.assertNotEquals(snakeHead.getCoordinates(), snakeHead.getNextPart().getCoordinates());
        Assertions.assertEquals(5, snakeHead.getNextPart().getCoordinates().getX());
        Assertions.assertEquals(5, snakeHead.getNextPart().getCoordinates().getY());

    }
}
