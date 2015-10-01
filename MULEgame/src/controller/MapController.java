package controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import application.Map;
import application.Plot;
import application.GameRunner.ActivePlayer;
import application.GameRunner.GameState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import jfx.messagebox.MessageBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.*;

public class MapController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	private GameState gameState;
	boolean firstTwoRoundsFlag = true;
	private ActivePlayer activePlayerState;
	private double xCor, yCor;
	//private int numPlayers;
	Map map;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@Override
	public void onLoad() {
		int msgBoxRslt;
		gameState = Main.game.getGameState();
		
		// what to do if in Land Purchase State
		if (gameState.equals(GameState.LANDPURCHASE)) {
			//numPlayers = Main.game.getNumPlayers();
			firstTwoRoundsFlag = Main.game.getFirstTwoRoundsFlag();
			Main.game.calculatePlayerOrder();
			msgBoxRslt = handleLandPurchaseTurn();
			updatePlayerPass(msgBoxRslt);
		}
	}
	
	//-----------------FXML Injections---------------------------------------
	
	@FXML
	public Canvas plotMarkerCanvas;
	
	//----------------End FXML Injections------------------------------------
	
	//-------------FXML On-Action Methods------------------------------------
	
	@FXML	
	public void mouseClick(MouseEvent mouseEvent) {
		int msgBoxRslt;
		Plot currentPlot;
		gameState = retrieveGameState();
		activePlayerState = retrieveActivePlayerState();
		//numPlayers = retrieveNumPlayers();
		map = Main.game.getMap();
		
		// get (x,y) coordinates
		xCor = mouseEvent.getX();
		yCor = mouseEvent.getY();	
		
		
		//-------------------LAND PURCHASE STATE------------------------------------------------------------
		if (gameState.equals(GameState.LANDPURCHASE)) {	
				if (!Main.game.isPlayerPassing()) {	   
					currentPlot = map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag); // purchase plot	
					if (currentPlot != null) {
						markPlot(currentPlot);   // mark the purchased plot
					    
						// handle the players turn
	                    msgBoxRslt = handleLandPurchaseTurn();
	                    
	                    // update the next player's response to buy or pass
	                    updatePlayerPass(msgBoxRslt);						
					}
				} else {
					 // skip this player's turn and move to the next player
					 msgBoxRslt = handleLandPurchaseTurn();
					 
					 // update the next player's response
	                 updatePlayerPass(msgBoxRslt);	
				}
		}
			
		
		//----------------- MULE PURCHASE STATE-------------------------------------------------------------
		if (gameState.equals(GameState.MULEPURCHASE)) {
			map.placeMule( xCor,  yCor, Main.game.getActivePlayer());
			
		}
		
		//--------------------------End of MULE PURCHASE STATE----------------------------------	
	}
	//----------------------------End On-Action Methods-------------------------------------------------
	
	
	//------------------------Helper Methods---------------------------------------------------
	
	private void updatePlayerPass(int msgBoxRslt) {
		if (msgBoxRslt == MessageBox.NO) {
			Main.game.setPlayerPassing(true);
		} else {
			Main.game.setPlayerPassing(false);
		}
	}
	
	private int handleLandPurchaseTurn() {
		int msgBoxRslt;
		int playerNum;
		int ENDPURCHASEPHASE = -1;
		StringBuilder announcement = new StringBuilder();
		Main.game.updateActivePlayerState();
		activePlayerState = Main.game.getActivePlayerState();
		
		if (activePlayerState != null) {
			switch (activePlayerState) {
				case PLAYER4:
					playerNum = 4;
					break;
				case PLAYER3:
					playerNum = 3;
					break;
				case PLAYER2:
					playerNum = 2;
					break;
				default:
					playerNum = 1;	
			}
	
			System.out.println("makeAnnouncement method firstTwoRoundsFlag state is: " + firstTwoRoundsFlag);
		if (firstTwoRoundsFlag) {
			announcement.append("Player " + playerNum + " would you like to buy a plot? Plots are FREE!");
													
		} else {
			announcement.append("Player " + playerNum +  " would you like to buy a plot? Plots are $300");
		}
	
		// present popUp message and get results
		msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
									announcement.toString(),
									"Information dialog",
									MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
		return msgBoxRslt;
		
		} else {  // if we are at the last player the end land purchase round
		endPurchasePhase();
		return ENDPURCHASEPHASE;
		}	
	}
	
	private void endPurchasePhase() {
		MessageBox.show(Main.game.getScene().getWindow(),
		         "End of Land Purchase Phase",
		         "Information dialog",
		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		
		beginMulePurchasePhase();
	}
	
	
	
	private void beginMulePurchasePhase() {
		Main.game.setGameState(GameState.MULEPURCHASE);
		Main.game.calculatePlayerOrder();
		//Main.game.updateActivePlayerState();
		
		MessageBox.show(Main.game.getScene().getWindow(),
		         "Begin of Mule Purchase Phase",
		         "Information dialog",
		         MessageBox.ICON_INFORMATION | MessageBox.OK);
	
		handleMulePurchasePhaseTurn();
	}
	
	private void handleMulePurchasePhaseTurn() {
		int playerNum;
		StringBuilder announcement = new StringBuilder();
		Main.game.updateActivePlayerState();
		activePlayerState = Main.game.getActivePlayerState();
		
		if (activePlayerState != null) {
			switch (activePlayerState) {
			case PLAYER4:
				playerNum = 4;
				break;
			case PLAYER3:
				playerNum = 3;
				break;
			case PLAYER2:
				playerNum = 2;
				break;
			default:
				playerNum = 1;	
			}
			
			announcement.append("Player " + playerNum +  " it is your turn to acquire goods!");
			MessageBox.show(Main.game.getScene().getWindow(),
						announcement.toString(),
						"Information dialog",
						MessageBox.ICON_INFORMATION | MessageBox.OK);
		} else {  // if we are at the last player the end land purchase round
			endRound();
			
		}	
	}
	
	// implement end of round method
	public void endRound() {
		System.out.println("End Round method called in MapController");
		Main.game.setGameState(GameState.RANDOMEVENT);
		System.out.println("Game State set to: " + Main.game.getGameState());
	}
	
	// implement end of turn method
	public void endTurn() {
		System.out.println("End of Turn method called in MapController");
		handleMulePurchasePhaseTurn();
	}
	
	
	// marks a plot with the appropriate color
	private void markPlot(Plot currentPlot) {
		GraphicsContext gc = plotMarkerCanvas.getGraphicsContext2D();
		
		 gc.setFill(Main.game.getActivePlayer().getColor());
         Point center = currentPlot.getCenter();
         gc.fillRect(center.getX(), center.getY(), 10, 10);
	}


	
	
	
	
	
	private GameState retrieveGameState() {
		return Main.game.getGameState();
	}


	private ActivePlayer retrieveActivePlayerState() {
		return Main.game.getActivePlayerState();
	}


	private int retrieveNumPlayers() {
		return  Main.game.getNumPlayers();
	}


	
	
	
}
