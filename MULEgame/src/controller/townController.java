package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class townController implements Initializable, ControlledScreen {
	
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
	private Button returnToMapLeftBTN;
	
	@FXML 
	private Button returnToMapRightBTN;
	
	public void returnToMap() {
		System.out.println("Returning to the map");
		myController.setScreen(application.Main.standardMapID);
	}
}


