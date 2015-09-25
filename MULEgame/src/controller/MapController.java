package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Map;
import application.Player;
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
import java.net.URL;
import java.util.ResourceBundle;

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
		
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
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
		firstTwoRoundsFlag = application.Main.game.getFirstTwoRoundsFlag();
		int msgBoxRslt;
		
		System.out.println("Game is in " + gameState + " mode");
		System.out.println("Active Player is: " + activePlayerState);
		
		xCor = mouseEvent.getX();
		yCor = mouseEvent.getY();	
		
		
		//-------------------LAND PURCHASE STATE----------------------------------------
		if (gameState.equals(GameState.LANDPURCHASE)) {
			GraphicsContext gc = plotMarkerCanvas.getGraphicsContext2D();
			
			
			if (activePlayerState.equals(ActivePlayer.PLAYER1)) {
				
				if (!Main.game.isPlayerPassing()) {	
					currentPlot = map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag);	
					if (currentPlot != null) {
						gc.setFill(application.Main.game.getActivePlayer().getColor());
	                 	Point center = currentPlot.getCenter();
	                    gc.fillRect(center.getX(), center.getY(), 10, 10);
						
						                   
	                    Main.game.setActivePlayer(ActivePlayer.PLAYER2);
						if (numPlayers > 1) {
							if (firstTwoRoundsFlag) {
								msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
															"Player 2 would you like to buy a plot? Plots are FREE!",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							} else {
								msgBoxRslt= MessageBox.show(Main.game.getScene().getWindow(),
															"Player 2 would you like to buy a plot? Plots are $300",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							}
							updatePlayerPass(msgBoxRslt);
							
						} else {
							Main.game.setGameState(GameState.MULEPURCHASE);
							Main.game.setActivePlayer(ActivePlayer.PLAYER1);
							MessageBox.show(Main.game.getScene().getWindow(),
							         "End of Land Purchase Phase",
							         "Information dialog",
							         MessageBox.ICON_INFORMATION | MessageBox.OK);
							return;
						}
					}
				} else {
					 Main.game.setActivePlayer(ActivePlayer.PLAYER2);
						
						
					 if (numPlayers > 1) {
							if (firstTwoRoundsFlag) {
								msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
															"Player 2 would you like to buy a plot? Plots are FREE!",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							} else {
								msgBoxRslt= MessageBox.show(Main.game.getScene().getWindow(),
															"Player 2 would you like to buy a plot? Plots are $300",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							}
							
							
							updatePlayerPass(msgBoxRslt);
							
							
						} else {
							Main.game.setGameState(GameState.MULEPURCHASE);
							Main.game.setActivePlayer(ActivePlayer.PLAYER1);
							MessageBox.show(Main.game.getScene().getWindow(),
							         "End of Land Purchase Phase",
							         "Information dialog",
							         MessageBox.ICON_INFORMATION | MessageBox.OK);
							return;
						}
				}
			} else if (activePlayerState.equals(ActivePlayer.PLAYER2) && numPlayers >= 2) {
				if (!Main.game.isPlayerPassing()) {	
				
					currentPlot = map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag);
					if(currentPlot != null) {
	                    gc.setFill(application.Main.game.getActivePlayer().getColor());
	                    Point center = currentPlot.getCenter();
	                    gc.fillRect(center.getX(), center.getY(), 10, 10);
		
	
	                    
						Main.game.setActivePlayer(ActivePlayer.PLAYER3);
					
						if (numPlayers > 2) {
							if (firstTwoRoundsFlag) {
								msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
															"Player 3 would you like to buy a plot? Plots are FREE!",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							} else {
								msgBoxRslt= MessageBox.show(Main.game.getScene().getWindow(),
															"Player 3 would you like to buy a plot? Plots are $300",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							}
							updatePlayerPass(msgBoxRslt);
							
						} else {
								Main.game.setGameState(GameState.MULEPURCHASE);
								Main.game.setActivePlayer(ActivePlayer.PLAYER1);
								MessageBox.show(Main.game.getScene().getWindow(),
								         "End of Land Purchase Phase",
								         "Information dialog",
								         MessageBox.ICON_INFORMATION | MessageBox.OK);
								return;
							}
					}
				} else {
					Main.game.setActivePlayer(ActivePlayer.PLAYER3);
					
					if (numPlayers > 2) {
						if (firstTwoRoundsFlag) {
							msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
														"Player 3 would you like to buy a plot? Plots are FREE!",
														"Information dialog",
														MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
						} else {
							msgBoxRslt= MessageBox.show(Main.game.getScene().getWindow(),
														"Player 3 would you like to buy a plot? Plots are $300",
														"Information dialog",
														MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
						}
						updatePlayerPass(msgBoxRslt);
						
					} else {
							Main.game.setGameState(GameState.MULEPURCHASE);
							Main.game.setActivePlayer(ActivePlayer.PLAYER1);
							MessageBox.show(Main.game.getScene().getWindow(),
							         "End of Land Purchase Phase",
							         "Information dialog",
							         MessageBox.ICON_INFORMATION | MessageBox.OK);
							return;
						}
				}
			} else if (activePlayerState.equals(ActivePlayer.PLAYER3) && numPlayers >= 3) {
				if (!Main.game.isPlayerPassing()) {	
					 currentPlot = map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag);
	                 if(currentPlot != null) {
	                     gc.setFill(application.Main.game.getActivePlayer().getColor());
	                     Point center = currentPlot.getCenter();
	                     gc.fillRect(center.getX(), center.getY(), 10, 10);
						
						
						
						Main.game.setActivePlayer(ActivePlayer.PLAYER4);
					
						if (numPlayers > 3) {
							if (firstTwoRoundsFlag) {
								msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
															"Player 4 would you like to buy a plot? Plots are FREE!",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							} else {
								msgBoxRslt= MessageBox.show(Main.game.getScene().getWindow(),
															"Player 4 would you like to buy a plot? Plots are $300",
															"Information dialog",
															MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
							}
							updatePlayerPass(msgBoxRslt);
							
						} else {
								Main.game.setGameState(GameState.MULEPURCHASE);
								Main.game.setActivePlayer(ActivePlayer.PLAYER1);
								MessageBox.show(Main.game.getScene().getWindow(),
								         "End of Land Purchase Phase",
								         "Information dialog",
								         MessageBox.ICON_INFORMATION | MessageBox.OK);
								return;
							}
					}
				} else {
					Main.game.setActivePlayer(ActivePlayer.PLAYER4);
					
					if (numPlayers > 3) {
						if (firstTwoRoundsFlag) {
							msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
														"Player 4 would you like to buy a plot? Plots are FREE!",
														"Information dialog",
														MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
						} else {
							msgBoxRslt= MessageBox.show(Main.game.getScene().getWindow(),
														"Player 4 would you like to buy a plot? Plots are $300",
														"Information dialog",
														MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
						}
						updatePlayerPass(msgBoxRslt);
						
					} else {
							Main.game.setGameState(GameState.MULEPURCHASE);
							Main.game.setActivePlayer(ActivePlayer.PLAYER1);
							MessageBox.show(Main.game.getScene().getWindow(),
							         "End of Land Purchase Phase",
							         "Information dialog",
							         MessageBox.ICON_INFORMATION | MessageBox.OK);
							return;
						}
				}
			} else {
				if (!Main.game.isPlayerPassing()) {	
					currentPlot = map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag);
	                if(currentPlot != null) {
	                    gc.setFill(application.Main.game.getActivePlayer().getColor());
	                    Point center = currentPlot.getCenter();
	                    gc.fillRect(center.getX(), center.getY(), 10, 10);
	                    Main.game.setActivePlayer(ActivePlayer.PLAYER3);
						
						
						
						Main.game.setGameState(GameState.MULEPURCHASE);
						Main.game.setActivePlayer(ActivePlayer.PLAYER1);
						
						MessageBox.show(Main.game.getScene().getWindow(),
						         "End of Land Purchase Phase",
						         "Information dialog",
						         MessageBox.ICON_INFORMATION | MessageBox.OK);
					}
				}
			}
		}
		
	//----------------------------End of LANDPURCHASE STATE-----------------------------------	
		
		
		
		//----------------- MULE PURCHASE STATE-----------------------------------------------
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
	
	
	
	
}
