package ru.nsu.nikita.backlogic.tiles;

public class TileFactory {
    public static Tile makeTile(TileTypes type) throws Exception {
        Tile tile;
        switch (type) {
            case OBSTACLE -> tile = new Obstacle();
            case GRASS -> tile = new Grass();
            default -> {
                Exception exc = new IllegalArgumentException();
                exc.printStackTrace();
                throw exc;
            }
        }
        return tile;
    }

    public static Tile makeTile(int x, int y, TileTypes type) throws Exception {
        Tile tile = makeTile(type);
        tile.setX(x);
        tile.setY(y);
        return tile;
    }
}
