module com.restaurante1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.restaurante1 to javafx.fxml;
    exports com.restaurante1;
}