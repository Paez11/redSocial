package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import redSocial.DAO.PostDao;
import redSocial.DAO.UserDao;
import redSocial.utils.Windows;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class PostC implements Initializable {

    private PostDao p;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label username;

    @FXML
    private Label date;

    @FXML
    private Label content;

    @FXML
    private Label editLabel;

    @FXML
    private ToggleButton likes;

    @FXML
    private Button comments;

    @FXML
    private Button delete;

    @FXML
    private Button update;

    @FXML
    private ImageView profileImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setDataPost(p);
        editLabel.setVisible(false);
    }

    public void setDataPost(PostDao p){
        UserDao aux2 = new UserDao();
        aux2 = (UserDao) aux2.getById(p.getUserName().getId());
        username.setText(aux2.getName());
        //profileImage.setImage(new Image(aux2.getAvatar()));
        content.setText(p.getText());
        String format = new SimpleDateFormat("dd/MM/yyyy").format(p.getDateCreate());
        date.setText(format);
        this.p = p;
        if (Data.principalUser.getId()==p.getUserName().getId()) {
            delete.setVisible(true);
            update.setVisible(true);
        }else{
            delete.setVisible(false);
            update.setVisible(false);
        }
    }

    public void deletePost(){
        if (Data.principalUser.getId()==p.getUserName().getId()){
            p.delete();
        }else {
            Windows.mostrarAlerta("ERROR","ERROR","No puedes borrar este post");
        }
    }

    public void updatePost(){
        if (Data.principalUser.getId()==p.getUserName().getId()){
            App.loadScene(new Stage(), "UpdatePost", "RedSocial", true, false);
        }else {
            Windows.mostrarAlerta("ERROR","ERROR","No puedes editar este post");
        }
    }

    public void openComments(){
        Data.p = this.p;
        App.loadScene(new Stage(), "Comments", "RedSocial", true, false);
    }

    public void switchProfile(){
        Data.aux= (UserDao) this.p.getUserName();
        Data.p = this.p;
        if (Data.principalUser.getId()==Data.aux.getId()) {
            App.loadScene(new Stage(), "Profile", "RedSocial", false, false);
            App.closeScene((Stage) anchorPane.getScene().getWindow());
        }else{
            App.loadScene(new Stage(), "Followed", "RedSocial", false, false);
            App.closeScene((Stage) anchorPane.getScene().getWindow());
        }
    }


}
