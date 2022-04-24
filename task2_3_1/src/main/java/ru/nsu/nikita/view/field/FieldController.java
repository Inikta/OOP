package ru.nsu.nikita.view.field;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.Tile;

import java.net.URL;
import java.util.ResourceBundle;

public class FieldController implements Initializable {

    private Field field;
    private double xShift;
    private double yShift;

    @FXML
    private Group fieldGroup;

    public FieldController(Field field, double xShift, double yShift) {
        this.field = field;
        this.xShift = xShift;
        this.yShift = yShift;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double size = 100;
        for (Tile[] row : field.getField()) {
            for (Tile tile : row) {

                Rectangle tileView = new Rectangle(xShift + tile.getCoordinates().getX() * size,
                        yShift + tile.getCoordinates().getY() * size,
                        size, size);
                tileView.fillProperty().set(makeColor(tile));

                fieldGroup.getChildren().add(tileView);
            }
        }
    }

    public void update() {

    }

    private Paint makeColor(Tile tileData) {
        Paint color = Color.WHITE;
        switch (tileData.getType()) {
            case GRASS -> color = Color.DARKGREEN;
            case OBSTACLE -> color = Color.DARKGRAY;
        }

        return color;
    }
}
