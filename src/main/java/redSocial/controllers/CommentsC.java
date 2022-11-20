package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import redSocial.DAO.CommentDao;
import redSocial.DAO.PostDao;
import redSocial.model.Comment;
import redSocial.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommentsC  implements Initializable {
    @FXML
    private GridPane commentGrid;
    @FXML
    private Label nickname;
    @FXML
    private TextArea CommentText;

    private List<Comment> comments;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comments= new ArrayList<>(comments());
        int columns=0;
        int rows=1;

        try{
            for (int i = 0; i < comments.size(); i++){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Comment.fxml"));
                VBox box = fxmlLoader.load();
                CommentC commentC= fxmlLoader.getController();
                commentC.setData(comments.get(i));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<Comment> comments(){
        User u=Data.principalUser;
        PostDao p= Data.p;
        CommentDao cd = new CommentDao();
        List<Comment> lc = new ArrayList<>();
        lc=cd.getAllByPost(p);

        return lc;
    }
}
