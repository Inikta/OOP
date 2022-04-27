package ru.nsu.nikita.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.backlogic.tiles.Tile;

import static ru.nsu.nikita.backlogic.tiles.TileType.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Pane fieldPane;

    @FXML
    private TextField scoreTextField;

    private Field fieldData;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        fieldData = new Field(10, 10);
        //createField();
    }

    private void createField() {
        double currentX = 0.0;
        double currentY = 0.0;
        double padding = 5;
        for (List<Tile> row : fieldData.getField()) {
            for (Tile tile : row) {
                Rectangle newTile = new Rectangle(currentX + fieldPane.getWidth() * row.indexOf(tile),
                        currentY + fieldPane.getHeight() * fieldData.getField().indexOf(row),
                        fieldPane.getWidth() / fieldData.getHorizontalSize(),
                        fieldPane.getHeight() / fieldData.getVerticalSize());
                newTile.fillProperty().set(Color.DARKGREEN);
                if (tile.getType() == GRASS) {
                    newTile.fillProperty().set(Color.DARKGREEN);
                } else if (tile.getType() == OBSTACLE) {
                    newTile.fillProperty().set(Color.DARKGRAY);
                }

                fieldPane.getChildren().add(newTile);
            }
        }
    }


}