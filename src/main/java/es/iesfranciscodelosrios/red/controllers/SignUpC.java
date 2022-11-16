package es.iesfranciscodelosrios.red.controllers;

import es.iesfranciscodelosrios.red.App;
import es.iesfranciscodelosrios.red.DAO.UserDao;
import es.iesfranciscodelosrios.red.model.User;
import es.iesfranciscodelosrios.red.utils.Valid;
import es.iesfranciscodelosrios.red.utils.Windows;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static es.iesfranciscodelosrios.red.App.loadScene;

public class SignUpC implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField Password2;
    @FXML
    private Button SignUp;
    @FXML
    private Button LogIn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            Windows.closeRequest((Stage) anchorPane.getScene().getWindow());
        });
    }

    @FXML
    public void SignUp(){
        String username = Username.getText();
        String password = Password.getText();
        String password2 = Password2.getText();

        if (username.isEmpty() || password.isEmpty() || password2.isEmpty()){
            Windows.mostrarAlerta("Error","Error","Rellene todos los campos");
        }else{
            if (password.equals(password2)){
                password = Valid.sha256(String.valueOf(Password));
                UserDao user = new UserDao(username,password);
                user.save();
                App.loadScene(new Stage(), "LogIn", "LogIn", false, false);
            }else{
                Windows.mostrarAlerta("Error","Error","Las contraseñas no coinciden");
            }
        }
    }

    @FXML
    private void switchToLogIn(ActionEvent event) throws IOException {
        App.loadScene(new Stage(),"LogIn","LogIn",false,false);
        App.closeScene((Stage) anchorPane.getScene().getWindow());
    }

}
