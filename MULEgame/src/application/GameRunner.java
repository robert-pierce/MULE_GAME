package application;

import java.awt.Point;
import java.util.ArrayList;

import application.Map.MapSelection;
import controller.ScreensController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


public class GameRunner {
	//---------Enum----------------------------------------
	private enum GameState{LANDPURCHASE, MULEPURCHASE, ENDROUND};
	public enum Difficulty {BEGINNER, STANDARD, TOURNAMENT};
	private enum ActivePlayer{PLAYER1, PLAYER2, PLAYER3, PLAYER4}; 
	//-----------------------------------------------------
	
	//----------------Instance Variables-------------------
	private GameState gameState;
	private Difficulty difficulty;
	private boolean FirstTwoRounds = true;
	private Scene scene;
	private ActivePlayer activePlayer;
	private ArrayList<Player> playerList;
	private ArrayList<Turn> turnList = new ArrayList<Turn>();
	private ScreensController mainController;
	private int numRounds;
	private Map gameMap;
	private int round = 1;
	int xCor, yCor;
	final private int XOFFSET = 127;
	final private int YOFFSET = 141;
	
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
	
	public int setXCoord(double xCor) {
		int xMouseCoord;
		
		xMouseCoord = (int) (xCor / XOFFSET); 
		System.out.println("X: " + xMouseCoord);
		
		return xMouseCoord;
	}
	
	public int setYCoord(double yCor) {
		int yMouseCoord;
		
		yMouseCoord = (int) (yCor / YOFFSET); 
		System.out.println("Y: " + yMouseCoord);
		
		return yMouseCoord;
	}
	

	
	//----------------Main GameRunner Class----------------
		 public void LandPurchaseState() {
			 Point plotKey;
			 gameState=GameState.LANDPURCHASE;
			 System.out.println("Reached LandPurchase State");
			
			 while(true) {
				 // Player One Select Plot
				 if (activePlayer.equals(ActivePlayer.PLAYER1)) {
					 plotKey = new Point(xCor, yCor);
					// System.out.println("Player 1 chose plot:" + plotKey); 
				 }
					 
					
			 }
			
				
			
		 }
		//-----------------------------------------------------
	
	
}
