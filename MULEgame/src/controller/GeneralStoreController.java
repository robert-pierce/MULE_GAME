package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Player;
import application.Map.MapSelection;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class GeneralStoreController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	
	
	@Override
	public void onLoad() {
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		mapController = (MapController) myController.getController(mapID);
		ProgressBar mapBar = mapController.getTimerTask().getTimerBar();
		DoubleProperty progProp = mapBar.progressProperty();
		timerBarGeneralStore.progressProperty().bind(progProp);	

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
	public ProgressBar timerBarGeneralStore;
	
	
	@FXML 
	private Button sellFoodBTN;
	
	@FXML 
	private Button sellEnergyBTN;
	
	@FXML 
	private Button  sellSmithoreBTN;
	
	@FXML 
	private Button  sellCrystiteBTN;
	
	@FXML 
	private Button  buyFoodBTN;
	
	@FXML 
	private Button  buyEnergyBTN;
	
	@FXML 
	private Button  buySmithoreBTN;
	
	@FXML 
	private Button  buyCrystiteBTN;
	
	@FXML 
	private Button returnToTownBTN;
	
	
	
	
	public void sellFoodClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Sell Food BTN Clicked ");
		
		player.sellFood();
		
	}
	
	public void sellEnergyClick() {
		System.out.println("Sell Energy BTN Clicked ");
	}
	
	public void sellSmithoreClick() {
		System.out.println("Sell Smithore BTN Clicked ");
	}
	
	public void sellCrystiteClick() {
		System.out.println("Sell Crystite BTN Clicked ");
	}
	
	public void buyFoodClick() {
		System.out.println("Buy Food BTN Clicked ");
	}
	
	public void buyEnergyClick() {
		System.out.println("Buy Energy BTN Clicked ");
	}
	
	public void buySmithoreClick() {
		System.out.println("Buy Smithore BTN Clicked ");
	}
	
	public void buyCrystiteClick() {
		System.out.println("Buy Crystite BTN Clicked ");
	}
	
	
	public void returnToTown() {
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
}
