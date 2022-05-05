package ru.nsu.nikita.view.snake_view;

import ru.nsu.nikita.backlogic.snake.SnakeHead;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeHeadView extends SnakePartView {

    private Deque<SnakePartView> tail;
    private SnakeHead snakeHead;

    private SnakeViewSettingsContainer headViewSettings;
    private SnakeViewSettingsContainer bodyViewSettings;

    public SnakeHeadView(SnakeHead snakeHead, SnakeViewSettingsContainer headViewSettings, SnakeViewSettingsContainer bodyViewSettings) {
        super(snakeHead, headViewSettings);
        tail = new ArrayDeque<>();
        this.snakeHead = snakeHead;

        this.headViewSettings = headViewSettings;
        this.bodyViewSettings = bodyViewSettings;
    }

    public void update() {
        if (snakeHead.isLiving()) {
            if (snakeHead.isGrows()) {
                grow();
            }
            moveHead();
        } else {
            this.die();
        }
    }

    private void moveHead() {
        double newX = shiftX + snakeHead.getCoordinates().getX() * (width + padding);
        double newY = shiftY + snakeHead.getCoordinates().getY() * (height + padding);

        this.coordinates = snakeHead.getCoordinates();
        setSnakePart(snakeHead);

        setX(newX);
        setY(newY);

        nextPartView.moveTail();
    }

    private void grow() {
        SnakePartView newSnakePartView = new SnakePartView(snakeHead.getNextPart(), bodyViewSettings);

        newSnakePartView.setNextPartView(nextPartView);
        newSnakePartView.setPrevPartView(this);
        nextPartView.setPrevPartView(newSnakePartView);
        setNextPartView(newSnakePartView);

        tail.addFirst(newSnakePartView);
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
}
