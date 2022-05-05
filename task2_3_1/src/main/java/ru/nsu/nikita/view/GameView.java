package ru.nsu.nikita.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ru.nsu.nikita.view.field_view.FieldView;

public class GameView {

    @FXML
    private HBox root;

    @FXML
    private FieldView fieldView;

    @FXML
    private Label currentScoreLabel;
    @FXML
    private Label goalScoreLabel;

    @FXML
    private Button restartButton;


}
