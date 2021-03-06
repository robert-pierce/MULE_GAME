package application;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import application.GameRunner.Difficulty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by KillDogg on 10/8/2015.
 */
public class Store implements Serializable, Saveable {
	private static final long serialVersionUID = -4938173949110663527L;
	private GameRunner.Difficulty difficulty;
    private Deque<Mule> muleDeque;
    private int initialMuleStock;
    //private int foodStock;
    //private int energyStock;
    //private int smithoreStock;
    //private int crystiteStock;
    //private int mulePrice;
    //private int foodPrice;
    //private int energyPrice;
    //private int smithorePrice;
    //private int crystitePrice;
    private int foodEquipPrice;
    private int energyEquipPrice;
    private int smithoreEquipPrice;
    private int crystiteEquipPrice;

    private transient SimpleIntegerProperty muleStockProperty;
    private transient SimpleIntegerProperty foodStockProperty;
    private transient SimpleIntegerProperty energyStockProperty;
    private transient SimpleIntegerProperty smithoreStockProperty;
    private transient SimpleIntegerProperty crystiteStockProperty;
    
    private int muleStockBacking;
    private int foodStockBacking;
    private int energyStockBacking;
    private int smithoreStockBacking;
    private int crystiteStockBacking;
    
    private transient SimpleIntegerProperty mulePriceProperty;
    private transient SimpleIntegerProperty foodPriceProperty;
    private transient SimpleIntegerProperty energyPriceProperty;
    private transient SimpleIntegerProperty smithorePriceProperty;
    private transient SimpleIntegerProperty crystitePriceProperty;
   
    private int mulePricePropertyBacking;
    private int foodPricePropertyBacking;
    private int energyPricePropertyBacking;
    private int smithorePricePropertyBacking;
    private int crystitePricePropertyBacking;
    
    //------------------------Constructor----------------------------------------//
    public Store(Difficulty difficulty) {
        this.difficulty = difficulty;
        System.out.println("The store thinks difficulty is:" + difficulty);
        muleDeque = new ArrayDeque<Mule>();
        fillStore();
        mulePriceProperty = new SimpleIntegerProperty(100);
        foodPriceProperty = new SimpleIntegerProperty(30);
        energyPriceProperty = new SimpleIntegerProperty(25);
        smithorePriceProperty = new SimpleIntegerProperty(50);
        crystitePriceProperty = new SimpleIntegerProperty(100);
        this.foodEquipPrice = 25;
        this.energyEquipPrice = 50;
        this.smithoreEquipPrice = 75;
        this.crystiteEquipPrice = 100;
    }
    //--------------------End Constructor----------------------------------------//
    
    private void fillStore() {
        if(difficulty.equals(Difficulty.BEGINNER)) {
        	initialMuleStock = 25;
        	muleStockProperty = new SimpleIntegerProperty(25);
        	foodStockProperty = new SimpleIntegerProperty(16);
        	energyStockProperty = new SimpleIntegerProperty(16);
        	smithoreStockProperty = new SimpleIntegerProperty(0);
        	crystiteStockProperty = new SimpleIntegerProperty(0);
        } else {
        	initialMuleStock = 14;
        	muleStockProperty = new SimpleIntegerProperty(14);
            foodStockProperty = new SimpleIntegerProperty(8);
            energyStockProperty = new SimpleIntegerProperty(8);
            smithoreStockProperty = new SimpleIntegerProperty(8);
            crystiteStockProperty = new SimpleIntegerProperty(0);
        }
        createMules();
    }

    private void createMules() {
        for (int i = 0; i < initialMuleStock; i++) {
            muleDeque.add(new Mule());
        }
    }

    public Mule sellMule() {
    	if (getMuleStock() > 0) {
    		muleStockProperty.setValue(muleStockProperty.subtract(1).getValue());
	        return muleDeque.poll();
    	} else {
    		return null;
    	}
    }

    public int getMuleStock() {
        System.out.println("The muleStock is: " + muleDeque.size());
        return muleDeque.size();
    }
    
    public SimpleIntegerProperty getMuleStockProperty() {
    	return muleStockProperty;
    }
    
    public int getFoodStock() {
        return foodStockProperty.get();
    }
    
    public SimpleIntegerProperty getFoodStockProperty() {
    	return foodStockProperty;
    }
    
    public int getEnergyStock() {
        return energyStockProperty.get();
    }
    
    public SimpleIntegerProperty getEnergyStockProperty() {
    	return energyStockProperty;
    }
    
    public int getSmithoreStock() {
        return smithoreStockProperty.get();
    }
    
    public SimpleIntegerProperty getSmithoreStockProperty() {
    	return smithoreStockProperty;
    }
    
    public int getCrystiteStock() {
        return crystiteStockProperty.get();
    }
    
    public SimpleIntegerProperty getCrystiteStockProperty() {
    	return crystiteStockProperty;
    }
    

    public int getMulePrice() {
        return mulePriceProperty.get();
    }
    
