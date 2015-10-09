package application;

import java.awt.Point;
import java.util.HashMap;

import application.GameRunner.Difficulty;
import application.GameRunner.MuleType;
import javafx.scene.paint.Color;
import jfx.messagebox.MessageBox;

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
		
		initPlayer(difficulty);
		updateScore();
	}

	private void initPlayer(Difficulty diff) {
		food = 4;
		energy = 2;
		smithore = 0;
		crystite = 0;
		
		if (diff.equals(Difficulty.BEGINNER)) {
			food = food + 4;
			energy = energy + 2;
			
		}
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
		Store store = Main.game.getStore();
		if (money < store.getMulePrice()) {
			
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You cannot afford a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;
			
		} else if (store.getMuleStock() == 0) {
			
			MessageBox.show(Main.game.getScene().getWindow(),
			         "The store is out of Mules!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;
			
		} else if (hasMule()){
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You already own a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;
		} else {
			playerMule = store.sellMule();
			money = money - store.getMulePrice();
			
			MessageBox.show(Main.game.getScene().getWindow(),
			         "Player " + playerNum + " you have bought a Mule",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return true;
			
		}
	}
	
	public boolean equipFood() {
		Store store = Main.game.getStore();
		if (hasMule()) {
			if (money < store.getFoodEquipPrice()) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for food production!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else {
				playerMule.setMuleType(MuleType.FOOD);
				money = money - store.getFoodEquipPrice();
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerNum + " you have equiped your Mule for food production",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You do not own a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;	
		}
	}
	
	public boolean equipEnergy() {
		Store store = Main.game.getStore();
		if (hasMule()) {
			if (money < store.getEnergyEquipPrice()) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for energy production!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			
			} else {
				playerMule.setMuleType(MuleType.ENERGY);
				money = money - store.getEnergyEquipPrice();
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerNum + " you have equiped your Mule for energy production",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You do not own a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;	
		}
	}
	
	public boolean equipSmithore() {
		Store store = Main.game.getStore();
		if (hasMule()) {
			if (money < store.getSmithoreEquipPrice()) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for smithore production!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			
			} else {
				playerMule.setMuleType(MuleType.SMITHORE);
				money = money - store.getSmithoreEquipPrice();
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerNum + " you have equiped your Mule for smithore production",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You do not own a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;	
		}
	}
	
	public boolean equipCrystite() {
		Store store = Main.game.getStore();
		if (hasMule()) {
			if (money < store.getCrystiteEquipPrice()) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for crystite mining!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			
			} else {
				playerMule.setMuleType(MuleType.CRYSTITE);
				money = money - store.getCrystiteEquipPrice();
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerNum + " you have equiped your Mule for cyrstite mining",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You do not own a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;	
		}
	}
	
	
	public boolean hasMule() {
		return playerMule != null;
	}
	
	public Mule getMule() {
		return playerMule;
	}
	
	public void removeMule() {
		playerMule = null;
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
		StringBuilder str = new StringBuilder();
		str.append("Player " + playerNum + " \n");
		str.append("Name: " + playerName + " \n");
		str.append("Race: " + race + " \n");
		str.append("Color: " + color.toString() + " \n");
		str.append("Score: " + score);
	
		return str.toString();
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
