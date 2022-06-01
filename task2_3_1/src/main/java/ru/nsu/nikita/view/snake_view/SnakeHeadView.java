package ru.nsu.nikita.view.snake_view;

import javafx.beans.property.SimpleBooleanProperty;
import ru.nsu.nikita.backlogic.snake.SnakeHead;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeHeadView extends SnakePartView {

    private final Deque<SnakePartView> tail;
    private final SnakeHead snakeHead;

    private SnakeViewSettingsContainer headViewSettings;
    private SnakeViewSettingsContainer bodyViewSettings;

    public SimpleBooleanProperty living;

    /**
     * Snake head view constructor
     * @param snakeHead snake head itself
     * @param headViewSettings view settings for snake head
     * @param bodyViewSettings view settings for snake body
     */
    public SnakeHeadView(SnakeHead snakeHead, SnakeViewSettingsContainer headViewSettings, SnakeViewSettingsContainer bodyViewSettings) {
        super(snakeHead, headViewSettings);
        tail = new ArrayDeque<>();
        this.snakeHead = snakeHead;

        this.headViewSettings = headViewSettings;
        this.bodyViewSettings = bodyViewSettings;

        living = new SimpleBooleanProperty(true);
    }

    /**
     * Update snake state.
     * Moves body parts and adds new.
     */
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

    /**
     * Move snake head view
     */
    private void moveHead() {
        double newX = shiftX + snakeHead.getCoordinates().getX() * (width + padding);
        double newY = shiftY + snakeHead.getCoordinates().getY() * (height + padding);

        this.coordinates = snakeHead.getCoordinates();

        setX(newX);
        setY(newY);
    }

    /**
     * Add views of new body parts
     */
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

    /**
     * Destroy snake
     */
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
}
