package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;

public class MapController implements Initializable, ControlledScreen {
	ScreensController myController;
	double xCor, yCor;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@FXML	
	public void mouseClick(MouseEvent mouseEvent) {
		xCor = mouseEvent.getX();
		System.out.println(xCor);
		yCor = mouseEvent.getY();	
	}
	
}
