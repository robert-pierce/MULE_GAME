package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML 
	private Button startBTN;
	
	public void startGame(ActionEvent event) {
		System.out.println("YES IT WORKDED!");
	}
		
		
	

}
