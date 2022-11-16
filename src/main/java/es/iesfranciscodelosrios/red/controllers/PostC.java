package es.iesfranciscodelosrios.red.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import es.iesfranciscodelosrios.red.model.Post;
import es.iesfranciscodelosrios.red.model.User;

public class PostC implements Initializable {
 
    @FXML
    private Label username;
    
    @FXML
    private Label date;

    @FXML
    private Label text;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private HBox likeContainer;


    //private long startTime = 0;
    //private Reactions currentReaction;
    private Post post;

    /*@FXML
    public void onLikeContainerPressed(MouseEvent me){
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent me){
        if(System.currentTimeMillis() - startTime > 500){
            reactionsContainer.setVisible(true);
        }else {
            if(reactionsContainer.isVisible()){
                reactionsContainer.setVisible(false);
            }
            if(currentReaction == Reactions.NON){
                setReaction(Reactions.LIKE);
            }else{
                setReaction(Reactions.NON);
            }
        }
    }*/

 
    public void setData(Post post){
        this.post = post;
        username.setText(post.getText());
        date.setText(post.getDateCreate().toString());
        text.setText(post.getText());
    }

   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setData();
    }
}
