package ru.nsu.nikita.backlogic.tiles;

import ru.nsu.nikita.backlogic.Coordinates;

public class Obstacle extends Tile {

    public Obstacle() {
        super();
    }

    public Obstacle(int x, int y) {
        super(x, y);
        type = TileTypes.OBSTACLE;
    }

    public Obstacle(Coordinates coordinates) {
        super(coordinates);
        type = TileTypes.OBSTACLE;
    }
}
