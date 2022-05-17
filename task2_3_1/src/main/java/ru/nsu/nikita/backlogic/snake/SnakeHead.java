package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.TileType;


import java.util.ArrayDeque;
import java.util.Deque;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

public class SnakeHead extends SnakePart {
    private int length;
    private boolean living;

    private Deque<SnakePart> tail;
    private Field field;
    private Direction lastDirection;
    private Direction inverseLastDirection;
    private Coordinates prevCoordinates;

    private boolean grows = false;

    public SnakeHead(Coordinates headCoordinates, Field field) {
        super(headCoordinates);

        length = 0;
        living = true;
        lastDirection = NONE;

        tail = new ArrayDeque<>();
        this.field = field;
    }

    public void move(Direction direction) {
        prevCoordinates = coordinates.clone();
        Coordinates newCoordinates;
        switch (direction) {
            case LEFT -> {
                inverseLastDirection = RIGHT;
                newCoordinates = moveLeft();
            }
            case RIGHT -> {
                inverseLastDirection = LEFT;
                newCoordinates = moveRight();
            }
            case UP -> {
                inverseLastDirection = DOWN;
                newCoordinates = moveUp();
            }
            case DOWN -> {
                inverseLastDirection = UP;
                newCoordinates = moveDown();
            }
            default -> {
                return;
            }
        }
        if (length > 0) {
            if (direction != inverseLastDirection) {
                setCoordinates(newCoordinates);
                if (grows) {
                    grows = false;
                }
                moveTail(prevCoordinates);
            }
            tileEvent();
            //return;
        } else {
            setCoordinates(newCoordinates);
        }
        setCoordinates(newCoordinates);

        lastDirection = direction;
        switch (lastDirection) {
            case LEFT -> inverseLastDirection = RIGHT;
            case RIGHT -> inverseLastDirection = LEFT;
            case UP -> inverseLastDirection = DOWN;
            case DOWN -> inverseLastDirection = UP;
            default -> {
                return;
            }
        }

        tileEvent();
    }

    private Coordinates moveLeft() {
        return new Coordinates(
                field.getTile(coordinates).getLeftNeighbor().getX(),
                field.getTile(coordinates).getLeftNeighbor().getY());
    }

    private Coordinates moveRight() {
        return new Coordinates(
                field.getTile(coordinates).getRightNeighbor().getX(),
                field.getTile(coordinates).getRightNeighbor().getY());
    }

    private Coordinates moveDown() {
        return new Coordinates(
                field.getTile(coordinates).getDownNeighbor().getX(),
                field.getTile(coordinates).getDownNeighbor().getY());
    }

    private Coordinates moveUp() {
        return new Coordinates(
                field.getTile(coordinates).getUpperNeighbor().getX(),
                field.getTile(coordinates).getUpperNeighbor().getY());
    }

    private void tileEvent() {
        TileType type = field.getTile(coordinates).getType();
        switch (type) {
            case OBSTACLE -> die();
            case GRASS -> eat();
            default -> {
            }
        }
    }

    private void eat() {
        if (field.getTile(coordinates).isHasFood()) {
            grow();
        }
    }

    public void grow() {
        SnakePart newSnakePart = new SnakePart(coordinates);

        newSnakePart.setPrevPart(this);
        newSnakePart.setNextPart(nextPart);
        setNextPart(newSnakePart);

        if (nextPart.nextPart != null) {
            nextPart.setPrevPart(newSnakePart);
        }

        tail.addFirst(newSnakePart);

        grows = true;
        length++;
    }

    @Override
    public void die() {
        super.die();
        living = false;
    }

    public boolean isLiving() {
        return living;
    }

    public int getLength() {
        return length;
    }

    public Deque<SnakePart> getTail() {
        return tail;
    }

    public Field getField() {
        return field;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public boolean isGrows() {
        return grows;
    }
}

