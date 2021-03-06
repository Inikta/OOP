package ru.nsu.nikita.backlogic.tiles;

import ru.nsu.nikita.backlogic.Coordinates;

import static ru.nsu.nikita.backlogic.tiles.TileType.*;

public class Tile {
    private Coordinates coordinates;
    private TileType type;
    private Coordinates leftNeighbor;
    private Coordinates rightNeighbor;
    private Coordinates upperNeighbor;
    private Coordinates downNeighbor;

    private boolean hasFood;

    /**
     * Tile constructor.
     * @param x horizontal coordinate in the field
     * @param y vertical coordinate in the field
     * @param type type of the tile
     */
    public Tile(int x, int y, TileType type) {
        coordinates = new Coordinates(x, y);
        this.type = type;
        hasFood = false;
    }

    /**
     * Checks, if tile has food on it.
     * Always false, if tile is an OBSTACLE.
     * @return state of tile
     */
    public boolean isHasFood() {
        if (type != OBSTACLE) {
            return hasFood;
        } else {
            return false;
        }
    }

    /**
     * Sets, if tile has food on it or not.
     * Always false, if tile is an OBSTACLE.
     * @return
     */
    public void setHasFood(boolean hasFood) {
        if (type != OBSTACLE) {
            this.hasFood = hasFood;
        } else {
            this.hasFood = false;
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
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
