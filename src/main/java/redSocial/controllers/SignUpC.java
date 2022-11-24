package redSocial.controllers;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import redSocial.DAO.UserDao;
import redSocial.Start;
import redSocial.utils.Tools;
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
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
                password = Valid.sha256(password);
                UserDao user = new UserDao(username,password);
                try {
                    user.setAvatar((Start.class.getResourceAsStream("user.png").readAllBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Data.principalUser = user;
                Data.principalUser.save();
                App.loadScene(new Stage(), "LogIn", "RedSocial", false, false);
                App.closeScene((Stage) anchorPane.getScene().getWindow());
            }else{
                Windows.mostrarAlerta("Error","Error","Las contraseñas no coinciden");
            }
        }
    }
    public void enter(){
        Password2.setOnKeyPressed( event -> {
            if(event.getCode() == KeyCode.ENTER) {
                SignUp();
            }
        });
    }
    @FXML
    private void switchToLogIn(ActionEvent event) throws IOException {
        App.loadScene(new Stage(),"LogIn","RedSocial",false,false);
        App.closeScene((Stage) anchorPane.getScene().getWindow());
    }

}
