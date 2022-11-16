package es.iesfranciscodelosrios.red.controllers;

import es.iesfranciscodelosrios.red.App;
import es.iesfranciscodelosrios.red.DAO.UserDao;
import es.iesfranciscodelosrios.red.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileC implements Initializable {

    private UserDao userDao=new UserDao();

    List<User> followed = userDao.getFollowed();

    private ObservableList<User> observableUsers = FXCollections.observableArrayList(followed);

    @FXML
    private Button photoEditBtn;

    @FXML
    private Button nickNameEditBtn;

    @FXML
    private Button passwordEditBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button newBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private Button configBtn;

    @FXML
    private TableView<UserDao> followedTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exit(){
        goExit();
    }

    private void goExit(){
        App.loadScene(new Stage(),"LogIn","redSocial",false,false);
        App.closeScene((Stage) logoutBtn.getScene().getWindow());
    }



    public void refresh() {
        observableUsers.removeAll(observableUsers);
        observableUsers.addAll(followed);
    }
}
