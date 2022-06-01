package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.TileType;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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

    private boolean selfCrash;

    /**
     * Create snakeHead data structure with initial coordinates and field.
     * @param headCoordinates initial coordinates
     * @param field field, on which snake will be moving
     */
    public SnakeHead(Coordinates headCoordinates, Field field) {
        super(headCoordinates);

        selfCrash = false;
        length = 0;
        living = true;
        lastDirection = NONE;

        tail = new ArrayDeque<>();
        this.field = field;
    }

    /**
     * Move snake head in provided direction. After it:
     *  1. Checks, if head have intersections with body.
     *  2. Will move the whole snake.
     *  3. Checks for new tile event.
     * Restricts movement in direction opposite to current.
     * @param direction movement direction
     */
    public void move(Direction direction) {
        prevCoordinates = coordinates.clone();
        Coordinates newCoordinates;

        if (direction == inverseLastDirection) {
            newCoordinates = moveCoordinates(lastDirection);
        } else {
            newCoordinates = moveCoordinates(direction);

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
        }
        checkSnake(newCoordinates);
        moveTail(newCoordinates);

        tileEvent();
    }

    /**
     * Compute coordinates of new head position.
     * @param direction movement direction
     * @return new coordinates
     */
    private Coordinates moveCoordinates(Direction direction) {
        Coordinates newCoordinates;

        switch (direction) {
            case LEFT -> newCoordinates = moveLeft();
            case RIGHT -> newCoordinates = moveRight();
            case UP -> newCoordinates = moveUp();
            case DOWN -> newCoordinates = moveDown();
            default -> newCoordinates = coordinates;
        }

        return newCoordinates;
    }

    /**
     * Checks if snake head crosses its body.
     * Have two modes: to die after crossing or remove body parts lower cross point (not working).
     * @param newCoordinates current snake head coordinates
     */
    private void checkSnake(Coordinates newCoordinates) {
        List<SnakePart> partsToRemove = new ArrayList<>();
        tail.forEach(snakePart -> {
            if (newCoordinates.getX() == snakePart.getCoordinates().getX() &&
                    newCoordinates.getY() == snakePart.getCoordinates().getY()) {
                if (selfCrash) {
                    die();
                } else {
                    SnakePart part = snakePart;
                    while (part != null) {
                        partsToRemove.add(part);
                        part = part.nextPart;
                    }
                }
            }
        });

        tail.removeAll(partsToRemove);
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

    /**
     * Check for something new in the current tile.
     * Calls die() method, if tile is OBSTACLE;
     * Calls eat() method, if tile is GRASS;
     * Does nothing otherwise.
     */
    private void tileEvent() {
        TileType type = field.getTile(coordinates).getType();
        switch (type) {
            case OBSTACLE -> die();
            case GRASS -> eat();
            default -> {
            }
        }
    }

    /**
     * Checks, if there is food on the tile.
     * If it is true, then calls method grow().
     */
    private void eat() {
        if (field.getTile(coordinates).isHasFood()) {
            grow();
        }
    }

    /**
     * Create new body part right after head and increase length by 1.
     */
    public void grow() {
        SnakePart newSnakePart = new SnakePart(coordinates);

        newSnakePart.setPrevPart(this);
        newSnakePart.setNextPart(nextPart);
        setNextPart(newSnakePart);

        if (nextPart.nextPart != null) {
            nextPart.nextPart.setPrevPart(nextPart);
        }

        tail.addFirst(newSnakePart);

        grows = true;
        length++;
    }

    /**
     * Deletes snake parts recursively.
     */
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

    public boolean isSelfCrash() {
        return selfCrash;
    }

    public void setSelfCrash(boolean selfCrash) {
        this.selfCrash = selfCrash;
    }
}

