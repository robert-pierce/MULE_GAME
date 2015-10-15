package controller;

import java.net.URL;
import java.util.Random;
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
import jfx.messagebox.MessageBox;

public class PubController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	
	
	@Override
	public void onLoad() {
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		mapController = (MapController) myController.getController(mapID);
		ProgressBar mapBar = mapController.getTimerTask().getTimerBar();
		DoubleProperty progProp = mapBar.progressProperty();
		timerBarPub.progressProperty().bind(progProp);	
		
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
	public ProgressBar timerBarPub;
	
	@FXML
	private Button gambleBTN;
	
	@FXML
	private Button returnToTownBTN;


	public void gamble(ActionEvent event) {
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		
		
		
		mapController = (MapController) myController.getController(mapID);
		
		System.out.println("Player is gambling!");
		dispurseGamblingWinnings(mapController);
		
		myController.setScreen(mapID);
		mapController.endTurn();
		
	}
	
	
	public void returnToTown() {
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
	
	
	private void dispurseGamblingWinnings(MapController mapController) {
		Random rand = new Random();
		Player player = Main.game.getActivePlayer();
		int roundBonus = getRoundBonus(Main.game.getRoundNumber());
		double timeLeft = mapController.stopTimer();
		int timeBonus = getTimeBonus(timeLeft);	
		System.out.println(" the time bonus is " + timeBonus);
		int randomNum = rand.nextInt(timeBonus);
		System.out.println("the random number in PubController is: " + randomNum);
		System.out.println("the round bonus in PubController is: " + roundBonus);
		
		int winnings = roundBonus+randomNum;    // calculate winnings
		System.out.println("winnings in PubController is: " + winnings);
	
		if(winnings > 250) {
			winnings = 250;
		}
		
		// add winnings
		player.addMoney(winnings);
	
		//make announcement 
		MessageBox.show(Main.game.getScene().getWindow(),
				"Player " + player.getPlayerNum() + ", you won $" + winnings + " gambling!",
				"Information dialog",
				MessageBox.ICON_INFORMATION | MessageBox.OK);
		
	}
	
	// THIS METHOD IS NOT IMPLEMENTED FOR TIMER FUNCTIONALITY
	private int getTimeBonus(double timeLeft) {
		System.out.println("In Pub Controller, the time Left is: " + timeLeft);
		
		if (timeLeft >= 37 ) {
			return 200;
		} else if (timeLeft >= 25) {
			return 150;
		} else if (timeLeft >= 12) {
			return 100;
		} else {
			return 50;
		}
	}
	
	private int getRoundBonus(int roundNum) {
		if (roundNum == 12 ) {
			return 200;
		} else if (roundNum >= 8) {
			return 150;
		} else if (roundNum >= 4) {
			return 100;
		} else {
			return 50;
		}
	}
}
