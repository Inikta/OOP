package ru.nsu.nikita.view;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ru.nsu.nikita.backlogic.Coordinates;

public abstract class AbstractRectangle extends Rectangle {
    protected Coordinates coordinates;
    protected double shiftX;
    protected double shiftY;
    protected double padding;
    protected double width;
    protected double height;
    protected Color color;

    public AbstractRectangle(Coordinates coordinates,
                             double shiftX,
                             double shiftY,
                             double width,
                             double height,
                             double padding,
                             Color color) {
        super(shiftX + coordinates.getX() * (width + padding), shiftY + coordinates.getY() * (height + padding), width, height);
        this.fillProperty().set(color);

        this.coordinates = coordinates;
        this.shiftX = shiftX;
        this.shiftY = shiftY;
        this.padding = padding;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    protected double computeX() {
        return shiftX + coordinates.getX() * (width + padding);
    }

    protected double computeY() {
        return shiftY + coordinates.getY() * (height + padding);
    }
}
