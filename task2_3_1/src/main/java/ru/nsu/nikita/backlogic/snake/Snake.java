package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.TileType;


import java.util.ArrayList;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

public class Snake {
    private Coordinates headCoordinates;
    private Coordinates tailTrail;
    private int length;
    private ArrayList<Coordinates> tail;
    private boolean living;
    private Field field;
    private Direction lastDirection;

    public Snake(int xHead, int yHead, Field field) {
        headCoordinates = new Coordinates(xHead, yHead);
        length = 1;

        tail = new ArrayList<>(length);
        living = true;
        lastDirection = NONE;

        this.field = field;
    }

    public void move(Direction direction) {
        lastDirection = direction;
        Coordinates oldHead = headCoordinates.clone();
        Coordinates newHead;
        switch (direction) {
            case LEFT -> newHead = moveLeft();
            case RIGHT -> newHead = moveRight();
            case UP -> newHead = moveUp();
            case DOWN -> newHead = moveDown();
            default -> {
                return;
            }
        }
        if (length > 1) {
            if (newHead.getX() != tail.get(0).getX() & newHead.getY() != tail.get(0).getY()) {
                headCoordinates.setXY(newHead.getX(), newHead.getY());
                moveTail(oldHead);
            }
        } else {
            headCoordinates.setXY(newHead.getX(), newHead.getY());
        }
        tailTrail = oldHead;
        tileEvent();
    }

    private Coordinates moveLeft() {
        return new Coordinates(
                field.getTile(headCoordinates).getLeftNeighbor().getX(),
                field.getTile(headCoordinates).getLeftNeighbor().getY());
    }

    private Coordinates moveRight() {
        return new Coordinates(
                field.getTile(headCoordinates).getRightNeighbor().getX(),
                field.getTile(headCoordinates).getRightNeighbor().getY());
    }

    private Coordinates moveDown() {
        return new Coordinates(
                field.getTile(headCoordinates).getDownNeighbor().getX(),
                field.getTile(headCoordinates).getDownNeighbor().getY());
    }

    private Coordinates moveUp() {
        return new Coordinates(
                field.getTile(headCoordinates).getUpperNeighbor().getX(),
                field.getTile(headCoordinates).getUpperNeighbor().getY());
    }

    private void moveTail(Coordinates oldHead) {
        Coordinates trail = tail.get(0);
        tail.get(0).setXY(oldHead.getX(), oldHead.getY());
        for (int i = 1; i < tail.size(); i++) {
            Coordinates temp = tail.get(i);
            tail.get(i).setXY(
                    trail.getX(),
                    trail.getY());
            trail = temp;
        }
        tailTrail = trail;
    }

    private void tileEvent() {
        TileType type = field.getTile(headCoordinates).getType();
        switch (type) {
            case OBSTACLE -> die();
            case GRASS -> eat();
            default -> {
            }
        }
    }

    private void eat() {
        if (field.getTile(headCoordinates).isHasFood()) {
            grow();
        }
    }

    public void grow() {
        tail.add(new Coordinates(tailTrail.getX(), tailTrail.getY()));
        length++;
    }

    public void die() {
        tail.clear();
        living = false;
    }

    public boolean isLiving() {
        return living;
    }

    public Coordinates getHeadCoordinates() {
        return headCoordinates;
    }

    public Coordinates getTailTrail() {
        return tailTrail;
    }

    public int getLength() {
        return length;
    }

    public ArrayList<Coordinates> getTail() {
        return tail;
    }

    public Field getField() {
        return field;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }
}

