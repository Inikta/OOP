package ru.nsu.nikita.view.field_view;

import javafx.scene.paint.Color;
import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.tiles.TileType;
import ru.nsu.nikita.view.AbstractRectangle;

import static ru.nsu.nikita.backlogic.tiles.TileType.*;

public class TileView extends AbstractRectangle {

    private TileType type = GRASS;

    public TileType getType() {
        return type;
    }

    public void setType(TileType newType) {
        type = newType;
    }

    public TileView(Coordinates coordinates,
                    double shiftX,
                    double shiftY,
                    double padding,
                    double width,
                    double height,
                    TileType type) {
        super(coordinates, shiftX, shiftY, padding, width, height, Color.WHITE);
        switch (type) {
            case GRASS -> color = Color.GREEN;
            case OBSTACLE -> color = Color.GRAY;
        }

        this.fillProperty().set(color);
    }
}
