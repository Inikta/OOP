module ru.nsu.nikita.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens ru.nsu.nikita.view to javafx.fxml;
    exports ru.nsu.nikita.view;
    exports ru.nsu.nikita.view.snake_view;
    opens ru.nsu.nikita.view.snake_view to javafx.fxml;
    exports ru.nsu.nikita.view.field_view;
    opens ru.nsu.nikita.view.field_view to javafx.fxml;
    exports ru.nsu.nikita.view.application;
    opens ru.nsu.nikita.view.application to javafx.fxml;
    exports ru.nsu.nikita.view.screens_controllers;
    opens ru.nsu.nikita.view.screens_controllers to javafx.fxml;
}