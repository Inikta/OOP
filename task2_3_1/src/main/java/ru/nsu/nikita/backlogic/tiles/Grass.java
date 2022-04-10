package ru.nsu.nikita.backlogic.tiles;

import ru.nsu.nikita.backlogic.Coordinates;

public class Grass extends Tile {

    public Grass() {
        super();
    }

    public Grass(int x, int y) {
        super(x, y);
        type = TileTypes.GRASS;
    }

    public Grass(Coordinates coordinates) {
        super(coordinates);
        type = TileTypes.GRASS;
    }
}
