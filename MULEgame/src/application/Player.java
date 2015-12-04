package application;

import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

import application.GameRunner.Difficulty;
import application.GameRunner.MuleType;
import application.Map.MapSelection;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import jfx.messagebox.MessageBox;
import java.util.Random;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Comparable<Player>, Serializable, Saveable {

	private static final long serialVersionUID = 7585487069641759509L;

	//-------------enums-----------------------------
	public enum Race {HUMAN, FLAPPER, BONZOID, UGAITE, BUZZITE};
	
	
	
	//--------------Instance Variables---------------
	private Race race;
	private transient Color color;
	private double colorRedBacking;
	private double colorBlueBacking;
	private double colorGreenBacking;
	private String playerName;
	//private int playerNum;
	//private int money;
	//private int score;
	private int smithore;
	//private int food;
	private int energy;
	private int crystite;
	private int m;
	private int round;
	private Mule playerMule;
	private boolean isLowestPlayer;
	private HashMap<Point, Plot> plotMap;
	private transient SimpleIntegerProperty playerIdProperty;
	private transient SimpleIntegerProperty moneyProperty;
	private transient SimpleIntegerProperty foodProperty;
	private transient SimpleIntegerProperty energyProperty;
	private transient SimpleIntegerProperty smithoreProperty;
	private transient SimpleIntegerProperty crystiteProperty;
	private transient NumberBinding scoreProperty;
	private transient SimpleIntegerProperty numPlotsProperty;
	
	private int playerIdPropertyBacking;
	private int moneyPropertyBacking;
	private int foodPropertyBacking;
	private int energyPropertyBacking;
	private int smithorePropertyBacking;
	private int crystitePropertyBacking;
	private int numPlotsPropertyBacking;
	
	
	private ArrayList<RandomEvent> randomEvents;
	private RandomEvent alumniPackage;
    private RandomEvent wanderingStudent;
    private RandomEvent antiqueComputer;
    private RandomEvent deadMooseRat;
    private RandomEvent roofEaten;
    private RandomEvent ugaStudents;
    private RandomEvent spaceGypsies;
    private RandomEvent marsOutlaws;
    private RandomEvent bribe;
    private RandomEvent lightsOn;
    private RandomEvent birthday;
    
    
    private int foodProduction = 0;
    private int energyProduction = 0;
    private int oreProduction = 0;
    private int crystiteProduction = 0;
	
    private MapSelection mapSelection;
    
	//-------------Constructor------------------------
	public Player(Race rce, Color clr, String plyrName, int plyrNum, GameRunner.Difficulty difficulty) {
		race = rce;
		color = clr;
		playerName = plyrName;
		playerIdProperty = new SimpleIntegerProperty(plyrNum);
		
		//playerNum = plyrNum;
		playerMule = null;
		plotMap = new HashMap<Point,Plot>();
		
		createRandomEvents();
		
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

	 public void createRandomEvents() {
	        randomEvents = new ArrayList<>();
	        alumniPackage = new RandomEvent(false);
	        randomEvents.add(alumniPackage);
	        wanderingStudent = new RandomEvent(false);
	        randomEvents.add(wanderingStudent);
	        antiqueComputer = new RandomEvent(false);
	        randomEvents.add(antiqueComputer);
	        deadMooseRat = new RandomEvent(false);
	        randomEvents.add(deadMooseRat);
	        roofEaten = new RandomEvent(true);
	        randomEvents.add(roofEaten);
	        ugaStudents = new RandomEvent(true);
	        randomEvents.add(ugaStudents);
	        spaceGypsies = new RandomEvent(true);
	        randomEvents.add(spaceGypsies);
	        marsOutlaws = new RandomEvent(true);
	        randomEvents.add(marsOutlaws);
	        bribe = new RandomEvent(false);
	        randomEvents.add(bribe);
	        lightsOn = new RandomEvent(true);
	        randomEvents.add(lightsOn);
	        birthday = new RandomEvent(false);
	        
	    }

	    public String runRandomEvent() {

	        round = Main.game.getRoundNumber();
	        if (round <= 3) {
	            m = 25;
	        } else if (round > 3 && round <= 7) {
	            m = 50;
	        } else if (round > 7 && round <= 11) {
	            m = 75;
	        } else {
	            m = 100;
	        }

	        String message;

	        Random rand = new Random();
	        int eventIdx = rand.nextInt(7);
	        RandomEvent event = randomEvents.get(eventIdx);

	        if (isLowestPlayer) {
	            while (event.isBad()) {
	                eventIdx = rand.nextInt(7);
	                event = randomEvents.get(eventIdx);
	            }
	        }

	        if (event == alumniPackage) {
	            foodProperty.setValue(foodProperty.add(3).getValue());
	            energyProperty.setValue(energyProperty.add(2).getValue());
	            message = "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.";
	        } else if (event == wanderingStudent) {
	            smithoreProperty.setValue(smithoreProperty.add(2).getValue());
	            message = "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE";
	        } else if (event == antiqueComputer) {
	            moneyProperty.setValue(moneyProperty.add(8*m).getValue());
	            message = "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + 8*m + ".";
	        } else if (event == deadMooseRat) {
	            moneyProperty.setValue(moneyProperty.add(2*m).getValue());
	            message = "YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $" + 2*m + ".";
	        } else if (event == roofEaten) {
	            moneyProperty.setValue(moneyProperty.subtract(4*m).getValue());
	            if (moneyProperty.getValue() < 0) {
	                moneyProperty.setValue(0);
	            }
	            message = "FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $" + 4*m + ".";
	        } else if (event == ugaStudents) {
	            foodProperty.setValue(foodProperty.getValue() / 2);
	            message = "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.";
	        } else if (event == spaceGypsies) {
	            moneyProperty.setValue(moneyProperty.subtract(6*m).getValue());
	            if (moneyProperty.getValue() < 0) {
	                moneyProperty.setValue(0);
	            }
	            message = "YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU " + 6*m + " TO CLEAN IT UP.";
	        
	        
	       //EXTRA CREDIT RANDOM EVENTS----------------------------------------------------------------------------------
	        } else if (event == marsOutlaws){
                moneyProperty.setValue(moneyProperty.subtract(2*m).getValue());
                smithoreProperty.setValue(smithoreProperty.subtract(2).getValue());
                if (smithoreProperty.getValue() < 0) {
                    smithoreProperty.setValue(0);
                }
                message = "YOU GOT HELD UP BY MARTIAN OUTLAWS ON THE WAY TO THE STORE. THEY TOOK $" + 2*m + " AND 2 BARS OF SMITHORE";
            } else if (event == bribe) {
                moneyProperty.setValue(moneyProperty.add(8 * m).getValue());
                message = "YOU CATCH YOUR NEIGHBOR WITH THE SHERIFF'S WIFE. HE GIVES YOU A FRIENDLY GIFT OF $" + 8 * m + ".";
            } else if (event == lightsOn) {
                energyProperty.setValue(energyProperty.subtract(3).getValue());
                message = "YOU GO ON A WEEK LONG VACATION BUT LEAVE ALL YOUR LIGHTS ON. YOU LOSE 3 ENERGY UNITS";
            } else if (event == birthday) {
                moneyProperty.setValue(moneyProperty.getValue() + m);
                foodProperty.setValue(foodProperty.getValue() + 1);
                energyProperty.setValue(energyProperty.getValue() + 1);
                smithoreProperty.setValue(smithoreProperty.getValue() + 1);
                message = "IT'S YOUR BIRTHDAY! YOUR FRIENDS GIVE YOU $" + m + ", 1 FOOD UNIT, 1 ENERGY UNIT, AND 1 BAR OF SMITHORE";

            } else {
                message = "";
            }
	        
	        
	        
	        return message;
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
	
	public int getFoodProduction() {
		return foodProduction;
	}
	
	public int getEnergyProduction() {
		return energyProduction;
	}
	
	
	public int getOreProduction() {
		return oreProduction;
	}
	
	public int getCrystiteProduction() {
		return crystiteProduction;
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
			
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You cannot afford a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;
			
		} else if (store.getMuleStock() == 0) {
			
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
			MessageBox.show(Main.game.getScene().getWindow(),
			         "The store is out of Mules!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;
			
		} else if (hasMule()){
			
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You already own a Mule!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return false;
		} else {
			new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
			playerMule = store.sellMule();
			moneyProperty.setValue(moneyProperty.subtract(store.getMulePrice()).getValue());
			System.out.println("score " + scoreProperty.getValue());
			
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "Player " + playerIdProperty.get() + " you have bought a Mule",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			
			return true;
			
		}
	}
	
	public boolean equipFood() {
		Store store = Main.game.getStore();
		if (hasMule()) {
			if (moneyProperty.get() < store.getFoodEquipPrice()) {

				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for food production!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else {
				new AudioClip(new File(Main.game.getClankURL()).toURI().toString()).play();
				playerMule.setMuleType(MuleType.FOOD);
				moneyProperty.setValue(moneyProperty.subtract(store.getFoodEquipPrice()).getValue());
				
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "Player " + playerIdProperty.get() + " you have equiped your Mule for food production",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
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
				
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for energy production!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			
			} else {
				new AudioClip(new File(Main.game.getClankURL()).toURI().toString()).play();
				playerMule.setMuleType(MuleType.ENERGY);
				moneyProperty.setValue(moneyProperty.subtract(store.getEnergyEquipPrice()).getValue()) ;
				
				
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "Player " + playerIdProperty.get() + " you have equiped your Mule for energy production",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
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
				
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for smithore production!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			
			} else {
				new AudioClip(new File(Main.game.getClankURL()).toURI().toString()).play();
				playerMule.setMuleType(MuleType.SMITHORE);
				moneyProperty.setValue(moneyProperty.subtract(store.getSmithoreEquipPrice()).getValue());
				
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "Player " + playerIdProperty.get() + " you have equiped your Mule for smithore production",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
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
				
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "You cannot afford to equip your Mule for crystite mining!",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
				
			} else if(!playerMule.getMuleType().equals(MuleType.EMPTY)) {
				
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule is already equiped",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return false;
			
			} else {
				new AudioClip(new File(Main.game.getClankURL()).toURI().toString()).play();
				playerMule.setMuleType(MuleType.CRYSTITE);
				moneyProperty.setValue(moneyProperty.subtract(store.getCrystiteEquipPrice()).getValue());
				
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "Player " + playerIdProperty.get() + " you have equiped your Mule for cyrstite mining",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return true;	
			}
		} else {
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
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
		System.out.println("Compute Time Called");
		System.out.println("Food is:" + foodProperty.get());
		
		System.out.println("FoodProperty.equals(0) " + foodProperty.equals(0));
		if (foodProperty.get() == 0) {
			System.out.println("Food is zero in Compute Time");
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
	
	
	public void sellFood() {
		Store store = Main.game.getStore();
		if(foodProperty.get() > 0) {
			new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
			moneyProperty.setValue(moneyProperty.add(store.getFoodPrice()).getValue());
			foodProperty.setValue(foodProperty.subtract(1).getValue());
			store.incrementFood();
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have any food!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	public void buyFood() {
		Store store = Main.game.getStore();
		if(moneyProperty.get() > store.getFoodPrice()) {
			if(store.getFoodStock() > 0) {
				new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
				moneyProperty.setValue(moneyProperty.subtract(store.getFoodPrice()).getValue());
				foodProperty.setValue(foodProperty.add(1).getValue());
				store.decrementFood();
			} else 
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "The store does not have any food!",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have enough money!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	public void sellEnergy() {
		Store store = Main.game.getStore();
		if(energyProperty.get() > 0) {
			new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
			moneyProperty.setValue(moneyProperty.add(store.getEnergyPrice()).getValue());
			energyProperty.setValue(energyProperty.subtract(1).getValue());
			store.incrementEnergy();
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have any energy!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	public void buyEnergy() {
		Store store = Main.game.getStore();
		if(moneyProperty.get() > store.getEnergyPrice()){
			if(store.getEnergyStock() > 0) {
				new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
				moneyProperty.setValue(moneyProperty.subtract(store.getEnergyPrice()).getValue());
				energyProperty.setValue(energyProperty.add(1).getValue());
				store.decrementEnergy();
			} else 
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "The store does not have any energy!",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have enough money!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			
	}
	
	public void sellSmithore() {
		Store store = Main.game.getStore();
		if(smithoreProperty.get() > 0) {
			new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
			moneyProperty.setValue(moneyProperty.add(store.getSmithorePrice()).getValue());
			smithoreProperty.setValue(smithoreProperty.subtract(1).getValue());
			store.incrementSmithore();
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have any smithore!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	public void buySmithore() {
		Store store = Main.game.getStore();
		if(moneyProperty.get() > store.getSmithorePrice()) {
			if(store.getSmithoreStock() > 0) {
				new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
				moneyProperty.setValue(moneyProperty.subtract(store.getSmithorePrice()).getValue());
				smithoreProperty.setValue(smithoreProperty.add(1).getValue());
				store.decrementSmithore();
			} else 
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "The store does not have any smithore!",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have enough money!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	public void sellCrystite() {
		Store store = Main.game.getStore();
		if(crystiteProperty.get() > 0) {
			new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
			moneyProperty.setValue(moneyProperty.add(store.getCrystitePrice()).getValue());
			crystiteProperty.setValue(crystiteProperty.subtract(1).getValue());
			store.incrementCrystite();
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have any crystite!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}
	
	public void buyCrystite() {
		Store store = Main.game.getStore();
		if(moneyProperty.get() >  store.getCrystitePrice()) {
			if(store.getCrystiteStock() > 0) {
				new AudioClip(new File(Main.game.getCashRegisterSoundURL()).toURI().toString()).play();
				moneyProperty.setValue(moneyProperty.subtract(store.getCrystitePrice()).getValue());
				crystiteProperty.setValue(crystiteProperty.add(1).getValue());
				store.decrementCrystite();
			} else 
				new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//				MessageBox.show(Main.game.getScene().getWindow(),
//				         "The store does not have any crystite!",
//				         "Information dialog",
//				         MessageBox.ICON_INFORMATION | MessageBox.OK);
		} else 
			new AudioClip(new File(Main.game.getErrorSoundURL()).toURI().toString()).play();
//			MessageBox.show(Main.game.getScene().getWindow(),
//			         "You do not have enough money!",
//			         "Information dialog",
//			         MessageBox.ICON_INFORMATION | MessageBox.OK);
	}

	public void calculateProduction() {
		  foodProduction = 0;
	      energyProduction = 0;
	      oreProduction = 0;
	      crystiteProduction = 0; 
	      
	     for (Plot plot : plotMap.values()) {
	           GameRunner.PlotType plotType;
	           GameRunner.MuleType muleType;
	          
	           if (energyProperty.getValue() != 0) {
	               plotType = plot.getType();
	               if (plot.getMule() != null) {

	                   muleType = plot.getMule().getMuleType();
	                   if (plotType == GameRunner.PlotType.M1) {
	                       if (muleType == MuleType.CRYSTITE) {
	                           crystiteProduction += 0;
	                       } else if (muleType == MuleType.ENERGY) {
	                           energyProduction += 1;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.FOOD) {
	                           foodProduction += 1;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.SMITHORE) {
	                           oreProduction += 2;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       }
	                   } else if (plotType == GameRunner.PlotType.M2) {
	                       if (muleType == MuleType.CRYSTITE) {
	                           crystiteProduction += 0;
	                       } else if (muleType == MuleType.ENERGY) {
	                           energyProduction += 1;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.FOOD) {
	                           foodProduction += 1;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.SMITHORE) {
	                           oreProduction += 3;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       }
	                   } else if (plotType == GameRunner.PlotType.M3) {
	                       if (muleType == MuleType.CRYSTITE) {
	                           crystiteProduction += 0;
	                       } else if (muleType == MuleType.ENERGY) {
	                           energyProduction += 1;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.FOOD) {
	                           foodProduction += 1;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.SMITHORE) {
	                           oreProduction += 4;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       }
	                   } else if (plotType == GameRunner.PlotType.PLAIN) {
	                       if (muleType == MuleType.CRYSTITE) {
	                           crystiteProduction += 0;
	                       } else if (muleType == MuleType.ENERGY) {
	                           energyProduction += 3;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.FOOD) {
	                           foodProduction += 2;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.SMITHORE) {
	                           oreProduction += 1;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       }
	                   } else if (plotType == GameRunner.PlotType.RIVER) {
	                       if (muleType == MuleType.CRYSTITE) {
	                           crystiteProduction += 0;
	                       } else if (muleType == MuleType.ENERGY) {
	                           energyProduction += 2;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.FOOD) {
	                           foodProduction += 4;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.SMITHORE) {
	                           oreProduction += 0;
	                       }
	                   } else if (plotType == GameRunner.PlotType.VOLCANO) {
	                       if (muleType == MuleType.CRYSTITE) {
	                           crystiteProduction += 0;
	                       } else if (muleType == MuleType.ENERGY) {
	                           energyProduction += 5;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.FOOD) {
	                           foodProduction += 0;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.SMITHORE) {
	                           oreProduction += 3;
	                       }
	                   } else if (plotType == GameRunner.PlotType.SWAMP) {
	                       if (muleType == MuleType.CRYSTITE) {
	                           crystiteProduction += 0;
	                       } else if (muleType == MuleType.ENERGY) {
	                           energyProduction += 2;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.FOOD) {
	                           foodProduction += 4;
	                           energyProduction--;
	                           //energyProperty.setValue(energyProperty.add(-1).getValue());
	                       } else if (muleType == MuleType.SMITHORE) {
	                           oreProduction += 0;
	                       }
	                   }
	               }
	           }
	     }
	     
	     foodSpoilage();
	     
	     if (foodProperty.add(foodProduction).getValue() < 0) {
	    	 foodProperty.setValue(0);
	     } else {
	    	 foodProperty.setValue(foodProperty.add(foodProduction).getValue());
	     }
         energyProperty.setValue(energyProperty.add(energyProduction).getValue());
         smithoreProperty.setValue(smithoreProperty.add(oreProduction).getValue());
         crystiteProperty.setValue(crystiteProperty.add(crystiteProduction).getValue());
	}
	
	private void foodSpoilage() {
		Random rand = new Random();
		 for (Plot plot : plotMap.values()) {
			 if (rand.nextDouble() >= 0.6) {
				 System.out.println("Some Food Spoiled!");
				 foodProduction--;
			 }
		 }
		 
	}

	public boolean isLowestPlayer() {
        return isLowestPlayer;
    }

    public void setLowestPlayer(boolean lowest) {
        isLowestPlayer = lowest;
    }
    
    public int getRound() {
    	return round;
    }
	
    public MapSelection getMapSelction() {
    	return mapSelection;
    }
    
    public void changeFoodProperty(int change) {
        foodProperty.setValue(foodProperty.getValue() + change);
    }

    public void changeMoneyProperty(int change) {
        moneyProperty.setValue(moneyProperty.getValue() + change);
        if (moneyProperty.getValue() < 0) {
            moneyProperty.setValue(0);
        }
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
	public void prepSave() {
		round = Main.game.getRoundNumber();
		round++;
		mapSelection = Main.game.getMap().getMapSelection();
		
		playerIdPropertyBacking = playerIdProperty.get();
		foodPropertyBacking = foodProperty.get();
		energyPropertyBacking = energyProperty.get();
		smithorePropertyBacking = smithoreProperty.get();
		crystitePropertyBacking = crystiteProperty.get();
		numPlotsPropertyBacking = numPlotsProperty.get();
		moneyPropertyBacking = moneyProperty.get();
		colorRedBacking = color.getRed();
		colorBlueBacking = color.getBlue();
		colorGreenBacking = color.getGreen();
		
		
	}

	@Override
	public void restoreSave() {
		playerIdProperty = new SimpleIntegerProperty();
		foodProperty = new SimpleIntegerProperty();
		energyProperty = new SimpleIntegerProperty();
		smithoreProperty = new SimpleIntegerProperty();
		crystiteProperty = new SimpleIntegerProperty();
		numPlotsProperty = new SimpleIntegerProperty();
		moneyProperty = new SimpleIntegerProperty();
		color = new Color(colorRedBacking, colorGreenBacking, colorBlueBacking, 1);
		
		playerIdProperty.setValue(playerIdPropertyBacking);
		foodProperty.setValue(foodPropertyBacking);
		energyProperty.setValue(energyPropertyBacking);
		smithoreProperty.setValue(smithorePropertyBacking);
		crystiteProperty.setValue(crystitePropertyBacking);
		numPlotsProperty.setValue(numPlotsPropertyBacking);
		moneyProperty.setValue(moneyPropertyBacking);
		scoreProperty = Bindings.add(moneyProperty.add(energyProperty), smithoreProperty.add(numPlotsProperty.multiply(500)));
	}

}
