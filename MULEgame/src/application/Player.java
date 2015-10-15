package application;

import java.awt.Point;
import java.util.HashMap;

import application.GameRunner.Difficulty;
import application.GameRunner.MuleType;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import jfx.messagebox.MessageBox;
import sun.security.krb5.internal.CredentialsUtil;

public class Player implements Comparable<Player>{
	//-------------enums-----------------------------
	public enum Race {HUMAN, FLAPPER, BONZOID, UGAITE, BUZZITE};
	
	
	
	//--------------Instance Variables---------------
	private Race race;
	private Color color;
	private String playerName;
	//private int playerNum;
	//private int money;
	//private int score;
	private int smithore;
	//private int food;
	private int energy;
	private int crystite;
	private Mule playerMule;
	private HashMap<Point, Plot> plotMap;
	private SimpleIntegerProperty playerIdProperty ;
	private SimpleIntegerProperty moneyProperty;
	private SimpleIntegerProperty foodProperty;
	private SimpleIntegerProperty energyProperty;
	private SimpleIntegerProperty smithoreProperty;
	private SimpleIntegerProperty crystiteProperty;
	private NumberBinding scoreProperty;
	private SimpleIntegerProperty numPlotsProperty;
	

	
	//-------------Constructor------------------------
	public Player(Race rce, Color clr, String plyrName, int plyrNum, GameRunner.Difficulty difficulty) {
		race = rce;
		color = clr;
		playerName = plyrName;
		playerIdProperty = new SimpleIntegerProperty(plyrNum);
		
		//playerNum = plyrNum;
		playerMule = null;
		plotMap = new HashMap<Point,Plot>();
		
		
		
		if (race.equals(Race.FLAPPER)) {
			moneyProperty = new SimpleIntegerProperty(1600);
		} else if (race.equals(Race.HUMAN)) {
			moneyProperty = new SimpleIntegerProperty(600); 
		} else  {
			moneyProperty = new SimpleIntegerProperty(1000);
		}
		
		initPlayer(difficulty);
		//updateScore();
	}

	private void initPlayer(Difficulty diff) {
		foodProperty = new SimpleIntegerProperty(4);
		energyProperty = new SimpleIntegerProperty(2);
		smithoreProperty = new SimpleIntegerProperty(0);
		crystiteProperty = new SimpleIntegerProperty(0);
		numPlotsProperty = new SimpleIntegerProperty(plotMap.size());
		scoreProperty = Bindings.add(moneyProperty.add(energyProperty), smithoreProperty.add(numPlotsProperty.multiply(500)));
		
		
		System.out.println("Money at plyr init " + moneyProperty.getValue());
		System.out.println("energy at plyr init " + energyProperty.getValue());
		System.out.println("smithore at plyr init " + smithoreProperty.getValue());
		System.out.println("crystitehore at plyr init " + crystiteProperty.getValue());
		System.out.println("score at plyr init " + scoreProperty.getValue());
		
		scoreProperty.addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> ov, Number oldVal,
		          Number newVal) {
		        System.out.println("score old value: "+oldVal);
		        System.out.println("score new value: "+newVal);
		      }
		    });
		
		if (diff.equals(Difficulty.BEGINNER)) {
			foodProperty.setValue(foodProperty.add(4).getValue());
			energyProperty.setValue(energyProperty.add(2).getValue());
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
		return playerIdProperty.get();
	}
	
	public SimpleIntegerProperty getPlayerIdProperty() {
		return playerIdProperty;
	}

	
	public HashMap<Point, Plot> getPlots() {
		return plotMap;
	}

	public void addPlot(Point plotCoord, Plot newPlot) {
		plotMap.put(plotCoord, newPlot);
		numPlotsProperty.setValue(plotMap.size());
	}

	public int getMoney() {
		return moneyProperty.get();
	}
	
	public SimpleIntegerProperty getMoneyProperty() {
		return moneyProperty;
	}

	public void addMoney(int moneyChange) {
		moneyProperty.setValue(moneyProperty.add(moneyChange).getValue());
	}

	public int getScore() {
		return (int) scoreProperty.getValue();
	}
	
	public NumberBinding getScoreProperty() {
		return scoreProperty;
	}

	public int getSmithore() {
		return smithoreProperty.get();
	}
	
	public SimpleIntegerProperty getSmithoreProperty() {
		return smithoreProperty;
	}

	public void addSmithore(int smithoreChange) {
		smithoreProperty.setValue(smithoreProperty.add(smithoreChange).getValue());
	}

	public int getFood() {
		return foodProperty.get();
	}
	
	public SimpleIntegerProperty getFoodProperty() {
		return foodProperty;
	}

	public void addFood(int foodChange) {
		foodProperty.setValue(foodProperty.add(foodChange).getValue());
				
	}

	public int getEnergy() {
		return energyProperty.get();
	}
	
	public SimpleIntegerProperty getEnergyProperty() {
		return energyProperty;
	}

	public void addEnergy(int energyChange) {
		energyProperty.setValue(energyProperty.add(energyChange).getValue());
	}

	public int getCrystite() {
		return crystiteProperty.get();
	}
	
	public SimpleIntegerProperty getCrystiteProperty() {
		return crystiteProperty;
	}

	public void addCrystite(int crystiteChange) {
		crystiteProperty.setValue(crystiteProperty.add(crystiteChange).getValue());
	}

