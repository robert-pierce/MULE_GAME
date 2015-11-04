package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Player;
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
import jfx.messagebox.MessageBox;

public class EquipMuleController implements Initializable, ControlledScreen, Loadable {
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
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
	
	public void saveState() {
		MessageBox.show(Main.game.getScene().getWindow(),
   			 "Game Saves are only allowed at the End of a Round",
 		         "Save Game",
 		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		//Main.game.saveState();
	}
}
