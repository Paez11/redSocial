package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import redSocial.model.DAO.CommentDao;
import redSocial.model.DAO.UserDao;
import redSocial.model.DataObject.Comment;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;

public class CommentC {

    private CommentDao c;

    @FXML
    private ImageView profileImage;
    @FXML
    protected static Button profilePhotoComment;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label name;
    @FXML
    private Label comment2;
    @FXML
    private Label date;

    public void setData(Comment comment){
        CommentDao cd= (CommentDao) new CommentDao().getById(comment.getId());
        UserDao ud= new UserDao(cd.getUser());
        name.setText(ud.getName());
        comment2.setText(comment.getTextComment());
        String format = new SimpleDateFormat("dd/MM/yyyy").format(comment.getDate());
        date.setText(format);
        this.c=cd;
        Data.aux= (UserDao) this.c.getUser();
        profileImage.setImage(new Image(new ByteArrayInputStream(Data.aux.getAvatar())));
    }

    public void switchProfile(){
        Data.aux= (UserDao) this.c.getUser();
        Data.caux = this.c;
        if (Data.principalUser.getId()==Data.aux.getId()) {
            App.loadScene(new Stage(), "Profile", "RedSocial", false, false);
            App.closeScene((Stage) anchorPane.getScene().getWindow());
        }else{
            App.loadScene(new Stage(), "Followed", "RedSocial", false, false);
            App.closeScene((Stage) anchorPane.getScene().getWindow());
        }
    }
}
