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

    opens redSocial.utils.Connection to java.xml.bind;
    opens redSocial.controllers to javafx.fxml;
    exports redSocial.controllers;
}