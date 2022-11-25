package redSocial.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Windows {
	
	/**
	 * muestra una alerta de peligro
	 * @param title titulo de la alerta
	 * @param header encabezado de la alerta
	 * @param description descripción de la alerta
	 */
    public static void mostrarAlerta(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    /**
	 * muestra una alerta de información
	 * @param title titulo de la alerta
	 * @param header encabezado de la alerta
	 * @param description descripción de la alerta
	 */
    public static void mostrarInfo(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    /**
     * muestra una alerta para confirmar cerrar el programa
     * @param stage ventana que se cerrara
     */
    public static void closeRequest(Stage stage){
        stage.setOnCloseRequest(windowEvent -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmacion de cierre");
            a.setHeaderText("¿Esta seguro de salir del programa?");
            Stage s =(Stage)a.getDialogPane().getScene().getWindow();
            s.initOwner(stage);
            s.toFront();
            a.showAndWait().filter(buttonType -> buttonType== ButtonType.OK).ifPresentOrElse(buttonType -> {
                Platform.exit();},windowEvent::consume);
        });
    }
}
