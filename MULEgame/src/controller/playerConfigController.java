package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.gameConfigController.Difficulty;
import javafx.fxml.Initializable;

public class playerConfigController implements Initializable, ControlledScreen{

	//--------------Instance Variables---------------------------------
		ScreensController myController;
	//-----------------------------------------------------------------
		
	//--------------Interface Overrides--------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;	
	}

	//---------------FXML IDs------------------------------------------

}
