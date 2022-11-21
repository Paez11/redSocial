package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button likes;

    @FXML
    private Button comments;

    @FXML
    private ImageView profileImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setDataPost(p);
    }

    public void setDataPost(PostDao p){
        UserDao aux2 = new UserDao();
        aux2 = (UserDao) aux2.getById(p.getUserName().getId());
        username.setText(aux2.getName());
        content.setText(p.getText());
        String format = new SimpleDateFormat("dd/MM/yyyy").format(p.getDateCreate());
        date.setText(format);
        this.p = p;

    }

    private void deletePost(){
        if (Data.principalUser.getId()==p.getUserName().getId()){
            p.delete();
        }else {
            Windows.mostrarAlerta("ERROR","ERROR","No puedes borrar este post");
        }
    }

    public void openComments(){
        Data.p = this.p;
        App.loadScene(new Stage(), "Comments", "RedSocial", true, false);
    }

    public void switchProfile(){
        Data.aux= (UserDao) this.p.getUserName();
        Data.p = this.p;
        App.loadScene(new Stage(), "Followed", "RedSocial", false, false);
    }


}
