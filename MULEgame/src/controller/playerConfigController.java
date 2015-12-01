package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import jfx.messagebox.MessageBox;
import application.GameRunner.Difficulty;
import application.Main;
import application.Player;
import application.Player.Race;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;

public class playerConfigController implements Initializable, ControlledScreen, Loadable{
	private static int numPlayers;
	private application.Player.Race race = Race.HUMAN;
	private Color color = Color.RED;
	private int playerINDEX = 1;
	private String playerName;
	private Difficulty difficulty;
	private ArrayList<Color> takenColorList = new ArrayList<Color>();
	
	ScreensController myController;
	AudioClip click, radio_Click;

	
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
	
	@FXML
	public MenuBar menuBar;
	
	@FXML
	public MenuItem saveMenuItem;
	
	@FXML
	private Label playerNumLBL;
	
	//--------------Event Handler Methods------------------------------

	public void testText(ActionEvent event) {
		System.out.println("Yes");
	}
	
	public void getPlayerName(ActionEvent event) {
		new AudioClip(new File(Main.game.getTextBarClickURL()).toURI().toString()).play();
		playerName = playerNameTXTFLD.getText();
		
		if (color == color.RED) {
			playerNameTXTFLD.setStyle("-fx-text-fill: red");
		} else if (color == color.GREEN) {
			playerNameTXTFLD.setStyle("-fx-text-fill: green");
		} else if (color == color.PINK) {
			playerNameTXTFLD.setStyle("-fx-text-fill: pink");
		} else if (color == color.PURPLE) {
			playerNameTXTFLD.setStyle("-fx-text-fill: purple");
		}
		
		System.out.println("Player name set to: " + playerName);
	}
	
	public void setRaceHuman(ActionEvent event) {
		playRadioClick();
		race = application.Player.Race.HUMAN;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceFlapper(ActionEvent event) {
		playRadioClick();
		race = application.Player.Race.FLAPPER;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceBonzoid(ActionEvent event) {
		playRadioClick();
		race = application.Player.Race.BONZOID;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceUgaite(ActionEvent event) {
		playRadioClick();
		race = application.Player.Race.UGAITE;
		System.out.println("Player race set to: " + race.toString());
	}
	
	public void setRaceBuzzite(ActionEvent event) {
		playRadioClick();
		race = application.Player.Race.BUZZITE;
		System.out.println("Player race set to: " + race.toString());
	}
	
	
	public void setColorRed(ActionEvent event) {
		playRadioClick();
		color = Color.RED;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorGreen(ActionEvent event) {
		playRadioClick();
		color = Color.GREEN;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorPink(ActionEvent event) {
		playRadioClick();
		color = Color.PINK;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void setColorPurple(ActionEvent event) {
		playRadioClick();
		color = Color.PURPLE;
		System.out.println("Player color set to: " + color.toString());
	}
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Continue Button Pressed");
		click = new AudioClip(new File(Main.game.getClickURL()).toURI().toString());
		click.play();
			
		if (playerNameTXTFLD.getText() == null || playerNameTXTFLD.getText().equals("")) {
			System.out.println("Please enter a player name!");
			MessageBox.show(Main.game.getScene().getWindow(),
        			"Please Enter a Player Name.",
      		         "Enter a Player Name",
      		         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return;
		} else if (takenColorList.contains(color)) {
			System.out.println(color.toString() + " has already been chosen. Please pick another color.");
			MessageBox.show(Main.game.getScene().getWindow(),
        			"That color has already been chosen. Please pick another color.",
      		         "Pick Another Color",
      		         MessageBox.ICON_INFORMATION | MessageBox.OK);
        
			return;
		} else {
			//takenRaceList.add(race);
			takenColorList.add(color);
			
			if (color == color.RED) {
				colorRedBTN.setOpacity(0);
			} else if (color == color.GREEN) {
				colorGreenBTN.setOpacity(0);
			} else if (color == color.PINK) {
				colorPinkBTN.setOpacity(0);
			} else if (color == color.PURPLE) {
				colorPurpleBTN.setOpacity(0);
			}
			
		}
		
		
		if (playerINDEX != numPlayers) {
			Player player = new Player(race, color, playerName, playerINDEX, difficulty);
			application.Main.game.addPlayer(player);
			System.out.println("Player " + playerINDEX + " created and added to game");
			System.out.println(player);
			playerINDEX++;
			
			playerNameTXTFLD.setText("");
			playerNameTXTFLD.setStyle("-fx-text-fill: black");
			playerName = "";
			playerNumLBL.setText(Integer.toString(playerINDEX));
			
		} else {
			Player player = new Player(race, color, playerName, playerINDEX, difficulty );
			application.Main.game.addPlayer(player);
			System.out.println("Player " + playerINDEX + " created and added to game");
			System.out.println(player);
			myController.setScreen(application.Main.mapConfigID);
		}
	}
	
	public void playRadioClick() {
		radio_Click = new AudioClip(new File(Main.game.getRadioClickURL()).toURI().toString());
		radio_Click.play();
	}
	
	
	public void saveState() {
		MessageBox.show(Main.game.getScene().getWindow(),
   			 "Game Saves are only allowed at the End of a Round",
 		         "Save Game",
 		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		//Main.game.saveState();
	}
	
	
	//---------------Getters and Setters-------------------------------
	public void setNumPlayers(int numPlyrs) {
		numPlayers = numPlyrs;
	}

	
	
	
	
}
