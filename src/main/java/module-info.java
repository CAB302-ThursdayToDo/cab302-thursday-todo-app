module com.example.hometaskplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hometaskplanner to javafx.fxml;
    exports com.example.hometaskplanner;
}