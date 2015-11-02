package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class RoundResultsController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController; 

	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onLoad() {
	
	}
	

}
