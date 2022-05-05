package ru.nsu.nikita.view.snake_view;

import javafx.scene.paint.Color;
import ru.nsu.nikita.backlogic.snake.SnakePart;
import ru.nsu.nikita.view.AbstractRectangle;

public class SnakePartView extends AbstractRectangle {
    protected SnakePart snakePart;
    protected SnakePartView prevPartView;
    protected SnakePartView nextPartView;

    protected SnakeViewSettingsContainer viewSettings;

    public SnakePartView(SnakePart snakePart, SnakeViewSettingsContainer viewSettings) {
        super(
                snakePart.getCoordinates(),
                viewSettings.getShiftX(),
                viewSettings.getShiftY(),
                viewSettings.getPadding(),
                viewSettings.getWidth(),
                viewSettings.getHeight(),
                viewSettings.getColor()
        );
        this.snakePart = snakePart;
        this.viewSettings = viewSettings;
    }

    public void moveTail() {
        if (nextPartView != null) {
            nextPartView.moveTail();
        }
        double newX = shiftX + prevPartView.coordinates.getX() * (width + padding);
        double newY = shiftY + prevPartView.coordinates.getY() * (height + padding);

        this.coordinates = prevPartView.coordinates;
        setSnakePart(prevPartView.snakePart);

        setX(newX);
        setY(newY);
    }

    public void die() {
        if (nextPartView != null) {
            nextPartView.die();
        }
        setFill(Color.RED);
    }

    public SnakePartView getPrevPartView() {
        return prevPartView;
    }

    public void setPrevPartView(SnakePartView prevPartView) {
        this.prevPartView = prevPartView;
    }

    public SnakePartView getNextPartView() {
        return nextPartView;
    }

    public void setNextPartView(SnakePartView nextPartView) {
        this.nextPartView = nextPartView;
    }

    public SnakeViewSettingsContainer getViewSettings() {
        return viewSettings;
    }

    public void setViewSettings(SnakeViewSettingsContainer viewSettings) {
        this.viewSettings = viewSettings;
    }

    public SnakePart getSnakePart() {
        return snakePart;
    }

    public void setSnakePart(SnakePart snakePart) {
        this.snakePart = snakePart;
    }
}
