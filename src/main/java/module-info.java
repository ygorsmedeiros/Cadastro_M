module com.ygor.cadastromestre {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ygor.cadastromestre to javafx.fxml;
    opens com.ygor.cadastromestre.Controller to javafx.fxml;
    exports com.ygor.cadastromestre;
}