    public SimpleIntegerProperty getMulePriceProperty() {
        return mulePriceProperty;
    }
    
    public int getFoodEquipPrice() {
    	return foodEquipPrice;
    }
    
    public int getEnergyEquipPrice() {
    	return energyEquipPrice;
    }
    
    public int getSmithoreEquipPrice() {
    	return smithoreEquipPrice;
    }
    
    public int getCrystiteEquipPrice() {
    	return crystiteEquipPrice;
    }
    
    public int getFoodPrice() {
    	return foodPriceProperty.get();
    }
    
    public SimpleIntegerProperty getFoodPriceProperty() {
        return foodPriceProperty;
    }
    
    public void incrementFood() {
    	foodStockProperty.setValue(foodStockProperty.add(1).getValue());
    }
    
    public void decrementFood() {
    	foodStockProperty.setValue(foodStockProperty.subtract(1).getValue());
    }
    
    public int getEnergyPrice() {
    	return energyPriceProperty.get();
    }
    
    public SimpleIntegerProperty getEnergyPriceProperty() {
        return energyPriceProperty;
    }
    
    public void incrementEnergy() {
    	energyStockProperty.setValue(energyStockProperty.add(1).getValue());
    }
    
    public void decrementEnergy() {
    	energyStockProperty.setValue(energyStockProperty.subtract(1).getValue());
    }
    
    public int getSmithorePrice() {
    	return smithorePriceProperty.get();
    }
    
    public SimpleIntegerProperty getSmithorePriceProperty() {
        return smithorePriceProperty;
    }
    
    public void incrementSmithore() {
    	smithoreStockProperty.setValue(smithoreStockProperty.add(1).getValue());
    }

    public void decrementSmithore() {
    	smithoreStockProperty.setValue(smithoreStockProperty.subtract(1).getValue());
    }
    
    public int getCrystitePrice() {
    	return crystitePriceProperty.get();
    }
    
    public SimpleIntegerProperty getCrystitePriceProperty() {
        return crystitePriceProperty;
    }
    
    public void incrementCrystite() {
    	crystiteStockProperty.setValue(crystiteStockProperty.add(1).getValue());
    }

    public void decrementCrystite() {
    	crystiteStockProperty.setValue(crystiteStockProperty.subtract(1).getValue());
    }
    
    public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("The store stock is: \n");
		str.append("food stock: " + foodStockProperty.get() + " at $" + foodPriceProperty.get() + " per unit\n");
		str.append("energy stock: " + energyStockProperty.get() + " at $" + energyPriceProperty.get() + " per unit\n");
		str.append("smithore stock: " + smithoreStockProperty.get() + " at $" + smithorePriceProperty.get() + " per unit\n");
		str.append("crystite stock: " + crystiteStockProperty.get() + " at $" + crystitePriceProperty.get() + " per unit\n");
    	
    	return str.toString();
    	
    }

	@Override
	public void prepSave() {
		muleStockBacking = muleStockProperty.get();
	    foodStockBacking = foodStockProperty.get();
	    energyStockBacking = energyStockProperty.get();
	    smithoreStockBacking = smithoreStockProperty.get();
	    crystiteStockBacking = crystiteStockProperty.get();
	    
	    
	    mulePricePropertyBacking = mulePriceProperty.get();
	    foodPricePropertyBacking = foodPriceProperty.get();
	    energyPricePropertyBacking = energyPriceProperty.get();
	    smithorePricePropertyBacking = smithorePriceProperty.get();
	    crystitePricePropertyBacking = crystitePriceProperty.get();
	    
	}

	@Override
	public void restoreSave() {
		muleStockProperty = new SimpleIntegerProperty();
	    foodStockProperty = new SimpleIntegerProperty();
	    energyStockProperty = new SimpleIntegerProperty();
	    smithoreStockProperty = new SimpleIntegerProperty();
	    crystiteStockProperty = new SimpleIntegerProperty(); 
	    mulePriceProperty = new SimpleIntegerProperty();
	    foodPriceProperty = new SimpleIntegerProperty();
	    energyPriceProperty = new SimpleIntegerProperty();
	    smithorePriceProperty = new SimpleIntegerProperty();
	    crystitePriceProperty = new SimpleIntegerProperty();
		
		
		muleStockProperty.setValue(muleStockBacking);
	    foodStockProperty.setValue(foodStockBacking);
	    energyStockProperty.setValue(energyStockBacking);
	    smithoreStockProperty.setValue(smithorePricePropertyBacking);
	    crystiteStockProperty.setValue(crystitePricePropertyBacking);
	    
	    
	    mulePriceProperty.setValue(mulePricePropertyBacking);
	    foodPriceProperty.setValue(foodPricePropertyBacking);
	    energyPriceProperty.setValue(energyPricePropertyBacking);
	    smithorePriceProperty.setValue(smithorePricePropertyBacking);
	    crystitePriceProperty.setValue(crystitePricePropertyBacking);
	    
		
	}

}