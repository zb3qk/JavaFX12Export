module strangeExe.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens hellofx to javafx.fxml;
    exports hellofx;
}