package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private Button publish;
    @FXML
    private Button back;

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
                AnchorPane anchorPane = fxmlLoader.load();
                CommentC commentC= fxmlLoader.getController();
                commentC.setData(comments.get(i));

                commentGrid.add(anchorPane, columns++, rows);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void publishComment(){
        CommentDao cd = new CommentDao();
        cd.setTextComment(CommentText.getText());
        cd.setUserComment(Data.principalUser);
        cd.setPost(Data.p);
        cd.save();
    }

    private List<CommentDao> comments(){
        List<CommentDao> ls = CommentDao.getAllByPost(Data.p);

        return ls;
    }
}
