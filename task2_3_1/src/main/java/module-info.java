module ru.nsu.nikita.task2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.nikita to javafx.fxml;
    exports ru.nsu.nikita;
    exports ru.nsu.nikita.view;
    opens ru.nsu.nikita.view to javafx.fxml;
}