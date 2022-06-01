package ru.nsu.nikita.backlogic.snake;

import ru.nsu.nikita.backlogic.Coordinates;

public class SnakePart {
    protected Coordinates coordinates;
    protected SnakePart prevPart;
    protected SnakePart nextPart;

    /**
     * Body part constructor with initial coordinates.
     * @param coordinates initial coordinates on the field.
     */
    public SnakePart(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Move self to new coordinates and call this method for the rest tail of the snake.
     * @param newCoordinates new coordinates
     */
    public void moveTail(Coordinates newCoordinates) {
        Coordinates oldCoordinates = coordinates.clone();
        setCoordinates(newCoordinates);
        if (nextPart != null) {
            nextPart.moveTail(oldCoordinates);
        }
    }

    /**
     * Destroy next part, if its exists.
     */
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

    /**
     * Get snake part closer to head.
     * @return snake part
     */
    public SnakePart getPrevPart() {
        return prevPart;
    }

    public void setPrevPart(SnakePart prevPart) {
        this.prevPart = prevPart;
    }

    /**
     * Get snake part closer to the end of tail.
     * @return snake part
     */
    public SnakePart getNextPart() {
        return nextPart;
    }

    public void setNextPart(SnakePart nextPart) {
        this.nextPart = nextPart;
    }
}
