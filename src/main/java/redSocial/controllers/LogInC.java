package redSocial.controllers;

import redSocial.DAO.UserDao;
import redSocial.utils.Valid;
import redSocial.utils.Windows;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInC implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField Nickname;
    @FXML
    private PasswordField Password;
    @FXML
    private Button LogIn;
    @FXML
    private Button SignUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            Windows.closeRequest((Stage) anchorPane.getScene().getWindow());
        });
    }

    @FXML
    public void LogIn(){

        String nickname = Nickname.getText();
        String password = Password.getText();

        if (nickname.isEmpty() || password.isEmpty()){
            Windows.mostrarAlerta("Error","Error","Rellene todos los campos");
        }else{
            password = Valid.sha256(String.valueOf(Password));
            Data.principalUser.getByName(nickname);
            if (Data.principalUser!=null){
                App.loadScene(new Stage(), "Home", "RedSocial", false, false);
                App.closeScene((Stage) anchorPane.getScene().getWindow());
            }else{
                Windows.mostrarAlerta("Error", "Usuario o contraseña incorrectos", "Usuario o contraseña incorrectos");
                Nickname.setText("");
                Password.setText("");
            }
        }
    }

    @FXML
    private void switchToRegister(ActionEvent event) throws IOException {
        App.loadScene(new Stage(),"SignUp","RedSocial",false,false);
        App.closeScene((Stage) anchorPane.getScene().getWindow());
    }

}
