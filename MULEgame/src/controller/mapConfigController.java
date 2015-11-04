package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import jfx.messagebox.MessageBox;
import application.Main;
import application.GameRunner.ActivePlayer;
import application.Map.MapSelection;

public class mapConfigController implements Initializable, ControlledScreen {
	//-----------------Enum--------------------------------------------

		
	
	//--------------Instance Variables---------------------------------
			ScreensController myController;
			MapSelection map = MapSelection.STANDARD;
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
	private RadioButton mapStandardBTN;
	
	@FXML
	private RadioButton mapEastWestBTN;
	
	@FXML
	private RadioButton mapThreeBTN;
	
	@FXML 
	private Button continueBTN;
	
	@FXML
	public MenuBar menuBar;
	
	@FXML
	public MenuItem saveMenuItem;
	
	
	//--------------Event Handler Methods------------------------------
	
	public void setMapStandard(ActionEvent event) {
		if (mapStandardBTN.isSelected()) {
			map = MapSelection.STANDARD;
			System.out.println("STANDARD map selected");
		}
	}
	
	public void setMapEastWest(ActionEvent event) {
		if (mapEastWestBTN.isSelected()) {
			map = MapSelection.EASTWEST;
			System.out.println("EAST WEST map selected");
		}
	}
	
	public void setMapThree(ActionEvent event) {
		if (mapThreeBTN.isSelected()) {
			map = MapSelection.MAP3;
			System.out.println("Map 3 selected");
		}
	}
	
	public ScreensController getMapConfigController() {
		return myController;
	}
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Coninue Button Pressed");
		
		
		//System.out.println("Map set to " + map.toString() + ".");
		
		// start the game
		System.out.println("Starting Game!");
		Main.game.startGame(map);
	}
	
	public void saveState() {
		MessageBox.show(Main.game.getScene().getWindow(),
   			 "Game Saves are only allowed at the End of a Round",
 		         "Save Game",
 		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		//Main.game.saveState();
	}
}
