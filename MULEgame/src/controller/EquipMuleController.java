package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class EquipMuleController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	
	
	@Override
	public void onLoad() {
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		//MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		mapController = (MapController) myController.getController(mapID);
		ProgressBar mapBar = mapController.getTimerTask().getTimerBar();
		DoubleProperty progProp = mapBar.progressProperty();
		timerBarEquipMule.progressProperty().bind(progProp);
		
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;	
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML 
	public ProgressBar timerBarEquipMule;
	
	
	@FXML
	private Button equipFoodBTN;
	
	@FXML
	private Button equipEnergyBTN;
	
	@FXML
	private Button equipSmithoreBTN;
	
	@FXML
	private Button equipCrystiteBTN;
	
	@FXML
	private Button returnToTownBTN;


	public void equipFood(ActionEvent event) {
		
	}
	
	
	public void equipEnergy(ActionEvent event) {
		
	}
	
	
	public void equipSmithore(ActionEvent event) {
		
	}
	
	
	public void equipCrystite(ActionEvent event) {
		
	}
	
	public void returnToTown() {
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
}
