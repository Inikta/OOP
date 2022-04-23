module ru.nsu.nikita.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens ru.nsu.nikita.view to javafx.fxml;
    exports ru.nsu.nikita.view;
}