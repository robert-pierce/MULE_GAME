package application;

import java.awt.Point;
import java.util.HashMap;
import javafx.scene.paint.Color;

public class Player implements Comparable<Player>{
	//-------------enums-----------------------------
	public enum Race {HUMAN, FLAPPER, BONZOID, UGAITE, BUZZITE};
	
	
	
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
	private Mule playerMule;
	private HashMap<Point, Plot> plotMap;
	
	
	
	

	
	//-------------Constructor------------------------
	public Player(Race rce, Color clr, String plyrName, int plyrNum, GameRunner.Difficulty difficulty) {
		race = rce;
		color = clr;
		playerName = plyrName;
		playerNum = plyrNum;
		playerMule = null;
		plotMap = new HashMap<Point,Plot>();
		if (race.equals(Race.FLAPPER)) {
			money = 16000;
		} else if (race.equals(Race.HUMAN)) {
			money = 600;
		} else  {
			money = 1000;
		}
		switch (difficulty) {
					case BEGINNER:
						food = 8;
						energy = 4;
						smithore = 0;
						crystite = 0;
					default:
						food = 4;
						energy = 2;
						smithore = 0;
						crystite = 0;
				}
		 		updateScore();
		 	}

	public Race getRace() {
		return race;
	}

	public Color getColor() {
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
	
	public void payForPlot() {
		money = money - 300;
	}
	
	public boolean buyMule() {
		int price = 100;  // GET PRICE FROM STORE
		
		if (money >= 100) {
			playerMule = new Mule();
			money = money - 100;
			return true;
		} else {
			return false;
		}
			
	}
	
	public boolean hasMule() {
		return playerMule == null;
	}
	
	public Mule getMule() {
		return playerMule;
	}
	
	public boolean isPlotOwner(Point coord) {
		if (plotMap.containsKey(coord)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int computeTime() {
		int roundNum;
		
		if (food == 0) {
			return 5;
		} else{ 
			roundNum = Main.game.getRoundNumber();
			if (roundNum >= 9) {
				if (food < 5) {
					return 30;
				} else { 
					return 50;
				}	
			} else if (roundNum >= 5) {
				if (food < 4) {
					return 30;
				} else {
					return 50;
				}
			} else if (food < 3){
				return 30;
			} else {
				return 50;
			}
		}
	}
	
	
	public String toString() {
		String str =  "Player " + playerNum + "\n Name: " + playerName + "\n" + "Race: " + race + "\n" + "Color: " + color + "\n" + "Score: " + score ;
		return str;
	}

	@Override
	public int compareTo(Player plyrCmp) {
		if (this.score < plyrCmp.score) {
			return -1;
		} else if (this.score == plyrCmp.score) {
			return 0;
		} else {
			return 1;
		}
	}
	
	
	

}