//	public void updateScore() {
//		this.score = money + energy + smithore + 500 * plotMap.size();
//	}
	
	public void payForPlot() {
		moneyProperty.setValue(moneyProperty.subtract(300).getValue());
	}
	
	public boolean buyMule() {
		Store store = Main.game.getStore();
		System.out.println("score: " + scoreProperty.getValue());
		
		if (moneyProperty.get() < store.getMulePrice()) {
			
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
			moneyProperty.setValue(moneyProperty.subtract(store.getMulePrice()).getValue());
			System.out.println("score " + scoreProperty.getValue());
			
			MessageBox.show(Main.game.getScene().getWindow(),
			         "Player " + playerIdProperty.get() + " you have bought a Mule",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			
			return true;
			
		}
	}
	
	public boolean equipFood() {
		Store store = Main.game.getStore();
		if (hasMule()) {
			if (moneyProperty.get() < store.getFoodEquipPrice()) {
				
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
				moneyProperty.setValue(moneyProperty.subtract(store.getFoodEquipPrice()).getValue());
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerIdProperty.get() + " you have equiped your Mule for food production",
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
			if (moneyProperty.get() < store.getEnergyEquipPrice()) {
				
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
				moneyProperty.setValue(moneyProperty.subtract(store.getEnergyEquipPrice()).getValue()) ;
				
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerIdProperty.get() + " you have equiped your Mule for energy production",
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
			if (moneyProperty.get() < store.getSmithoreEquipPrice()) {
				
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
				moneyProperty.setValue(moneyProperty.subtract(store.getSmithoreEquipPrice()).getValue());
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerIdProperty.get() + " you have equiped your Mule for smithore production",
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
			if (moneyProperty.get() < store.getCrystiteEquipPrice()) {
				
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
				moneyProperty.setValue(moneyProperty.subtract(store.getCrystiteEquipPrice()).getValue());
				
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Player " + playerIdProperty.get() + " you have equiped your Mule for cyrstite mining",
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
		
		if (foodProperty.equals(0)) {
			return 5;
		} else{ 
			roundNum = Main.game.getRoundNumber();
			if (roundNum >= 9) {
				if (foodProperty.get() < 5) {
					return 30;
				} else { 
					return 50;
				}	
			} else if (roundNum >= 5) {
				if (foodProperty.get() < 4) {
					return 30;
				} else {
					return 50;
				}
			} else if (foodProperty.get() < 3){
				return 30;
			} else {
				return 50;
			}
		}
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Player " + playerIdProperty.get() + " \n");
		str.append("Name: " + playerName + " \n");
		str.append("Race: " + race + " \n");
		str.append("Color: " + color + " \n");
		str.append("Score: " + scoreProperty.getValue());
	
		return str.toString();
	}

	@Override
	public int compareTo(Player plyrCmp) {
		if (this.scoreProperty.getValue().intValue() < plyrCmp.scoreProperty.getValue().intValue()) {
			return -1;
		} else if (this.scoreProperty.getValue().intValue() == plyrCmp.scoreProperty.getValue().intValue()) {
			return 0;
		} else {
			return 1;
		}
	}
	
	
	public void sellFood() {
		Store store = Main.game.getStore();
		if(foodProperty.get() > 0) {
			moneyProperty.setValue(moneyProperty.add(store.getFoodPrice()).getValue());
			foodProperty.setValue(foodProperty.subtract(1).getValue());
			store.incrementFood();
		} else 
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You do not have any food!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	

}
