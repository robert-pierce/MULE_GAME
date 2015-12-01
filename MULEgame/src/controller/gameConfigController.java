package controller;

import java.io.File;
import java.net.URL;
import application.GameRunner.Difficulty;
import application.Main;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.media.AudioClip;
import jfx.messagebox.MessageBox;

public class gameConfigController implements Initializable, ControlledScreen {	
	//--------------Instance Variables---------------------------------
	private ScreensController myController;
	private Difficulty difficulty = Difficulty.BEGINNER;
	ScreensController mainController;
	Node playerConfigCntrl;
	int numPlayers = 1;
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
	
	//---------------FXML IDs------------------------------------------
	
	@FXML
	private RadioButton onePlayersBTN;
	
	@FXML
	private RadioButton twoPlayersBTN;
	
	@FXML
	private RadioButton threePlayersBTN;
	
	@FXML
	private RadioButton fourPlayersBTN;
	
	@FXML 
	private RadioButton difficultyBeginnerBTN;
	
	@FXML 
	private RadioButton difficultyStandardBTN;
	
	@FXML 
	private RadioButton difficultyTournamentBTN;
	
	@FXML 
	private Button continueBTN;
	
	@FXML
	public MenuBar menuBar;
	
	@FXML
	public MenuItem saveMenuItem;
	
	//-----------------------------------------------------------------
	
	
	//--------------Event Handler Methods------------------------------
	
	public void setOnePlayers(ActionEvent event) {
		if (onePlayersBTN.isSelected()) {
			playRadioClick();
			numPlayers = 1;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	public void setTwoPlayers(ActionEvent event) {
		if (twoPlayersBTN.isSelected()) {
			playRadioClick();
			numPlayers = 2;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	
	public void setThreePlayers(ActionEvent event) {
		if (threePlayersBTN.isSelected()) {
			playRadioClick();
			numPlayers = 3;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	
	public void setFourPlayers(ActionEvent event) {
		if (fourPlayersBTN.isSelected()) {
			playRadioClick();
			numPlayers = 4;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	public void setBeginnerDifficulty(ActionEvent event) {
		if (difficultyBeginnerBTN.isSelected()) {
			playRadioClick();
			difficulty = Difficulty.BEGINNER;
			System.out.println("Beginner Difficulty Set");
		}
	}
	
	public void setStandardDifficulty(ActionEvent event) {
		if (difficultyStandardBTN.isSelected()) {
			playRadioClick();
			difficulty = Difficulty.STANDARD;
			System.out.println("Standard Difficulty Set");
		}
	}
	
	public void setTournamentDifficulty(ActionEvent event) {
		if (difficultyTournamentBTN.isSelected()) {
			playRadioClick();
			difficulty = Difficulty.TOURNAMENT;
			System.out.println("Tournament Difficulty Set");
		}
	}
	
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Continue Button Pressed");
		new AudioClip(new File(Main.game.getClickURL()).toURI().toString()).play();
		
		
		// This block will get the playerConfigController and pass to it the number of players
		
		try{
			FXMLLoader loader = new FXMLLoader( getClass().getResource("/view/PlayerConfig.fxml"));
			Parent loadScreen = (Parent) loader.load();
			playerConfigController playerController = loader.getController();
			playerController.setNumPlayers(numPlayers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// add the difficulty to the game
		application.Main.game.addDifficulty(difficulty);
		System.out.println("Game set to " + difficulty.toString() + " difficulty.");
		
		// close this screens and open the next screen
		myController.setScreen(application.Main.playerConfigID);	
	}
	
	public void playRadioClick() {
		new AudioClip(new File(Main.game.getRadioClickURL()).toURI().toString()).play();
	}
	
	public void saveState() {
		MessageBox.show(Main.game.getScene().getWindow(),
   			 "Game Saves are only allowed at the End of a Round",
 		         "Save Game",
 		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		//Main.game.saveState();
	}
	
	//------------------Getter Methods--------------------------------
	
	

}
