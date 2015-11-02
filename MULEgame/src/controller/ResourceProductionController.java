package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ResourceProductionController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController; 

	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onLoad() {
		List<Player> players = Main.game.getPlayerList();
        
		
		roundNumLBL.setText(Integer.toString(Main.game.getRoundNumber()));
		
		if(Main.game.getNumPlayers() < 2) {
			player2IdLBL.setOpacity(0);
			player2FoodLBL.setOpacity(0);
			player2EnergyLBL.setOpacity(0);		
			player2SmithoreLBL.setOpacity(0);
			player2FoodTagLBL.setOpacity(0);
			player2EnergyTagLBL.setOpacity(0);
			player2SmithoreTagLBL.setOpacity(0);
		}
		
		if(Main.game.getNumPlayers() < 3) {
			player3IdLBL.setOpacity(0);
			player3FoodLBL.setOpacity(0);
			player3EnergyLBL.setOpacity(0);		
			player3SmithoreLBL.setOpacity(0);
			player3FoodTagLBL.setOpacity(0);
			player3EnergyTagLBL.setOpacity(0);
			player3SmithoreTagLBL.setOpacity(0);
		}
		
		if(Main.game.getNumPlayers() < 4) {
			player4IdLBL.setOpacity(0);
			player4FoodLBL.setOpacity(0);
			player4EnergyLBL.setOpacity(0);		
			player4SmithoreLBL.setOpacity(0);
			player4FoodTagLBL.setOpacity(0);
			player4EnergyTagLBL.setOpacity(0);
			player4SmithoreTagLBL.setOpacity(0);
		}
		
		for (Player player : players) {
            if(player.getPlayerNum() == 1) {
            	player1FoodLBL.setText(Integer.toString(player.getFoodProduction()));
            	player1EnergyLBL.setText(Integer.toString(player.getEnergyProduction()));
            	player1SmithoreLBL.setText(Integer.toString(player.getOreProduction()));
            	
            } else if(player.getPlayerNum() == 2) {
            	player2FoodLBL.setText(Integer.toString(player.getFoodProduction()));
            	player2EnergyLBL.setText(Integer.toString(player.getEnergyProduction()));
            	player2SmithoreLBL.setText(Integer.toString(player.getOreProduction()));
            } else if(player.getPlayerNum() == 3) {
            	player3FoodLBL.setText(Integer.toString(player.getFoodProduction()));
            	player3EnergyLBL.setText(Integer.toString(player.getEnergyProduction()));
            	player3SmithoreLBL.setText(Integer.toString(player.getOreProduction()));
            } else if(player.getPlayerNum() == 4) {
            	player4FoodLBL.setText(Integer.toString(player.getFoodProduction()));
            	player4EnergyLBL.setText(Integer.toString(player.getEnergyProduction()));
            	player4SmithoreLBL.setText(Integer.toString(player.getOreProduction()));
            }
        }
	}

	
	@FXML
	private Label roundNumLBL;
	
	
	@FXML
	private Label player1IdLBL;
	
	@FXML
	private Label player1FoodLBL;
	
	@FXML
	private Label player1EnergyLBL;
	
	@FXML
	private Label player1SmithoreLBL;
	
	@FXML
	private Label player1FoodTagLBL;
	
	@FXML
	private Label player1EnergyTagLBL;
	
	@FXML
	private Label player1SmithoreTagLBL;
	
	
	@FXML
	private Label player2IdLBL;
	
	@FXML
	private Label player2FoodLBL;
	
	@FXML
	private Label player2EnergyLBL;
	
	@FXML
	private Label player2SmithoreLBL;
	
	@FXML
	private Label player2FoodTagLBL;
	
	@FXML
	private Label player2EnergyTagLBL;
	
	@FXML
	private Label player2SmithoreTagLBL;
	
	
	
	@FXML
	private Label player3IdLBL;
	
	
	@FXML
	private Label player3FoodLBL;
	
	@FXML
	private Label player3EnergyLBL;
	
	@FXML
	private Label player3SmithoreLBL;
	
	@FXML
	private Label player3FoodTagLBL;
	
	@FXML
	private Label player3EnergyTagLBL;
	
	@FXML
	private Label player3SmithoreTagLBL;
	
	
	@FXML
	private Label player4IdLBL;
	
	@FXML
	private Label player4FoodLBL;
	
	@FXML
	private Label player4EnergyLBL;
	
	@FXML
	private Label player4SmithoreLBL;
	
	@FXML
	private Label player4FoodTagLBL;
	
	@FXML
	private Label player4EnergyTagLBL;
	
	@FXML
	private Label player4SmithoreTagLBL;
	
	
	@FXML
	private Button continueBTN;

	
	public void continueBTNClick() {
		System.out.println("Continue Button Pressed in Resource Production Screen");
		myController.setScreen(Main.roundResultsID);
	}
	
}
