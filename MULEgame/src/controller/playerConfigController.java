package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Player;
import application.Player.Color;
import application.Player.Race;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class playerConfigController implements Initializable, ControlledScreen{
	
	private static int numPlayers;
	
	private application.Player.Race race = Race.RACE1;
	private application.Player.Color color = Color.RED;
	private int playerINDEX = 1;
	private String playerName;
	
	private ArrayList<Player.Race> takenRaceList = new ArrayList<Player.Race>();
	private ArrayList<Player.Color> takenColorList = new ArrayList<Player.Color>();
	
	
	//--------------Instance Variables---------------------------------
		ScreensController myController;
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
	private Label playerConfigLabel;
	
	
	@FXML
	private TextField playerNameTXTFLD;
	
	@FXML
	private RadioButton raceOneBTN;
	
	@FXML
	private RadioButton raceTwoBTN;
	
	@FXML
	private RadioButton raceThreeBTN;
	
	@FXML
	private RadioButton raceFourBTN;
	
	@FXML
	private RadioButton colorRedBTN;
	
	@FXML
	private RadioButton colorGreenBTN;
	
	@FXML
	private RadioButton colorPinkBTN;
	
	@FXML
	private RadioButton colorPurpleBTN;
	
	
	
	
	//--------------Event Handler Methods------------------------------

	public void testText(ActionEvent event) {
		System.out.println("Yes");
	}
	
	public void getPlayerName(ActionEvent event) {
		playerName = playerNameTXTFLD.getText();
		System.out.println("Player name set to: " + playerName);
	}
	
	public void setRaceOne(ActionEvent event) {
		race = application.Player.Race.RACE1;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceTwo(ActionEvent event) {
		race = application.Player.Race.RACE2;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceThree(ActionEvent event) {
		race = application.Player.Race.RACE3;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceFour(ActionEvent event) {
		race = application.Player.Race.RACE4;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setColorRed(ActionEvent event) {
		color = application.Player.Color.RED;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorGreen(ActionEvent event) {
		color = application.Player.Color.GREEN;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorPink(ActionEvent event) {
		color = application.Player.Color.PINK;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorPurple(ActionEvent event) {
		color = application.Player.Color.PURPLE;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Continue Button Pressed");
		
	//	if (playerName.isEmpty()) {
		//	System.out.println("Please enter a player name!");
		//	return;
		//} else 	
			
		if (takenRaceList.contains(race)) {
			System.out.println(race.toString() + " has already been chosen. Please pick another race.");
			return;
		} else if (takenColorList.contains(color)) {
			System.out.println(color.toString() + " has already been chosen. Please pick another color.");
			return;
		} else {
			takenRaceList.add(race);
			takenColorList.add(color);
		}
		
		
		
		if (playerINDEX != numPlayers) {
			Player player = new Player(race, color, playerName, playerINDEX );
			application.Main.game.addPlayer(player);
			System.out.println("Player " + playerINDEX + " created and added to game");
			playerINDEX++;
			
			playerNameTXTFLD.setText("");
			
		} else {
			Player player = new Player(race, color, playerName, playerINDEX );
			application.Main.game.addPlayer(player);
			System.out.println("Player " + playerINDEX + " created and added to game");
			myController.setScreen(application.Main.mapConfigID);
		}
	}
	
	
	//---------------Getters and Setters-------------------------------
	public void setNumPlayers(int numPlyrs) {
		numPlayers = numPlyrs;
	}
	
	
	
}
