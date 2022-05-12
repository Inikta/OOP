package ru.nsu.nikita.view.snake_view;

import javafx.scene.paint.Color;

public class SnakeViewSettingsContainer implements Cloneable {
    private double shiftX;
    private double shiftY;
    private double padding;
    private double width;
    private double height;
    private Color color;

    public SnakeViewSettingsContainer(double shiftX, double shiftY, double width, double height, double padding, Color color) {
        this.shiftX = shiftX;
        this.shiftY = shiftY;
        this.padding = padding;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public SnakeViewSettingsContainer clone() {
        try {
            SnakeViewSettingsContainer clone = (SnakeViewSettingsContainer) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public double getShiftX() {
        return shiftX;
    }

    public void setShiftX(double shiftX) {
        this.shiftX = shiftX;
    }

    public double getShiftY() {
        return shiftY;
    }

    public void setShiftY(double shiftY) {
        this.shiftY = shiftY;
    }

    public double getPadding() {
        return padding;
    }

    public void setPadding(double padding) {
        this.padding = padding;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
