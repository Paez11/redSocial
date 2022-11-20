package redSocial.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import redSocial.model.User;
import redSocial.utils.Windows;
import redSocial.utils.contador.Counter;
import redSocial.utils.contador.Increment;
import redSocial.utils.contador.Read;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeC implements Initializable {



    //List<User> followed = Data.principalUser.getFollowed();

    //private ObservableList<User> observableUsers = FXCollections.observableArrayList(followed);

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
    private TableColumn<String,String> followedColumn;

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //followedTable.setItems(observableUsers);

        followedList();
        refresh();

        Platform.runLater(()->{
            Windows.closeRequest((Stage) borderPane.getScene().getWindow());
        });
    }

    public void followedList(){
        followedColumn.setCellValueFactory(follower ->{
            SimpleStringProperty ssp = new SimpleStringProperty();
            ssp.setValue(follower.getValue());
            return ssp;
        });
    }

    @FXML
    public void Contador(){
        Counter c = new Counter();

        Thread inc = new Increment(c);
        Thread read = new Read(c);

        inc.start();
        read.start();
    }


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

    public void go(String fxml,boolean windowed){
        if (windowed) {
            App.loadScene(new Stage(), fxml, "RedSocial", false, false);
            App.closeScene((Stage) borderPane.getScene().getWindow());
        }else{
            App.loadScene(new Stage(), fxml, "redSocial", true, false);
        }
    }

    public void refresh() {
        /*
        observableUsers.removeAll(observableUsers);
        observableUsers.addAll(followed);

         */
    }
}
