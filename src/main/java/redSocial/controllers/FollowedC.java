package redSocial.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import redSocial.DAO.FollowDao;
import redSocial.model.User;
import redSocial.utils.Windows;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FollowedC implements Initializable {
    @FXML
    private TextField nickname;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button Followbtn;


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
        if (Data.principalUser.getFollowed().contains(Data.aux)){
            Followbtn.setText("UnFollow");
        }
        nickname.setText(Data.aux.getName());

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

    public void switchPane(ActionEvent event){
        Object source = event.getSource();
        if (homeBtn.equals(source)) {
            go("Home",true);
        } else if (profileBtn.equals(source)) {
            go("Profile",true);
        } else if (newBtn.equals(source)) {
            go("CreatePost",false);
        } else if (logoutBtn.equals(source)) {
            go("LogIn",true);
        } else if (searchBtn.equals(source)) {
            Windows.mostrarAlerta("Error", "SearchBar", "No implementado");
        } else if (configBtn.equals(source)) {
            Windows.mostrarAlerta("Error", "Configuration", "No implementado");
        }
    }

    @FXML
    public void FollowUser(){
        if (Followbtn.getText().equals("Follow")){
            if (!Data.principalUser.getFollowed().contains(Data.aux)){
                Data.principalUser.getFollowed().add(Data.aux);
                FollowDao fd= new FollowDao(Data.principalUser, Data.aux);
                fd.save();
                Followbtn.setText("UnFollow");
            }
        }else if (Followbtn.getText().equals("UnFollow")){
            if (Data.principalUser.getFollowed().contains(Data.aux)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Dejar de Seguir");
                alert.setHeaderText("¿Estas seguro?");
                alert.setContentText("¿Estas seguro de que quieres dejar de seguir a este usuario?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    FollowDao fd= new FollowDao(Data.principalUser, Data.aux);
                    fd.deletebyusers(Data.principalUser, Data.aux);
                }
            }
        }
    }

    public void go(String fxml,boolean windowed){
        if (windowed) {
            App.loadScene(new Stage(), fxml, "RedSocial", false, false);
            App.closeScene((Stage) borderPane.getScene().getWindow());
        }else{
            App.loadScene(new Stage(), fxml, "redSocial", true, false);
        }
    }
}
