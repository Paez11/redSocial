module es.iesfranciscodelosrios.red {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.iesfranciscodelosrios.red to javafx.fxml;
    exports es.iesfranciscodelosrios.red;
}