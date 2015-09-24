package application;

import java.util.ArrayList;

import application.Map.MapSelection;
import controller.ScreensController;


public class GameRunner {
	//---------Enum----------------------------------------
	private enum GameState{LANDPURCHASE} ;
	public enum Difficulty {BEGINNER, STANDARD, TOURNAMENT};
	//-----------------------------------------------------
	
	//----------------Instance Variables-------------------
	private ArrayList<Player> playerList;
	private ArrayList<Turn> turnList = new ArrayList<Turn>();
	private Difficulty difficulty;
	private MapSelection map;
	private double xMouseCoord, yMouseCoord;
	private ScreensController mainController;
	private int numRounds;
	private Map gameMap;
	
	
	//-----------------------------------------------------
	
	//--------------Constructor----------------------------
	public GameRunner(ScreensController mainCntrl) {
		playerList = new ArrayList<Player>();
		mainController = mainCntrl;
		numRounds = 6;
		
	}
	//-----------------------------------------------------
	
	//----------------Main GameRunner Class----------------
	 public void runGame() {
		 Turn thisTurn;
		 //load difficulty
		 
		 // load map
		 gameMap = loadMap(map);
		 
		 
		 System.out.println("Made it to turn loop");
		 for (int i = 0; i < numRounds; i++) {
			 System.out.println("Loop");
			 if (i == 0 || i == 1) {
				 thisTurn = new Turn(this, playerList, gameMap, true);
			     thisTurn.runTurn();
			} else {
					 thisTurn = new Turn(this, playerList, gameMap, false);
					 thisTurn.runTurn();
				 }
			}
		  
		 System.out.println("End of runGame method");
	 }
	//-----------------------------------------------------
	public Map loadMap(MapSelection map) {
		return new Map(map, mainController);
	}
	
	//-----------Getters and Setters-----------------------
	
	public void addDifficulty(Difficulty diffclt) {
		difficulty = diffclt;
	}
	
	public void addPlayer(Player plyr) {
		playerList.add(plyr);
	}
	
	public void addMap(MapSelection mapTyp) {
		map = mapTyp;
	}
	
	public ScreensController getMainController() {
		return mainController;
	}
	
	public void setXCoord(double xCor) {
		System.out.println("X: " + xCor);
		xMouseCoord = xCor;
	}
	
	public void setYCoord(double yCor) {
		System.out.println("Y: " + yCor);
		yMouseCoord = yCor;
	}
	
}
