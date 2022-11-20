package redSocial.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import redSocial.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import redSocial.utils.Valid;
import redSocial.utils.Windows;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileC implements Initializable {

    //List<User> followed = Data.principalUser.getFollowed();

    //private ObservableList<User> observableUsers = FXCollections.observableArrayList(followed);

    @FXML
    private TextField nickname;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView profileImage;

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
    private TableView<User> followedTable;

    @FXML
    private TableColumn<User,String> followedColumn;

    @FXML
    private BorderPane borderPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nickname.setText(Data.principalUser.getName());

        //followedTable.setItems(observableUsers);

        //followedList();
        //refresh();



        Platform.runLater(()->{
            Windows.closeRequest((Stage) borderPane.getScene().getWindow());
        });
    }

    /*
    public void followedList(){
        followedColumn.setCellValueFactory(follower ->{
            SimpleStringProperty ssp = new SimpleStringProperty();
            ssp.setValue(follower.getValue().getName());
            return ssp;
        });
    }

     */

    public void editNickName(ActionEvent event){
        String newNickName = "";
        Data.principalUser.update();
        nickname.setText(newNickName);
    }

    public void editPassword(ActionEvent event){
        String newPassword = "";
        newPassword = Valid.sha256(String.valueOf(password));
        Data.principalUser.update();
        password.setText(newPassword);
    }

    public void editPhoto(ActionEvent event){

    }

    public void switchPane(ActionEvent event){
        Object source = event.getSource();
        if (homeBtn.equals(source)) {
            go("Home");
        } else if (profileBtn.equals(source)) {
            go("Profile");
        } else if (newBtn.equals(source)) {
            go("Post");
        } else if (logoutBtn.equals(source)) {
            go("LogIn");
        } else if (searchBtn.equals(source)) {
            Windows.mostrarAlerta("Error", "SearchBar", "No implementado");
        } else if (configBtn.equals(source)) {
            Windows.mostrarAlerta("Error", "Configuration", "No implementado");
        }
    }

    public void go(String fxml){
        App.loadScene(new Stage(), fxml, "redSocial", false, false);
        App.closeScene((Stage) borderPane.getScene().getWindow());
    }


    /*
    public void refresh() {
        observableUsers.removeAll(observableUsers);
        observableUsers.addAll(followed);
    }
    */
}

