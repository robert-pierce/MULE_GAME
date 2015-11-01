package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class RecourceProductionController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController; 

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
