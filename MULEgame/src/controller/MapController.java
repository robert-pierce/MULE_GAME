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
		map = application.Main.game.getMap();
		firstTwoRoundsFlag = application.Main.game.getFirstTwoRoundsFlag();
		
		System.out.println("Game is in " + gameState + " mode");
		System.out.println("Active Player is: " + activePlayerState);
		
		xCor = mouseEvent.getX();
		yCor = mouseEvent.getY();	
		
		if (gameState.equals(GameState.LANDPURCHASE)) {
			
			if (activePlayerState.equals(ActivePlayer.PLAYER1) && firstTwoRoundsFlag ) {
				
				MessageBox.show(application.Main.game.getScene().getWindow(),
				         "Player 1 please select a plot to purchase, plots are FREE",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
				
				map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer());
				
			}
			
			
			
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
