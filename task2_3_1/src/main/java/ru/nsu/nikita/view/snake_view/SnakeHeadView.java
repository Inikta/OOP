package ru.nsu.nikita.view.snake_view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import ru.nsu.nikita.backlogic.snake.SnakeHead;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeHeadView extends SnakePartView {

    private Deque<SnakePartView> tail;
    private SnakeHead snakeHead;

    private SnakeViewSettingsContainer headViewSettings;
    private SnakeViewSettingsContainer bodyViewSettings;

    private SimpleBooleanProperty living;

    public SnakeHeadView(SnakeHead snakeHead, SnakeViewSettingsContainer headViewSettings, SnakeViewSettingsContainer bodyViewSettings) {
        super(snakeHead, headViewSettings);
        tail = new ArrayDeque<>();
        this.snakeHead = snakeHead;

        this.headViewSettings = headViewSettings;
        this.bodyViewSettings = bodyViewSettings;

        living = new SimpleBooleanProperty(true);
    }

    public void update() {
        if (snakeHead.isLiving()) {
            if (tail.size() < snakeHead.getLength()) {
                grow();
            }
            moveTail(snakeHead);

        } else {
            this.die();
        }
    }

    private void moveHead() {
        double newX = shiftX + snakeHead.getCoordinates().getX() * (width + padding);
        double newY = shiftY + snakeHead.getCoordinates().getY() * (height + padding);

        this.coordinates = snakeHead.getCoordinates();
        //setSnakePart(snakeHead);

        setX(newX);
        setY(newY);
    }

    private void grow() {
        SnakePartView newSnakePartView = new SnakePartView(snakeHead.getNextPart(), bodyViewSettings);

        newSnakePartView.setNextPartView(nextPartView);
        newSnakePartView.setPrevPartView(this);
        setNextPartView(newSnakePartView);

        if (nextPartView.nextPartView != null) {
            nextPartView.nextPartView.setPrevPartView(nextPartView);
        }

        tail.addFirst(newSnakePartView);
    }

    @Override
    public void die() {
        super.die();
        living.set(false);
    }

    public SnakeViewSettingsContainer getHeadViewSettings() {
        return headViewSettings;
    }

    public void setHeadViewSettings(SnakeViewSettingsContainer headViewSettings) {
        this.headViewSettings = headViewSettings;
    }

    public SnakeViewSettingsContainer getBodyViewSettings() {
        return bodyViewSettings;
    }

    public void setBodyViewSettings(SnakeViewSettingsContainer bodyViewSettings) {
        this.bodyViewSettings = bodyViewSettings;
    }

    public Deque<SnakePartView> getTail() {
        return tail;
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public boolean isLiving() {
        return living.get();
    }

    public SimpleBooleanProperty livingProperty() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living.set(living);
    }
}
