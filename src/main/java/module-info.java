module es.iesfranciscodelosrios.red {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires java.xml.bind;
    requires java.sql;
	requires javafx.graphics;

    opens redSocial to javafx.fxml;
    exports redSocial;
}