package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class map1Controller  implements Initializable, ControlledScreen {

	ScreensController myController;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
}
