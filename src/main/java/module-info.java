module com.example.cafeautomation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;


    opens com.example.cafeautomation to javafx.fxml;
    exports com.example.cafeautomation;
}