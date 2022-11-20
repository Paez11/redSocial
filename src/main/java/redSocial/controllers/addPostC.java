package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import redSocial.App;
import redSocial.DAO.PostDao;
import redSocial.model.Post;
import redSocial.model.User;
import redSocial.utils.DataService;

import java.io.IOException;
import java.time.LocalDate;

public class addPostC {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextArea Texto;
    @FXML
    private Button Publish;


    public void initAddPost() throws IOException {
        stage= new Stage();
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("addPost.fxml"));
        scene= new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }

    @FXML
    public void PostClose(){
        Post nPost = new Post();
        User user = DataService.user;
        LocalDate created = LocalDate.now();
        java.sql.Date sqlDate= java.sql.Date.valueOf(created);
        nPost.setText(Texto.getText());
        nPost.setDateCreate(sqlDate);
        nPost.setUserName(user);
        nPost.setDateUpdate(null);
        PostDao Post = new PostDao(nPost.getUserName(),nPost.getDateCreate(),nPost.getDateUpdate(),nPost.getText());
        Post.save();
    }
}
