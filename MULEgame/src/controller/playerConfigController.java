package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import application.GameRunner.Difficulty;
import application.Main;
import application.Player;
import application.Player.Race;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class playerConfigController implements Initializable, ControlledScreen, Loadable{
	
	private static int numPlayers;
	
	private application.Player.Race race = Race.HUMAN;
	private Color color = Color.RED;
	private int playerINDEX = 1;
	private String playerName;
	private Difficulty difficulty;
	//private ArrayList<Player.Race> takenRaceList = new ArrayList<Player.Race>();
	private ArrayList<Color> takenColorList = new ArrayList<Color>();
	
	
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
	
	@Override
	public void onLoad() {
		difficulty = Main.game.getDifficulty();
		
	}

	//---------------FXML IDs------------------------------------------
	@FXML
	private Label playerConfigLabel;
	
	@FXML
	private TextField playerNameTXTFLD;
	
	@FXML
	private RadioButton raceHumanBTN;
	
	@FXML
	private RadioButton raceFlapperBTN;
	
	@FXML
	private RadioButton raceBonziodBTN;
	
	@FXML
	private RadioButton raceUgaiteBTN;
	
	@FXML
	private RadioButton raceBuzziteBTN;
	
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
		playerNameTXTFLD.setStyle("-fx-text-fill: green");
		System.out.println("Player name set to: " + playerName);
	}
	
	public void setRaceHuman(ActionEvent event) {
		race = application.Player.Race.HUMAN;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceFlapper(ActionEvent event) {
		race = application.Player.Race.FLAPPER;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceBonzoid(ActionEvent event) {
		race = application.Player.Race.BONZOID;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceUgaite(ActionEvent event) {
		race = application.Player.Race.UGAITE;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceBuzzite(ActionEvent event) {
		race = application.Player.Race.BUZZITE;
		System.out.println("Player race set to: " + race.toString());
	}
	
	
	public void setColorRed(ActionEvent event) {
		color = Color.RED;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorGreen(ActionEvent event) {
		color = Color.GREEN;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorPink(ActionEvent event) {
		color = Color.PINK;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorPurple(ActionEvent event) {
		color = Color.PURPLE;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Continue Button Pressed");
		
			
		if (playerName == null || playerName.equals("")) {
			System.out.println("Please enter a player name!");
				return;
		} else if (takenColorList.contains(color)) {
			System.out.println(color.toString() + " has already been chosen. Please pick another color.");
			return;
		} else {
			//takenRaceList.add(race);
			takenColorList.add(color);
		}
		
		
		if (playerINDEX != numPlayers) {
			Player player = new Player(race, color, playerName, playerINDEX, difficulty);
			application.Main.game.addPlayer(player);
			System.out.println("Player " + playerINDEX + " created and added to game");
			System.out.println(player);
			playerINDEX++;
			
			playerNameTXTFLD.setText("");
			playerNameTXTFLD.setStyle("-fx-text-fill: red");
			playerName = "";
			
		} else {
			Player player = new Player(race, color, playerName, playerINDEX, difficulty );
			application.Main.game.addPlayer(player);
			System.out.println("Player " + playerINDEX + " created and added to game");
			System.out.println(player);
			myController.setScreen(application.Main.mapConfigID);
		}
	}
	
	
	//---------------Getters and Setters-------------------------------
	public void setNumPlayers(int numPlyrs) {
		numPlayers = numPlyrs;
	}

	
	
	
	
}
