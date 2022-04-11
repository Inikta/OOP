package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.TileType;


import java.util.ArrayList;

public class Snake {
    private Coordinates headCoordinates;
    private Coordinates tailTrail;
    private int length;
    private ArrayList<Coordinates> tail;
    private boolean alive;
    private Field field;
    private Direction lastDirection;

    public Snake(int xHead, int yHead, Field field) {
        headCoordinates = new Coordinates(xHead, yHead);
        length = 1;

        tail = new ArrayList<>(length);
        alive = true;

        this.field = field;
    }

    public void move(Direction direction) {
        lastDirection = direction;
        switch (direction) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        tileEvent();
    }

    private void moveLeft() {
        Coordinates oldHead = headCoordinates.clone();
        Coordinates newHead = new Coordinates(
                field.getTile(headCoordinates).getLeftNeighbor().getX(),
                field.getTile(headCoordinates).getLeftNeighbor().getY());
        if (newHead.getX() != tail.get(0).getX() & newHead.getY() != tail.get(0).getY()) {
            headCoordinates.setXY(
                    field.getTile(headCoordinates).getLeftNeighbor().getX(),
                    field.getTile(headCoordinates).getLeftNeighbor().getY());
            moveTail(oldHead);
        }
    }

    private void moveRight() {
        Coordinates oldHead = headCoordinates.clone();
        Coordinates newHead = new Coordinates(
                field.getTile(headCoordinates).getRightNeighbor().getX(),
                field.getTile(headCoordinates).getRightNeighbor().getY());
        if (newHead.getX() != tail.get(0).getX() & newHead.getY() != tail.get(0).getY()) {
            headCoordinates.setXY(
                    field.getTile(headCoordinates).getRightNeighbor().getX(),
                    field.getTile(headCoordinates).getRightNeighbor().getY());
            moveTail(oldHead);
        }
    }

    private void moveDown() {
        Coordinates oldHead = headCoordinates.clone();
        Coordinates newHead = new Coordinates(
                field.getTile(headCoordinates).getDownNeighbor().getX(),
                field.getTile(headCoordinates).getDownNeighbor().getY());
        if (newHead.getX() != tail.get(0).getX() & newHead.getY() != tail.get(0).getY()) {
            headCoordinates.setXY(
                    field.getTile(headCoordinates).getDownNeighbor().getX(),
                    field.getTile(headCoordinates).getDownNeighbor().getY());
            moveTail(oldHead);
        }
    }

    private void moveUp() {
        Coordinates oldHead = headCoordinates.clone();
        Coordinates newHead = new Coordinates(
                field.getTile(headCoordinates).getUpperNeighbor().getX(),
                field.getTile(headCoordinates).getUpperNeighbor().getY());
        if (newHead.getX() != tail.get(0).getX() & newHead.getY() != tail.get(0).getY()) {
            headCoordinates.setXY(
                    field.getTile(headCoordinates).getUpperNeighbor().getX(),
                    field.getTile(headCoordinates).getUpperNeighbor().getY());
            moveTail(oldHead);
        }
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
            default -> {}
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
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}

