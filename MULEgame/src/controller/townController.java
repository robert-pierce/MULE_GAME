package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

//import javax.swing.plaf.synth.SynthSeparatorUI;
import application.Main;
import application.Plot;
import application.Map.MapSelection;
import application.Player;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;

public class townController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	private double xCor, yCor;
	Timer myTimer;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onLoad() {
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		//MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		mapController = (MapController) myController.getController(mapID);
		ProgressBar mapBar = mapController.getTimerTask().getTimerBar();
		DoubleProperty progProp = mapBar.progressProperty();
		timerBarTown.progressProperty().bind(progProp);	
		
		Player player = Main.game.getActivePlayer();
		
		SimpleIntegerProperty playerNumProperty = player.getPlayerIdProperty();
		playerIdLBL.textProperty().bind(Bindings.convert(playerNumProperty));
		
		SimpleIntegerProperty playerMoneyProperty = player.getMoneyProperty();
		playerMoneyLBL.textProperty().bind(Bindings.convert(playerMoneyProperty));
		
		SimpleIntegerProperty playerFoodProperty = player.getFoodProperty();
		playerFoodLBL.textProperty().bind(Bindings.convert(playerFoodProperty));
		
		SimpleIntegerProperty playerEnergyProperty = player.getEnergyProperty();
		playerEnergyLBL.textProperty().bind(Bindings.convert(playerEnergyProperty));
		
		SimpleIntegerProperty playerSmithoreProperty = player.getSmithoreProperty();
		playerSmithoreLBL.textProperty().bind(Bindings.convert(playerSmithoreProperty));
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;	
	}

	
	@FXML 
	public ProgressBar timerBarTown;
	
	@FXML 
	private Button returnToMapLeftBTN;
	
	@FXML 
	private Button returnToMapRightBTN;
	
	@FXML
	private Label playerIdLBL;
	
	@FXML
	private Label playerMoneyLBL;
	
	@FXML
	private Label playerFoodLBL;
	
	@FXML
	private Label playerEnergyLBL;
	
	@FXML
	private Label playerSmithoreLBL;
	
	public void returnToMap() {
		String mapID = "";
		MapController mapController;
		System.out.println("Player is gambling!");
		MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		switch (mapSelection) {
		case STANDARD:
			mapID = Main.standardMapID;
			break;
		case EASTWEST:
			mapID = Main.eastWestMapID;
			break;
		case MAP3:
			mapID = ""; 
		}
		mapController = (MapController) myController.getController(mapID);
		
		System.out.println("Returning to the map");
		myController.setScreen(mapID);
		
		
		//myController.setScreen(application.Main.standardMapID);
	}
	
	@FXML	
	public void mouseClick(MouseEvent mouseEvent) {
		int msgBoxRslt;
		Plot currentPlot;
		//gameState = retrieveGameState();
		//activePlayerState = retrieveActivePlayerState();
		//numPlayers = retrieveNumPlayers();
		//map = Main.game.getMap();
		
		// get (x,y) coordinates
		xCor = mouseEvent.getX();
		yCor = mouseEvent.getY();	
		
		System.out.println("Xcor: " + xCor);
		System.out.println("YCor: " + yCor);
		
		processMouseClick(xCor, yCor);
		
		
	}
	
	public void processMouseClick(double xCor, double yCor ) {
		if ( xCor >= 53 && xCor <= 1120 && yCor >= 275 && yCor <= 573) {
			if (xCor <= 275 &&  yCor <= 571) {
				showPub();
			} 
			if (xCor >= 341 && xCor <= 555 && yCor >=326 && yCor <= 527) {
				showBuyMuleShop();
			}
			if (xCor >= 660 && xCor <= 869 && yCor >=326 && yCor <= 530) {
				showEquipMuleShop();
			}
			if (xCor >= 914 && xCor <= 1116 && yCor >=310 && yCor <= 533) {
				showShop();
			}
		}
	}
	
	
	private void showPub() {
		myController.setScreen(application.Main.pubID);
	}
	
	private void showBuyMuleShop() {
		myController.setScreen(application.Main.buyMuleID);
	}
	
	private void showEquipMuleShop() {
		myController.setScreen(application.Main.equipMuleID);
	}
	
	private void showShop() {
		myController.setScreen(application.Main.generalStoreID);
	}
}


