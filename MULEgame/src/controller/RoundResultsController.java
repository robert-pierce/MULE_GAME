package controller;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jfx.messagebox.MessageBox;

public class RoundResultsController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController; 
	boolean endOfGameFlag = false;
	
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
		List<Player> tempPlayerList = new ArrayList<Player>(Main.game.getPlayerList());
		int roundNum = Main.game.getRoundNumber();
		
		Collections.sort(tempPlayerList);
		
		if (roundNum == Main.game.getNumRounds()) {
			endOfGameFlag = true;
			roundNumLBL.setText("Game Over");
		} else {
			roundNumLBL.setText(Integer.toString(roundNum));
		}
		
		if(Main.game.getNumPlayers() < 2) {
			secondPlaceTagLBL.setOpacity(0);
			secondPlaceIdTagLBL.setOpacity(0);
			secondPlaceIdLBL.setOpacity(0);		
			secondPlaceScoreTagLBL.setOpacity(0);
			secondPlaceScoreLBL.setOpacity(0);
			secondPlaceMoneyTagLBL.setOpacity(0);
			secondPlaceMoneyLBL.setOpacity(0);
			secondPlacePlotsTagLBL.setOpacity(0);
			secondPlacePlotsLBL.setOpacity(0);
			secondPlaceFoodTagLBL.setOpacity(0);		
			secondPlaceFoodLBL.setOpacity(0);
			secondPlaceEnergyTagLBL.setOpacity(0);
			secondPlaceEnergyLBL.setOpacity(0);
			secondPlaceSmithoreTagLBL.setOpacity(0);
			secondPlaceSmithoreLBL.setOpacity(0);
			secondPlaceCrystiteTagLBL.setOpacity(0);
			secondPlaceCrystiteLBL.setOpacity(0);
		}
		
		if(Main.game.getNumPlayers() < 3) {
			thirdPlaceTagLBL.setOpacity(0);
			thirdPlaceIdTagLBL.setOpacity(0);
			thirdPlaceIdLBL.setOpacity(0);		
			thirdPlaceScoreTagLBL.setOpacity(0);
			thirdPlaceScoreLBL.setOpacity(0);
			thirdPlaceMoneyTagLBL.setOpacity(0);
			thirdPlaceMoneyLBL.setOpacity(0);
			thirdPlacePlotsTagLBL.setOpacity(0);
			thirdPlacePlotsLBL.setOpacity(0);
			thirdPlaceFoodTagLBL.setOpacity(0);		
			thirdPlaceFoodLBL.setOpacity(0);
			thirdPlaceEnergyTagLBL.setOpacity(0);
			thirdPlaceEnergyLBL.setOpacity(0);
			thirdPlaceSmithoreTagLBL.setOpacity(0);
			thirdPlaceSmithoreLBL.setOpacity(0);
			thirdPlaceCrystiteTagLBL.setOpacity(0);
			thirdPlaceCrystiteLBL.setOpacity(0);
		}
		
		if(Main.game.getNumPlayers() < 4) {
			fourthPlaceTagLBL.setOpacity(0);
			fourthPlaceIdTagLBL.setOpacity(0);
			fourthPlaceIdLBL.setOpacity(0);		
			fourthPlaceScoreTagLBL.setOpacity(0);
			fourthPlaceScoreLBL.setOpacity(0);
			fourthPlaceMoneyTagLBL.setOpacity(0);
			fourthPlaceMoneyLBL.setOpacity(0);
			fourthPlacePlotsTagLBL.setOpacity(0);
			fourthPlacePlotsLBL.setOpacity(0);
			fourthPlaceFoodTagLBL.setOpacity(0);		
			fourthPlaceFoodLBL.setOpacity(0);
			fourthPlaceEnergyTagLBL.setOpacity(0);
			fourthPlaceEnergyLBL.setOpacity(0);
			fourthPlaceSmithoreTagLBL.setOpacity(0);
			fourthPlaceSmithoreLBL.setOpacity(0);
			fourthPlaceCrystiteTagLBL.setOpacity(0);
			fourthPlaceCrystiteLBL.setOpacity(0);
		}
		
		for (int i = 0; i < tempPlayerList.size(); i++) {
			if ( (i + 1) == 1) {
				firstPlaceIdLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlayerNum()));		
				firstPlaceScoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getScore()));
				firstPlaceMoneyLBL.setText("$" + Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getMoney()));
				firstPlacePlotsLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlots().size()));
				firstPlaceFoodLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getFood()));
				firstPlaceEnergyLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getEnergy()));
				firstPlaceSmithoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getSmithore()));
				firstPlaceCrystiteLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getCrystite()));
			
			} else if ((i + 1) == 2) {
				secondPlaceIdLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlayerNum()));		
				secondPlaceScoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getScore()));
				secondPlaceMoneyLBL.setText("$" + Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getMoney()));
				secondPlacePlotsLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlots().size()));
				secondPlaceFoodLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getFood()));
				secondPlaceEnergyLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getEnergy()));
				secondPlaceSmithoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getSmithore()));
				secondPlaceCrystiteLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getCrystite()));
			
			} else if ((i + 1) == 3) {
				thirdPlaceIdLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlayerNum()));		
				thirdPlaceScoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getScore()));
				thirdPlaceMoneyLBL.setText("$" + Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getMoney()));
				thirdPlacePlotsLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlots().size()));
				thirdPlaceFoodLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getFood()));
				thirdPlaceEnergyLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getEnergy()));
				thirdPlaceSmithoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getSmithore()));
				thirdPlaceCrystiteLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getCrystite()));
			
			} else if ((i + 1) == 4) {
				fourthPlaceIdLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlayerNum()));		
				fourthPlaceScoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getScore()));
				fourthPlaceMoneyLBL.setText("$" + Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getMoney()));
				fourthPlacePlotsLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getPlots().size()));
				fourthPlaceFoodLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getFood()));
				fourthPlaceEnergyLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getEnergy()));
				fourthPlaceSmithoreLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getSmithore()));
				fourthPlaceCrystiteLBL.setText(Integer.toString(tempPlayerList.get(tempPlayerList.size() - (i + 1)).getCrystite()));
			}
		}
		
	}
	
	
	@FXML
	private Label endOfRoundLBL;

	@FXML
	private Label roundNumLBL;
	
	@FXML
	private Label firstPlaceTagLBL;
	
	@FXML
	private Label secondPlaceTagLBL;
	
	@FXML
	private Label thirdPlaceTagLBL;

	@FXML
	private Label fourthPlaceTagLBL;
		
	@FXML
	private Label firstPlaceIdTagLBL;
	
	@FXML
	private Label secondPlaceIdTagLBL;
	
	@FXML
	private Label thirdPlaceIdTagLBL;
	
	@FXML
	private Label fourthPlaceIdTagLBL;

	@FXML
	private Label firstPlaceIdLBL;
	
	@FXML
	private Label secondPlaceIdLBL;
	
	@FXML
	private Label thirdPlaceIdLBL;
	
	@FXML
	private Label fourthPlaceIdLBL;

	@FXML
	private Label firstPlaceScoreTagLBL;

	@FXML
	private Label secondPlaceScoreTagLBL;
	
	@FXML
	private Label thirdPlaceScoreTagLBL;

	@FXML
	private Label fourthPlaceScoreTagLBL;
	
	@FXML
	private Label firstPlaceScoreLBL;

	@FXML
	private Label secondPlaceScoreLBL;
		
	@FXML
	private Label thirdPlaceScoreLBL;
	
	@FXML
	private Label fourthPlaceScoreLBL;
	
	@FXML
	private Label firstPlaceMoneyTagLBL;

	@FXML
	private Label secondPlaceMoneyTagLBL;
	
	@FXML
	private Label thirdPlaceMoneyTagLBL;

	@FXML
	private Label fourthPlaceMoneyTagLBL;
	
	@FXML
	private Label firstPlaceMoneyLBL;

	@FXML
	private Label secondPlaceMoneyLBL;
		
	@FXML
	private Label thirdPlaceMoneyLBL;
	
	@FXML
	private Label fourthPlaceMoneyLBL;
	
	@FXML
	private Label firstPlacePlotsTagLBL;

	@FXML
	private Label secondPlacePlotsTagLBL;
	
	@FXML
	private Label thirdPlacePlotsTagLBL;

	@FXML
	private Label fourthPlacePlotsTagLBL;
	
	@FXML
	private Label firstPlacePlotsLBL;

	@FXML
	private Label secondPlacePlotsLBL;
		
	@FXML
	private Label thirdPlacePlotsLBL;
	
	@FXML
	private Label fourthPlacePlotsLBL;
	
	@FXML
	private Label firstPlaceFoodTagLBL;

	@FXML
	private Label secondPlaceFoodTagLBL;
	
	@FXML
	private Label thirdPlaceFoodTagLBL;

	@FXML
	private Label fourthPlaceFoodTagLBL;
	
	@FXML
	private Label firstPlaceFoodLBL;

	@FXML
	private Label secondPlaceFoodLBL;
		
	@FXML
	private Label thirdPlaceFoodLBL;
	
	@FXML
	private Label fourthPlaceFoodLBL;

	@FXML
	private Label firstPlaceEnergyTagLBL;

	@FXML
	private Label secondPlaceEnergyTagLBL;
	
	@FXML
	private Label thirdPlaceEnergyTagLBL;

	@FXML
	private Label fourthPlaceEnergyTagLBL;
	
	@FXML
	private Label firstPlaceEnergyLBL;

	@FXML
	private Label secondPlaceEnergyLBL;
		
	@FXML
	private Label thirdPlaceEnergyLBL;
	
	@FXML
	private Label fourthPlaceEnergyLBL;
	
	@FXML
	private Label firstPlaceSmithoreTagLBL;

	@FXML
	private Label secondPlaceSmithoreTagLBL;
	
	@FXML
	private Label thirdPlaceSmithoreTagLBL;

	@FXML
	private Label fourthPlaceSmithoreTagLBL;
	
	@FXML
	private Label firstPlaceSmithoreLBL;

	@FXML
	private Label secondPlaceSmithoreLBL;
		
	@FXML
	private Label thirdPlaceSmithoreLBL;
	
	@FXML
	private Label fourthPlaceSmithoreLBL;
	
	@FXML
	private Label firstPlaceCrystiteTagLBL;

	@FXML
	private Label secondPlaceCrystiteTagLBL;
	
	@FXML
	private Label thirdPlaceCrystiteTagLBL;

	@FXML
	private Label fourthPlaceCrystiteTagLBL;
	
	@FXML
	private Label firstPlaceCrystiteLBL;

	@FXML
	private Label secondPlaceCrystiteLBL;
		
	@FXML
	private Label thirdPlaceCrystiteLBL;
	
	@FXML
	private Label fourthPlaceCrystiteLBL;
	
	@FXML
	private Button continueBTN;
	
	@FXML
	public MenuBar menuBar;
	
	@FXML
	public MenuItem saveMenuItem;
	
	//-------------------------------------------------------------------------------//
	public void continueBTNClick() {
		System.out.println("Continue Button Pressed in Round Results Screen");
		
		if (endOfGameFlag) {
			MessageBox.show(Main.game.getScene().getWindow(),
	    			"The Game is Over ... Implement Game Over Screen",
	    			"Game Over",
	  		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		
		} else {
			myController.setScreen(Main.game.getMap().getMapID());
		}
	}
	
	public void saveState() {
		Main.game.saveState();
	}
}
