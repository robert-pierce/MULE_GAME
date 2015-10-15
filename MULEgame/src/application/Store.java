package application;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import application.GameRunner.Difficulty;

/**
 * Created by KillDogg on 10/8/2015.
 */
public class Store {
    private GameRunner.Difficulty difficulty;
    private Deque<Mule> muleDeque;
    private int initialMuleStock;
    private int foodStock;
    private int energyStock;
    private int smithoreStock;
    private int crystiteStock;
    private int mulePrice;
    private int foodPrice;
    private int energyPrice;
    private int smithorePrice;
    private int crystitePrice;
    private int foodEquipPrice;
    private int energyEquipPrice;
    private int smithoreEquipPrice;
    private int crystiteEquipPrice;

    public Store(Difficulty difficulty) {
        this.difficulty = difficulty;
        System.out.println("The store thinks difficulty is:" + difficulty);
        muleDeque = new ArrayDeque<Mule>();
        fillStore();
        this.mulePrice = 100;
        this.foodPrice = 30;
        this.energyPrice = 25;
        this.smithorePrice = 50;
        this.crystitePrice = 100;
        this.foodEquipPrice = 25;
        this.energyEquipPrice = 50;
        this.smithoreEquipPrice = 75;
        this.crystiteEquipPrice = 100;
    }

    private void fillStore() {
        if(difficulty.equals(Difficulty.BEGINNER)) {
        	initialMuleStock = 25;
        	foodStock = 16;
        	energyStock = 16;
        	smithoreStock = 0;
        	crystiteStock = 0;
        } else {
        	initialMuleStock = 14;
            foodStock = 8;
            energyStock = 8;
            smithoreStock = 8;
            crystiteStock = 0;
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
	        return muleDeque.poll();
    	} else {
    		return null;
    	}
    }

    public int getMuleStock() {
        System.out.println("The muleStock is: " + muleDeque.size());
        return muleDeque.size();
    }

    public int getMulePrice() {
        return mulePrice;
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
    	return foodPrice;
    }
    
    public void incrementFood() {
    	foodStock++;
    }
    
    public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("The store stock is: \n");
		str.append("food stock: " + foodStock + " at $" + foodPrice + " per unit\n");
		str.append("energy stock: " + energyStock + " at $" + energyPrice + " per unit\n");
		str.append("smithore stock: " + smithoreStock + " at $" + smithorePrice + " per unit\n");
		str.append("crystite stock: " + crystiteStock + " at $" + crystitePrice + " per unit\n");
    	
    	return str.toString();
    	
    }

}