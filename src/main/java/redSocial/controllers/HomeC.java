package redSocial.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import redSocial.model.User;
import redSocial.utils.Log;
import redSocial.utils.Windows;
import redSocial.utils.contador.Counter;
import redSocial.utils.contador.Increment;
import redSocial.utils.contador.Read;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class HomeC implements Initializable {

    Counter c = new Counter();

    Thread inc = new Increment(c);
    Thread read = new Read(c);

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
    private Button loadMore;

    @FXML
    private TableView<User> followedTable;

    @FXML
    private TableColumn<String,String> followedColumn;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox pnItems = null;

    @FXML
    private Label contadorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //followedTable.setItems(observableUsers);

        inc.setDaemon(true);
        read.setDaemon(true);

        loadPosts();
        Contador();

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

        inc.start();
        read.start();

        if (read.isAlive()){
            contadorLabel.setText(String.valueOf(c.getVal()));
        }

    }

    public void loadPosts(){

        Node[] nodes = new Node[4];
        pnItems.setSpacing(15);
        for (int i = 0; i < nodes.length; i++){
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("Post.fxml"));

                final int j = i;

                nodes[i].setOnMouseEntered(event ->{
                    nodes[j].setStyle("-fx-background-color: #091dce");
                });
                nodes[i].setOnMouseExited(event ->{
                    nodes[j].setStyle("-fx-background-color: #f3bcbc");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                Log.severe("Error al cargar los posts "+e.getMessage());
            }
        }
    }

    public void refreshPosts(){

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
            inc.stop();
            read.stop();
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
