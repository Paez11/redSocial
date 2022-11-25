package redSocial.utils;

import javafx.stage.FileChooser;

import java.io.File;

public class Tools {
	/**
	 * Selecciona la ubicación de una imagen en local
	 * @return la ruta de la imagen seleccionada o null si no la encuentra
	 */
    public static String getFilePathFromFileChooser() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpg", "*.jpeg", "*.png"));
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        } else {
            Log.info("No file selected");
            return null;
        }
    }
}
