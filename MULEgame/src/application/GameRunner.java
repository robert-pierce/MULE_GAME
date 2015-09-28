package application;


import java.util.ArrayList;

import application.Map.MapSelection;
import controller.ScreensController;

import javafx.scene.Scene;



public class GameRunner {
	//---------Enum----------------------------------------
	public enum GameState{LANDPURCHASE, MULEPURCHASE, ACQUIREGOODS, ENDROUND};
	public enum Difficulty {BEGINNER, STANDARD, TOURNAMENT};
	public enum ActivePlayer{PLAYER1, PLAYER2, PLAYER3, PLAYER4}; 
	public enum PlotType {M1, M2, M3, RIVER, PLAIN, TOWN};
	//-----------------------------------------------------
	
	//----------------Instance Variables-------------------
	private GameState gameState;
	private ActivePlayer activePlayer;
	private Difficulty difficulty;
	private Map gameMap;
	private Scene scene;
	private ArrayList<Player> playerList;
	private ScreensController mainController;
	private int numRounds;
	private int round = 0;
	private boolean playerPass = false;
	private boolean firstTwoRounds = true;
	
	//-----------------------------------------------------
	
	//--------------Constructor----------------------------
	public GameRunner(ScreensController mainCntrl, Scene scne) {
		scene = scne;
		playerList = new ArrayList<Player>();
		mainController = mainCntrl;
		activePlayer = ActivePlayer.PLAYER1;
		numRounds = 6;
		
		
		
	}
	//-----------------------------------------------------
	
	
	
	
	//-----------Getters and Setters-----------------------
	
	public Scene getScene() {
		return scene;
	}
	
	public void addDifficulty(Difficulty diffclt) {
		difficulty = diffclt;
	}
	
	public void addPlayer(Player plyr) {
		playerList.add(plyr);
	}
	
	public void addMap(MapSelection mapTyp) {
		gameMap = new Map(mapTyp, mainController);
	}
	
	public ScreensController getMainController() {
		return mainController;
	}
	
	
	
	public void setLandPurchaseState() {
		 gameState = GameState.LANDPURCHASE;
		 System.out.println("LandPurchase State Set");
	 }
	
	public void setActivePlayer(ActivePlayer actPlyr) {
		activePlayer = actPlyr;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	

	public ActivePlayer getActivePlayerState() {
		return activePlayer;
	}
	
	public boolean getFirstTwoRoundsFlag() {
		return firstTwoRounds;
	}
	
	public int getRoundNumber() {
		return round;
	}
	
	public void incrementRound() {
		round++;
		System.out.println("Round incremented to: " + round);
	}
	
	public Map getMap() {
		return gameMap;
	}
	
	public int getNumPlayers() {
		return playerList.size();
	}
	
	public void setGameState(GameState gmeSte) {
		gameState = gmeSte;
	}
	
	public Player getActivePlayer() {
		if (activePlayer.equals(ActivePlayer.PLAYER1)) {
			return playerList.get(0);	
		} else if (activePlayer.equals(ActivePlayer.PLAYER2)) {
			return playerList.get(1);	
		} else if (activePlayer.equals(ActivePlayer.PLAYER3)) {
			return playerList.get(2);	
		} else {
			return playerList.get(3);	
		} 	
	}
	
	public boolean isPlayerPassing() {
		return playerPass;
	}
	
	public void setPlayerPassing(boolean isPassing) {
		 playerPass = isPassing;
	}
	
	
}
