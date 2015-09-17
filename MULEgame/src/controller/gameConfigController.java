package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class gameConfigController implements Initializable, ControlledScreen {

	//----------------ENUM --------------------------------------------
	public enum Difficulty {BEGINNER, NORMAL};
	
	//--------------Instance Variables---------------------------------
	ScreensController myController;
	Difficulty difficulty;
	int numPlayers;
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
	private RadioButton difficultyNormalBTN;
	
	@FXML 
	private Button continueBTN;
	
	//-----------------------------------------------------------------
	
	
	//--------------Event Handler Methods------------------------------
	
	public void setOnePlayers(ActionEvent event) {
		if (onePlayersBTN.isSelected()) {
			numPlayers = 1;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	public void setTwoPlayers(ActionEvent event) {
		if (twoPlayersBTN.isSelected()) {
			numPlayers = 2;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	
	public void setThreePlayers(ActionEvent event) {
		if (threePlayersBTN.isSelected()) {
			numPlayers = 3;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	
	public void setFourPlayers(ActionEvent event) {
		if (fourPlayersBTN.isSelected()) {
			numPlayers = 4;
			System.out.println("Number of Players: " + numPlayers);
		}
	}
	
	public void setBeginnerDifficulty(ActionEvent event) {
		if (difficultyBeginnerBTN.isSelected()) {
			difficulty = Difficulty.BEGINNER;
			System.out.println("Beginner Difficulty Set");
		}
	}
	
	public void setNormalDifficulty(ActionEvent event) {
		if (difficultyNormalBTN.isSelected()) {
			difficulty = Difficulty.NORMAL;
			System.out.println("Normal Difficulty Set");
		}
	}
	
	
	public void nextScreen(ActionEvent event) {
		System.out.println("Coninue Button Pressed");
		myController.setScreen(application.Main.playerConfigID);
		
	}

}
