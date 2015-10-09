package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Map.MapSelection;
import application.Player;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;

public class BuyMuleController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	private Player activePlayer;
	
	@Override
	public void onLoad() {
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		//MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		mapController = (MapController) myController.getController(mapID);
		ProgressBar mapBar = mapController.getTimerTask().getTimerBar();
		DoubleProperty progProp = mapBar.progressProperty();
		timerBarBuyMule.progressProperty().bind(progProp);	
		
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
	public ProgressBar timerBarBuyMule;
	
	@FXML
	private Button buyMuleBTN;
	
	@FXML
	private Button returnToTownBTN;

	public void buyMuleClick(ActionEvent event) {
		activePlayer = Main.game.getActivePlayer();
        activePlayer.buyMule();
	}
	
	
	public void returnToTown() {
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
	
}
