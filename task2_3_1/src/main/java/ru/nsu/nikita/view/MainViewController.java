package ru.nsu.nikita.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.nsu.nikita.backlogic.field.Field;
import ru.nsu.nikita.view.field.FieldController;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private Field field;

    @FXML
    private AnchorPane mainViewScreen;

    @FXML
    private VBox playingField;

    @FXML
    private VBox stats;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        field = new Field(10, 10);
    }
}