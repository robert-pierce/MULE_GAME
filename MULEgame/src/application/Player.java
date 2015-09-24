package application;

import java.awt.Point;
import java.util.HashMap;

public class Player {
	//-------------enums-----------------------------
	public enum Race {HUMAN, FLAPPER, BONZOID, UGAITE, BUZZITE};
	public enum Color {RED, GREEN, PINK, PURPLE};
	
	
	//--------------Instance Variables---------------
	private Race race;
	private Color color;
	private String playerName;
	private int playerNum;
	private int money;
	private int score;
	private int smithore;
	private int food;
	private int energy;
	private int crystite;
	private HashMap<Point, Plot> plotMap;
	
	
	
	

	
	//-------------Constructor------------------------
	public Player(Race rce, Color clr, String plyrName, int plyrNum) {
		race = rce;
		color = clr;
		playerName = plyrName;
		playerNum = plyrNum;
		plotMap = new HashMap<Point,Plot>();
		if (race.equals(Race.FLAPPER)) {
			money = 1600;
		} else if (race.equals(Race.HUMAN)) {
			money = 600;
		} else {
			money = 1000;
		}
		food = 8;
		energy = 4;
		smithore = 0;
		crystite = 0;
		updateScore();
	}

	public application.Player.Race getRace() {
		return race;
	}

	public application.Player.Color getColor() {
		return color;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public HashMap<Point, Plot> getPlots() {
		return plotMap;
	}

	public void addPlot(Point plotCoord, Plot newPlot) {
		plotMap.put(plotCoord, newPlot);
	}

	public int getMoney() {
		return money;
	}

	public void addMoney(int moneyChange) {
		this.money = money + moneyChange;
	}

	public int getScore() {
		return score;
	}

	public int getSmithore() {
		return smithore;
	}

	public void addSmithore(int smithoreChange) {
		this.smithore = smithore + smithoreChange;
	}

	public int getFood() {
		return food;
	}

	public void addFood(int foodChange) {
		this.food = food + foodChange;
	}

	public int getEnergy() {
		return energy;
	}

	public void addEnergy(int energyChange) {
		this.energy = energy + energyChange;
	}

	public int getCrystite() {
		return crystite;
	}

	public void addCrystite(int crystiteChange) {
		this.crystite = crystite + crystiteChange;
	}

	public void updateScore() {
		this.score = money + energy + smithore + 500 * plotMap.size();
	}
	
	public String toString() {
		String str =  "Player " + playerNum + "\n Name: " + playerName + "\n" + "Race: " + race + "\n" + "Color: " + color;
		return str;
	}
	

}
