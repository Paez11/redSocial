package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import redSocial.DAO.CommentDao;
import redSocial.DAO.UserDao;
import redSocial.model.Comment;

import java.text.SimpleDateFormat;

public class CommentC {
    @FXML
    private Label name;
    @FXML
    private Label comment2;
    @FXML
    private Label date;

    public void setData(Comment comment){
        CommentDao cd= (CommentDao) new CommentDao().getById(comment.getId());
        UserDao ud= new UserDao(cd.getUserComment());
        name.setText(ud.getName());
        comment2.setText(comment.getTextComment());
        String format = new SimpleDateFormat("dd/MM/yyyy").format(comment.getDate());
        date.setText(format);
    }
}
