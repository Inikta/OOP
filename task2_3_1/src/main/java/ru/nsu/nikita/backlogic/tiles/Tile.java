package ru.nsu.nikita.backlogic.tiles;

import ru.nsu.nikita.backlogic.Coordinates;

public abstract class Tile {
    protected Coordinates coordinates;
    protected TileTypes type;

    protected Coordinates leftNeighbor;
    protected Coordinates rightNeighbor;
    protected Coordinates upperNeighbor;
    protected Coordinates downNeighbor;

    public Tile() {}

    public Tile(int x, int y) {
        coordinates = new Coordinates(x, y);
    }

    public Tile(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public TileTypes getType() {
        return type;
    }

    public void setType(TileTypes type) {
        this.type = type;
    }

    public Coordinates getLeftNeighbor() {
        return leftNeighbor;
    }

    public void setLeftNeighbor(Coordinates leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public Coordinates getRightNeighbor() {
        return rightNeighbor;
    }

    public void setRightNeighbor(Coordinates rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public Coordinates getUpperNeighbor() {
        return upperNeighbor;
    }

    public void setUpperNeighbor(Coordinates upperNeighbor) {
        this.upperNeighbor = upperNeighbor;
    }

    public Coordinates getDownNeighbor() {
        return downNeighbor;
    }

    public void setDownNeighbor(Coordinates downNeighbor) {
        this.downNeighbor = downNeighbor;
    }
}
