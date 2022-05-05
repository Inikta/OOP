package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.TileType;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

public class SnakeHead extends SnakePart {
    private int length;
    private boolean living;

    private Deque<SnakePart> tail;
    private Field field;
    private Direction lastDirection;

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
        lastDirection = direction;
        Coordinates oldCoordinates = coordinates.clone();
        Coordinates newCoordinates;
        switch (direction) {
            case LEFT -> newCoordinates = moveLeft();
            case RIGHT -> newCoordinates = moveRight();
            case UP -> newCoordinates = moveUp();
            case DOWN -> newCoordinates = moveDown();
            default -> {
                return;
            }
        }
        if (length > 0) {
            if (newCoordinates.getX() != prevPart.getCoordinates().getX() & newCoordinates.getY() != prevPart.getCoordinates().getY()) {
                setCoordinates(newCoordinates);
                if (grows) {
                    grows = false;
                } else {
                    moveTail(oldCoordinates);
                }
            }
        } else {
            setCoordinates(newCoordinates);
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
        nextPart.setPrevPart(newSnakePart);
        setNextPart(newSnakePart);

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

