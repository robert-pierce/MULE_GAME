package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class mapConfigController implements Initializable, ControlledScreen {
	//-----------------Enum--------------------------------------------
		private enum MapType {MAP1, MAP2};
		
	
	//--------------Instance Variables---------------------------------
			ScreensController myController;
			MapType mapType = MapType.MAP1;
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
	@FXML
	private RadioButton mapOneBTN;
	
	@FXML
	private RadioButton mapTwoBTN;
	
	@FXML 
	private Button continueBTN;
	
	//--------------Event Handler Methods------------------------------
	
	public void setMapOne(ActionEvent event) {
		if (mapOneBTN.isSelected()) {
			mapType = MapType.MAP1;
			System.out.println("Map 1 selected");
		}
	}
	
	public void setMapTwo(ActionEvent event) {
		if (mapTwoBTN.isSelected()) {
			mapType = MapType.MAP2;
			System.out.println("Map 2 selected");
		}
	}
	
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Coninue Button Pressed");
		myController.setScreen(application.Main.playerConfigID);	
	}
}
