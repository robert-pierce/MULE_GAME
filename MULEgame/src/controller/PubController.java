package controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import application.Map.MapSelection;
import application.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import jfx.messagebox.MessageBox;

public class PubController implements Initializable, ControlledScreen, Loadable {
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button gambleBTN;
	
	@FXML
	private Button returnToTownBTN;


	public void gamble(ActionEvent event) {
		String mapID = "";
		MapController mapController;
		
		System.out.println("Player is gambling!");
		dispurseGamblingWinnings();
		
		// this block handles the transition to the next player's turn
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
		myController.setScreen(mapID);
		mapController.endTurn();
		
	}
	
	
	public void returnToTown() {
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
	
	
	private void dispurseGamblingWinnings() {
		Random rand = new Random();
		Player player = Main.game.getActivePlayer();
		int roundNum = Main.game.getRoundNumber();
		int timeBonus = getTimeBonus();	
		int randomNum = rand.nextInt(timeBonus);
		System.out.println("the random number in PubController is: " + randomNum);
		System.out.println("the round num in PubController is: " + roundNum);
		
		int winnings = roundNum*rand.nextInt(timeBonus) ;    // calculate winnings
		System.out.println("winnings in PubController is: " + winnings);
		// add winnings
		player.addMoney(winnings);
	
		//make announcement 
		MessageBox.show(Main.game.getScene().getWindow(),
				"Player " + player.getPlayerNum() + ", you won $" + winnings + " gambling!",
				"Information dialog",
				MessageBox.ICON_INFORMATION | MessageBox.OK);
		
	}
	
	// THIS METHOD IS NOT IMPLEMENTED FOR TIMER FUNCTIONALITY
	private int getTimeBonus() {
		return 150;
	}
}
