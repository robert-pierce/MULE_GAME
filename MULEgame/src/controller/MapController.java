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
	private int numPlayers;
	Map map;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@Override
	public void onLoad() {
		System.out.println("Yay! on Load Method Works");
		System.out.println("Game state is " + Main.game.getGameState());
		gameState = Main.game.getGameState();
		if (gameState.equals(GameState.LANDPURCHASE)) {
			int msgBoxRslt;
			numPlayers = Main.game.getNumPlayers();
			firstTwoRoundsFlag = Main.game.getFirstTwoRoundsFlag();
			Main.game.calculatePlayerOrder();
			System.out.println("Player Order Calculated");
			//activePlayerState = Main.game.getActivePlayerState();
			System.out.println("Active player is "  + activePlayerState);
			msgBoxRslt = handleTurn();
			updatePlayerPass(msgBoxRslt);
		}
	}
	
	
	@FXML
	public Canvas plotMarkerCanvas;
	
	
	@FXML	
	public void mouseClick(MouseEvent mouseEvent) {
		Plot currentPlot;
		gameState = retrieveGameState();
		activePlayerState = retrieveActivePlayerState();
		numPlayers = retrieveNumPlayers();
		map = Main.game.getMap();
		//firstTwoRoundsFlag = application.Main.game.getFirstTwoRoundsFlag();
		int msgBoxRslt;
		
		System.out.println("Game is in " + gameState + " mode");
		System.out.println("Active Player is: " + activePlayerState);
		
		xCor = mouseEvent.getX();
		yCor = mouseEvent.getY();	
		
		
		//-------------------LAND PURCHASE STATE----------------------------------------
		if (gameState.equals(GameState.LANDPURCHASE)) {	
				if (!Main.game.isPlayerPassing()) {	
					currentPlot = map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag);	
					if (currentPlot != null) {
						markPlot(currentPlot);
						
						//Main.game.updateActivePlayerState();
	                    //Main.game.setActivePlayer(ActivePlayer.PLAYER2);  //***************
	                    msgBoxRslt = handleTurn();
	                    updatePlayerPass(msgBoxRslt);						
					}
				} else {
					//Main.game.updateActivePlayerState();
					// Main.game.setActivePlayer(ActivePlayer.PLAYER2);  //*******************
					 msgBoxRslt = handleTurn();
	                 updatePlayerPass(msgBoxRslt);	
				}
			
		}
		
	//----------------------------End of LANDPURCHASE STATE-----------------------------------	
		
		
		
		//----------------- MULE PURCHASE STATE-----------------------------------------------
		if (gameState.equals(GameState.MULEPURCHASE)) {
			gameState = retrieveGameState();
			activePlayerState = retrieveActivePlayerState();
			numPlayers = retrieveNumPlayers();
			map = Main.game.getMap();
			firstTwoRoundsFlag = Main.game.getFirstTwoRoundsFlag();
			
			System.out.println("Game is in " + gameState + " mode");
			System.out.println("Active Player is: " + activePlayerState);
			
			xCor = mouseEvent.getX();
			yCor = mouseEvent.getY();
			
			map.placeMule( xCor,  yCor, Main.game.getActivePlayer());
			
		}
		
		
		
		
	}
	
	
	
	//--------------------------End of MULE PURCHASE STATE------------------------------
	
	
	public GameState retrieveGameState() {
		return application.Main.game.getGameState();
	}
	
	
	public ActivePlayer retrieveActivePlayerState() {
		return application.Main.game.getActivePlayerState();
	}
	
	public int retrieveNumPlayers() {
		return  application.Main.game.getNumPlayers();
	}
	
	private void updatePlayerPass(int msgBoxRslt) {
		if (msgBoxRslt == MessageBox.NO) {
			Main.game.setPlayerPassing(true);
		} else {
			Main.game.setPlayerPassing(false);
		}
	}
	
	private int handleTurn() {
		System.out.println("Make announcement method has been called");
		int msgBoxRslt;
		int playerNum;
		int ENDPURCHASEPHASE = -1;
		StringBuilder announcement = new StringBuilder();
		Main.game.updateActivePlayerState();
		activePlayerState = Main.game.getActivePlayerState();
		
		
		System.out.println("makeAnnouncement ActivePlayer is: " + activePlayerState );
		System.out.println("makeAnouncement game state is: " + gameState );
		if (gameState.equals(GameState.LANDPURCHASE)) {
			System.out.println("makeAnnouncement method activePlayer state is: " + activePlayerState);
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
		
		
			//System.out.println("makeAnnoucement method numPlayers:" + numPlayers);
			//System.out.println("makeAnnoucement method nextPlayerNum:" + playerNum);
			
			System.out.println("in makeAnnouncement method activePlayerState is: " + activePlayerState);
			System.out.println("in makeAnnouncement maethod out of players method returns: " + Main.game.outOfPlayers());
			
			
			msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
										announcement.toString(),
										"Information dialog",
										MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
			return msgBoxRslt;
			
		} else {
			endPurchasePhase();
			return ENDPURCHASEPHASE;
		}
		
		}
		return 0;
	}
	
	private void endPurchasePhase() {
		System.out.println("End Purchase Method has been called");
		Main.game.setGameState(GameState.MULEPURCHASE);
		Main.game.calculatePlayerOrder();
		Main.game.updateActivePlayerState();   //**********************
		MessageBox.show(Main.game.getScene().getWindow(),
		         "End of Land Purchase Phase",
		         "Information dialog",
		         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	private void beginMulePurchasePhase() {
		
	}
	
	private void markPlot(Plot currentPlot) {
		GraphicsContext gc = plotMarkerCanvas.getGraphicsContext2D();
		
		 gc.setFill(Main.game.getActivePlayer().getColor());
         Point center = currentPlot.getCenter();
         gc.fillRect(center.getX(), center.getY(), 10, 10);
	}


	
	
	
}
