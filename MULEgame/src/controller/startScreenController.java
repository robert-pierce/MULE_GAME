package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class startScreenController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;	
	}
	
	@FXML 
	private Button startBTN;
	
	public void startGame(ActionEvent event) {
		myController.setScreen(application.Main.gameConfigID);
	}

	
	
}







	
	
	
		
		
	


