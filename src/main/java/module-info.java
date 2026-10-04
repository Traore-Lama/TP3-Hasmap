module sio.tp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;

    opens sio.tp3.Model;
    opens sio.tp3 to javafx.fxml;
    exports sio.tp3;

}