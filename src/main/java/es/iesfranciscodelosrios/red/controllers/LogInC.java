package es.iesfranciscodelosrios.red.controllers;

import es.iesfranciscodelosrios.red.DAO.UserDao;
import es.iesfranciscodelosrios.red.model.User;
import es.iesfranciscodelosrios.red.utils.Valid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

import static es.iesfranciscodelosrios.red.App.loadScene;

public class LogInC {
    @FXML
    private TextField Nickname;
    @FXML
    private PasswordField Password;
    @FXML
    private Button LogIn;
    @FXML
    private Button SignUp;
    private UserDao userDao=new UserDao();

    @FXML
    public void LogIn(){
        if (Valid.passwordMatched(Password)){
            String encrypt=Valid.sha256(Password.getText());
            User user = userDao.getUser(Nickname.getText(),encrypt);
            if (user!=null){
                loadScene(new Stage(),"Home","Home",false,false);
            }else{
                //no existe el usuario y tiene que logearse
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No existe el usuario");
                alert.setContentText("El usuario no existe");
            }
        }else{
            //no ha introducido una contraseña o nickname correctos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Datos erroneos");
            alert.setContentText("No ha introducido una contraseña o nickname correctos");
        }
    }

    @FXML
    private void switchToRegister(ActionEvent event) throws IOException {
        try {
            //aqui llama a la ventana emergente para poder logearse
            loadScene(new Stage(),"SignUp","SignUp",false,false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
