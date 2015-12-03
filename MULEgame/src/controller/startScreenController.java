package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class startScreenController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	private Media introMusic;
	private MediaPlayer soundPlayer;
	private AudioClip click;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;	
	}
	
	@Override
	public void onLoad() {
		File soundFile = new File("Sounds/Intro_Music.mp3");
		introMusic = new Media(soundFile.toURI().toString());
		soundPlayer = new MediaPlayer(introMusic);
		soundPlayer.play();
		soundPlayer.setCycleCount(soundPlayer.INDEFINITE);
	}
	
	@FXML 
	private Button newGameBTN;
	
	@FXML 
	private Button loadGameBTN;
	
	public void startGame(ActionEvent event) {
		new AudioClip(new File(Main.game.getClickURL()).toURI().toString()).play();
		//click.play();
		myController.setScreen(application.Main.gameConfigID);
	}

	public void loadGame(ActionEvent event) {
		new AudioClip(new File(Main.game.getClickURL()).toURI().toString()).play();
		Main.game.loadState();
		Main.game.stopThemeMusic();
	}
	
	public MediaPlayer getSoundPlayer() {
		return soundPlayer;
		
	}

	

	
}







	
	
	
		
		
	


