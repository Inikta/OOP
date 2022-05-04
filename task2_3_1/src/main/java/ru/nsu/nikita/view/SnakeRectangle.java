package ru.nsu.nikita.view;

import javafx.scene.paint.Color;
import ru.nsu.nikita.backlogic.Coordinates;
import ru.nsu.nikita.backlogic.snake.Snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeRectangle extends AbstractTile {

    private Snake currentSnakeData;
    private List<SnakeRectangle> tail;

    public SnakeRectangle(Snake snakeHead,
                          double shiftX,
                          double shiftY,
                          double padding,
                          double width,
                          double height,
                          Color color) {
        super(snakeHead.getHeadCoordinates(), shiftX, shiftY, padding, width, height, color);
        tail = new ArrayList<>();
        currentSnakeData = snakeHead;
    }

    public void update(Snake newSnakeData) {
        if (newSnakeData.isLiving()) {
            updateHeadPosition(newSnakeData);
            if (newSnakeData.getLength() > currentSnakeData.getLength()) {
                tail.add(new SnakeRectangle(
                        new Snake(
                                newSnakeData.getTail().get(newSnakeData.getTail().size() - 1).getX(),
                                newSnakeData.getTail().get(newSnakeData.getTail().size() - 1).getY(),
                                newSnakeData.getField()),
                        shiftX,
                        shiftY,
                        padding,
                        width,
                        height,
                        Color.ORANGERED));
            }
            updateTailPosition(newSnakeData);
        } else {
            this.fillProperty().set(Color.RED);
        }
    }

    private void updateHeadPosition(Snake newSnakeData) {
        double newX = shiftX + newSnakeData.getHeadCoordinates().getX() * (width + padding);
        double newY = shiftY + newSnakeData.getHeadCoordinates().getY() * (height + padding);

        this.coordinates = newSnakeData.getHeadCoordinates();
        currentSnakeData = newSnakeData;

        setX(newX);
        setY(newY);
    }

    private void updateTailPosition(Snake newSnakeData) {
        for (int i = 0; i < newSnakeData.getTail().size(); i++) {
            updateBodyPartPosition(tail.get(i), newSnakeData.getTail().get(i), newSnakeData);
        }
    }

    private void updateBodyPartPosition(SnakeRectangle bodyPart, Coordinates newCoordinates, Snake newSnakeData) {
        double newX = shiftX + newCoordinates.getX() * (width + padding);
        double newY = shiftY + newCoordinates.getY() * (height + padding);

        bodyPart.currentSnakeData.getHeadCoordinates().setXY(newCoordinates.getX(), newCoordinates.getY());

        bodyPart.setX(newX);
        bodyPart.setY(newY);
    }
}
