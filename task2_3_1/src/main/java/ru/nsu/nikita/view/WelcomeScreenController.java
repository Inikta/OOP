package ru.nsu.nikita.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {

    @FXML
    AnchorPane welcomeScreen;

    @FXML
    TextField sizeXField;

    @FXML
    TextField sizeYField;

    @FXML
    TextField spawnXField;

    @FXML
    TextField spawnYField;

    @FXML
    Button applyButton;

    private StringProperty sizeX;
    private StringProperty sizeY;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }

    @FXML
    public void sizeXChange() {
        sizeX.set(sizeXField.getText());
    }

    @FXML
    public void sizeYChange() {
        sizeY.set(sizeYField.getText());
    }

    @FXML
    public void applyChanges() {
        welcomeScreen.visibleProperty().set(false);
    }
}
