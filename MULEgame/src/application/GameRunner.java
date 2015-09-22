package application;

import java.util.ArrayList;

import com.sun.prism.PhongMaterial.MapType;
import controller.gameConfigController.Difficulty;
import controller.mapConfigController.MapSelection;



public class GameRunner {
	//----------------Instance Variables-------------------
	private ArrayList<Player> playerList;
	private Difficulty difficulty;
	private MapSelection map;
	private double xMouseCoord, yMouseCoord;
	
	
	//-----------------------------------------------------
	
	//--------------Constructor----------------------------
	public GameRunner() {
		playerList = new ArrayList<Player>();
	}
	//-----------------------------------------------------
	
	
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
	
	
	public void setXCoord(double xCor) {
		System.out.println("X: " + xCor);
		xMouseCoord = xCor;
	}
	
	public void setYCoord(double yCor) {
		System.out.println("Y: " + yCor);
		yMouseCoord = yCor;
	}
	
}
