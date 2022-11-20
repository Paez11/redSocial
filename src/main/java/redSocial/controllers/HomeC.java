package redSocial.controllers;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import redSocial.App;
import redSocial.model.User;
import redSocial.utils.DataService;
import redSocial.utils.Windows;
import redSocial.utils.contador.Counter;
import redSocial.utils.contador.Increment;
import redSocial.utils.contador.Read;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeC implements Initializable {

    private User user = DataService.user;
    private List<User> Followed = user.getFollowed();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<User> tblFollow;
    @FXML
    private TableColumn<User, String> clFollow;
    @FXML
    private Button Home;
    @FXML
    private Button Search;
    @FXML
    private Button New;
    @FXML
    private Button Settings;
    @FXML
    private Button Profile;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            Windows.closeRequest((Stage) anchorPane.getScene().getWindow());
        });
    }

    public void configuraTabla(){
        clFollow.setCellValueFactory(User ->{
            ObservableValue<String> ov = new SimpleStringProperty();
            ((ObjectProperty<String>) ov).setValue(User.getValue().getFollowed().get(-Followed.size()).getName());
            return ov;
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

    @FXML
    public void addPost(){
        try {
            new addPostC().initAddPost();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void switchToProfile(){
        App.loadScene(new Stage(), "Profile", "Profile", false, false);
        App.closeScene((Stage) anchorPane.getScene().getWindow());
    }


}
