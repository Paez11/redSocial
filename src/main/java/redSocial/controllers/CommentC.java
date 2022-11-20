package redSocial.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import redSocial.App;
import redSocial.DAO.CommentDao;
import redSocial.model.Comment;
import javafx.beans.property.SimpleStringProperty;







public class CommentC implements Initializable{
	@FXML
	private TableView<Comment> tableComment;
	@FXML
	private TableColumn<Comment, String> name;
	@FXML
	private TableColumn<Comment, String> text;
	@FXML
	private TableColumn<Comment, String> time;
	
	@FXML 
	private TextField txField;
	
	 @FXML
	    public void switchToHome(){
	        App.loadScene(new Stage(), "Home", "Home", false, false);
	        App.closeScene((Stage) tableComment.getScene().getWindow());
	    }
	
	public void updateTable() {
		ArrayList<Comment> comments =null; //dao;
		ObservableList<Comment> oList = FXCollections.observableArrayList(comments);
		
		
		name.setCellValueFactory(InfoComment -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(InfoComment.getValue().getUserComment().getName());
			return a;
		});
		
		text.setCellValueFactory(InfoComment -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(InfoComment.getValue().getTextComment());
			return a;
		});
		
		time.setCellValueFactory(InfoComment -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(InfoComment.getValue().getTime().toString());
			return a;
		});
		
		
		
		
		tableComment.getItems().addAll(oList);
	
	}
	
	@FXML
	private void newComment() {
		String texto=txField.getText();
		//recibir User
		//recibir Post
		CommentDao cd = new CommentDao(null, texto, null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateTable();
		
	}


}
