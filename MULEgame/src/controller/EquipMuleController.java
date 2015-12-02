package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import jfx.messagebox.MessageBox;

public class EquipMuleController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	private Player activePlayer;
	MediaPlayer soundPlayer;
	AudioClip click;
	File soundFile;
	
	
	@Override
	public void onLoad() {
		if (soundPlayer == null) {
			soundFile = new File(Main.game.getFactoryAmbianceURL());
			soundPlayer = new MediaPlayer(new Media(soundFile.toURI().toString()));
			soundPlayer.setCycleCount(soundPlayer.INDEFINITE);
			soundPlayer.setVolume(0);
			soundPlayer.play();
		} 
		startMusic();
		
		
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		//MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		mapController = (MapController) myController.getController(mapID);
		ProgressBar mapBar = mapController.getTimerTask().getTimerBar();
		DoubleProperty progProp = mapBar.progressProperty();
		timerBarEquipMule.progressProperty().bind(progProp);
		
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
		
		roundNumLBL.setText(Integer.toString(Main.game.getRoundNumber()));
		
		
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
	public ProgressBar timerBarEquipMule;
	
	@FXML
	private Label roundNumLBL;
	
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
	
	@FXML
	private Button equipFoodBTN;
	
	@FXML
	private Button equipEnergyBTN;
	
	@FXML
	private Button equipSmithoreBTN;
	
	@FXML
	private Button equipCrystiteBTN;
	
	@FXML
	private Button returnToTownBTN;
	
	@FXML
	public MenuBar menuBar;
	
	@FXML
	public MenuItem saveMenuItem;


	public void equipFood(ActionEvent event) {
		activePlayer = Main.game.getActivePlayer();
        activePlayer.equipFood();
	}
	
	
	public void equipEnergy(ActionEvent event) {
		activePlayer = Main.game.getActivePlayer();
        activePlayer.equipEnergy();
	}
	
	
	public void equipSmithore(ActionEvent event) {
		activePlayer = Main.game.getActivePlayer();
        activePlayer.equipSmithore();
	}
	
	
	public void equipCrystite(ActionEvent event) {
		activePlayer = Main.game.getActivePlayer();
        activePlayer.equipCrystite();
	}
	
	public void returnToTown() {
		new AudioClip(new File(Main.game.getClickURL()).toURI().toString()).play();
		silenceMusic();
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
	
	private void startMusic() {
		final DoubleProperty volume = soundPlayer.volumeProperty();
		Timeline fadeMusic = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(volume, 0)),
				new KeyFrame(new Duration(2000), new KeyValue(volume, 0.5)));
		
		fadeMusic.play();
	}
	
	
	public void silenceMusic() {
		final DoubleProperty volume = soundPlayer.volumeProperty();		
		Timeline fadeMusic = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(volume, 0.5)),
				new KeyFrame(new Duration(2000), new KeyValue(volume, 0.0)));
		
		fadeMusic.play();
	}
	
	
	public void saveState() {
		MessageBox.show(Main.game.getScene().getWindow(),
   			 "Game Saves are only allowed at the End of a Round",
 		         "Save Game",
 		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		//Main.game.saveState();
	}
}
