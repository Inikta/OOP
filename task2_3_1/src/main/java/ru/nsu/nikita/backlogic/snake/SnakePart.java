package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;

public class SnakePart {
    protected Coordinates coordinates;
    protected SnakePart prevPart;
    protected SnakePart nextPart;

    public SnakePart(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void moveTail(Coordinates newCoordinates) {
        Coordinates oldCoordinates = coordinates.clone();
        setCoordinates(newCoordinates);
        if (nextPart != null) {
            nextPart.moveTail(oldCoordinates);
        }
    }

    public void die() {
        if (nextPart != null) {
            nextPart.die();
        }
        nextPart = null;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public SnakePart getPrevPart() {
        return prevPart;
    }

    public void setPrevPart(SnakePart prevPart) {
        this.prevPart = prevPart;
    }

    public SnakePart getNextPart() {
        return nextPart;
    }

    public void setNextPart(SnakePart nextPart) {
        this.nextPart = nextPart;
    }
}
