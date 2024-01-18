module com.example.freshthreads {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.google.gson;

    opens com.example.freshthreads to javafx.fxml;
    exports com.example.freshthreads;
}