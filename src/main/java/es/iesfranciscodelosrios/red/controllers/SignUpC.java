package es.iesfranciscodelosrios.red.controllers;

import es.iesfranciscodelosrios.red.DAO.UserDao;
import es.iesfranciscodelosrios.red.model.User;
import es.iesfranciscodelosrios.red.utils.Valid;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static es.iesfranciscodelosrios.red.App.loadScene;

public class SignUpC {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField Password2;
    @FXML
    private Button SignUp;
    private UserDao userDao=new UserDao();

    @FXML
    public void LogIn(){
        if (Valid.passwordMatched(Password)) {
            if (Password.getText().equals(Password2.getText())) {
                User user = new User();
                user.setPassword(Valid.sha256(Password.getText()));
                user.setName(Username.getText());
                user.setAvatar("");
                userDao.save(user);
                loadScene(new Stage(), "Home", "Home", false, false);
            } else {
                //no ha introducido una contraseña o nickname correcto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Datos erroneos");
                alert.setContentText("No ha introducido una contraseña o nickname correctos");

            }
        }
    }
}
