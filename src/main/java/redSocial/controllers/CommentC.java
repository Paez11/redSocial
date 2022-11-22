package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import redSocial.model.Comment;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class CommentC {
    @FXML
    private Label name;
    @FXML
    private Label comment2;
    @FXML
    private Label date;

    public void setData(Comment comment){
        name.setText(comment.getUserComment().getName());
        comment2.setText(comment.getTextComment());
        String format = new SimpleDateFormat("dd/MM/yyyy").format(comment.getDate());
        date.setText(format);
    }
}
