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
		public enum MapSelection {MAP1, MAP2, MAP3};
		
	
	//--------------Instance Variables---------------------------------
			ScreensController myController;
			MapSelection map = MapSelection.MAP1;
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
	private RadioButton mapThreeBTN;
	
	@FXML 
	private Button continueBTN;
	
	//--------------Event Handler Methods------------------------------
	
	public void setMapOne(ActionEvent event) {
		if (mapOneBTN.isSelected()) {
			map = MapSelection.MAP1;
			System.out.println("Map 1 selected");
		}
	}
	
	public void setMapTwo(ActionEvent event) {
		if (mapTwoBTN.isSelected()) {
			map = MapSelection.MAP2;
			System.out.println("Map 2 selected");
		}
	}
	
	public void setMapThree(ActionEvent event) {
		if (mapThreeBTN.isSelected()) {
			map = MapSelection.MAP3;
			System.out.println("Map 3 selected");
		}
	}
	
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Coninue Button Pressed");
		
		// add the map to the game
		application.Main.game.addMap(map);
		System.out.println("Map set to " + map.toString() + ".");
		
		
		myController.setScreen(application.Main.placeHolderID);	
	}
}
