package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.field.Field;

import java.util.ArrayList;

public class Snake {
    private Coordinates headCoordinates;
    private int length;
    private ArrayList<Coordinates> tail;

    private Field field;

    public Snake(int xHead, int yHead, Field field) {
        headCoordinates = new Coordinates(xHead, yHead);
        length = 1;

        tail = new ArrayList<>(length);

        this.field = field;
    }

    private void moveTail() {
        for (int i = 0; i < tail.size(); i++) {
            if (i != 0) {
                tail.get(i).setXY(
                        tail.get(i - 1).getX(),
                        tail.get(i - 1).getY());
            }
        }
    }

    public void moveLeft() {
        Coordinates oldHead = headCoordinates.clone();
        headCoordinates.setXY(
                field.getTile(headCoordinates).getLeftNeighbor().getX(),
                field.getTile(headCoordinates).getLeftNeighbor().getY());
        tail.get(0).setXY(oldHead.);

    }

    public void moveRight() {
        headCoordinates.setXY(
                field.getTile(headCoordinates).getRightNeighbor().getX(),
                field.getTile(headCoordinates).getRightNeighbor().getY());
        tail.forEach(t -> t.setXY(
                field.getTile(t).getRightNeighbor().getX(),
                field.getTile(t).getRightNeighbor().getY()));
    }

    public void moveDown() {
        headCoordinates.setXY(
                field.getTile(headCoordinates).getDownNeighbor().getX(),
                field.getTile(headCoordinates).getDownNeighbor().getY());
        tail.forEach(t -> t.setXY(
                field.getTile(t).getDownNeighbor().getX(),
                field.getTile(t).getDownNeighbor().getY()));
    }

    public void moveUp() {
        headCoordinates.setXY(
                field.getTile(headCoordinates).getUpperNeighbor().getX(),
                field.getTile(headCoordinates).getUpperNeighbor().getY());
        tail.forEach(t -> t.setXY(
                field.getTile(t).getUpperNeighbor().getX(),
                field.getTile(t).getUpperNeighbor().getY()));
    }

}

