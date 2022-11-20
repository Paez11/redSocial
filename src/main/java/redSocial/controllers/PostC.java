package redSocial.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PostC implements Initializable {

    @FXML
    private Label username;

    @FXML
    private Label date;

    @FXML
    private Label content;

    @FXML
    private Button likes;

    @FXML
    private ImageView profileImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
