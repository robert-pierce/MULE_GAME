package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Map;
import application.GameRunner.ActivePlayer;
import application.GameRunner.GameState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import jfx.messagebox.MessageBox;


public class MapController implements Initializable, ControlledScreen {
	ScreensController myController;
	private GameState gameState;
	boolean firstTwoRoundsFlag = true;
	private ActivePlayer activePlayerState;
	private double xCor, yCor;
	private int numPlayers;
	Map map;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//todo 
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	
	
	@FXML	
	public void mouseClick(MouseEvent mouseEvent) {
		gameState = retrieveGameState();
		activePlayerState = retrieveActivePlayerState();
		numPlayers = retrieveNumPlayers();
		map = Main.game.getMap();
		firstTwoRoundsFlag = application.Main.game.getFirstTwoRoundsFlag();
		
		System.out.println("Game is in " + gameState + " mode");
		System.out.println("Active Player is: " + activePlayerState);
		
		xCor = mouseEvent.getX();
		yCor = mouseEvent.getY();	
		
		if (gameState.equals(GameState.LANDPURCHASE)) {
			
			if (activePlayerState.equals(ActivePlayer.PLAYER1)) {
				
				if (map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag) != null) {
					Main.game.setActivePlayer(ActivePlayer.PLAYER2);
				
				
					if (numPlayers > 1) {
						if (firstTwoRoundsFlag) {
							MessageBox.show(Main.game.getScene().getWindow(),
					         "Player 2 please select a plot to purchase, plots are FREE",
					         "Information dialog",
					         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
						} else {
							MessageBox.show(Main.game.getScene().getWindow(),
							 "Player 2 please select a plot to purchase, plots are $300",
							 "Information dialog",
							 MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
						}
						
					} else {
						Main.game.setGameState(GameState.MULEPURCHASE);
						Main.game.setActivePlayer(ActivePlayer.PLAYER1);
						MessageBox.show(Main.game.getScene().getWindow(),
						         "End of Land Purchase Phase",
						         "Information dialog",
						         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
						return;
					}
				}
			} else if (activePlayerState.equals(ActivePlayer.PLAYER2) && numPlayers >= 2) {
				if(map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag) != null) {
					Main.game.setActivePlayer(ActivePlayer.PLAYER3);
				
					if (numPlayers > 2) {
						if (firstTwoRoundsFlag) {
							MessageBox.show(Main.game.getScene().getWindow(),
					         "Player 3 please select a plot to purchase, plots are FREE",
					         "Information dialog",
					         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
						} else {
							MessageBox.show(Main.game.getScene().getWindow(),
							 "Player 3 please select a plot to purchase, plots are $300",
							 "Information dialog",
							 MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
						}
					} else {
							Main.game.setGameState(GameState.MULEPURCHASE);
							Main.game.setActivePlayer(ActivePlayer.PLAYER1);
							MessageBox.show(Main.game.getScene().getWindow(),
							         "End of Land Purchase Phase",
							         "Information dialog",
							         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
							return;
						}
				}
			} else if (activePlayerState.equals(ActivePlayer.PLAYER3) && numPlayers >= 3) {
				if(map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag) != null) {
					Main.game.setActivePlayer(ActivePlayer.PLAYER4);
				
					if (numPlayers > 3) {
						if (firstTwoRoundsFlag) {
							MessageBox.show(Main.game.getScene().getWindow(),
					         "Player 4 please select a plot to purchase, plots are FREE",
					         "Information dialog",
					         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
						} else {
							MessageBox.show(Main.game.getScene().getWindow(),
							 "Player 4 please select a plot to purchase, plots are $300",
							 "Information dialog",
							 MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
						}
					} else {
							Main.game.setGameState(GameState.MULEPURCHASE);
							Main.game.setActivePlayer(ActivePlayer.PLAYER1);
							MessageBox.show(Main.game.getScene().getWindow(),
							         "End of Land Purchase Phase",
							         "Information dialog",
							         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
							return;
						}
				}
			} else {
				if( map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag) != null) {
					Main.game.setGameState(GameState.MULEPURCHASE);
					Main.game.setActivePlayer(ActivePlayer.PLAYER1);
					
					MessageBox.show(Main.game.getScene().getWindow(),
					         "End of Land Purchase Phase",
					         "Information dialog",
					         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
				}
			}
		}
		
		//----------------- MULE PURCHASE STATE--------------------------------------
		if (gameState.equals(GameState.MULEPURCHASE)) {
			gameState = retrieveGameState();
			activePlayerState = retrieveActivePlayerState();
			numPlayers = retrieveNumPlayers();
			map = Main.game.getMap();
			firstTwoRoundsFlag = application.Main.game.getFirstTwoRoundsFlag();
			
			System.out.println("Game is in " + gameState + " mode");
			System.out.println("Active Player is: " + activePlayerState);
			
			xCor = mouseEvent.getX();
			yCor = mouseEvent.getY();	
		}
	}
	
	public GameState retrieveGameState() {
		return application.Main.game.getGameState();
	}
	
	
	public ActivePlayer retrieveActivePlayerState() {
		return application.Main.game.getActivePlayerState();
	}
	
	public int retrieveNumPlayers() {
		return  application.Main.game.getNumPlayers();
	}
	
	
	
	
	
	
}
