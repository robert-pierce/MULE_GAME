package application;

import java.util.ArrayList;

public class Turn {
	//--------Instance Variables--------------------------------
	GameRunner gameRunner;
	ArrayList<Player> playerList;
	boolean isFirstOrSecond;
	Map gameMap;
	double xMouseCoord, yMouseCoord;
	
	//------------Constructor------------------------------------
	public Turn(GameRunner gmRnr, ArrayList<Player> plyrLst, Map gmeMap, boolean isFrstOrScnd) {
		gameRunner = gmRnr;
		playerList = plyrLst;
		gameMap = gmeMap;
		isFirstOrSecond = isFrstOrScnd;
	}
	
	 
	
	public void runTurn() {
		
		buyLandPhase();
		buyMulesPhase();
		showScoresPhase();
		
	}
	
	
	private void buyLandPhase() {
		//gameMap.showMap();
		System.out.println("Map loaded in Turn Obj");
	}
	
	private void buyMulesPhase() {
		
	}
	
	private void showScoresPhase() {
		
	}
	
	public void setXCoord(double xCor) {
		xMouseCoord = xCor;
		System.out.println("Turn received xCoord: " + xMouseCoord);
	}
	
	public void setYCoord(double yCor) {
		yMouseCoord = yCor;
		System.out.println("Turn received yCoord: " + yMouseCoord);
	}
	
	

}
