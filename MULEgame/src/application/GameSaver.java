package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class GameSaver  {
	
	public GameSaver() {}
	
	public void saveState() {
		ArrayList<Player> plyrList = Main.game.getPlayerList();
		
		try {
	         FileOutputStream fileOut = new FileOutputStream("./SavedStates/test.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
	         //write the map
	         //out.writeObject(Main.game.getMap());
	         
	         // write the store
	         Main.game.getStore().prepSave();
	         out.writeObject(Main.game.getStore());
	         
	         // write all of the players (in order)
	         for (Player plyr : plyrList) {
	        	  plyr.prepSave();
	        	  out.writeObject(plyr);  
	         }
	         
	         // write and EOF object as last element
	         out.writeObject(new EOFClass());
	         out.close();
	         
	         // terminate stream and close file
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved");
	      
		} catch(IOException e) {
	          e.printStackTrace();
	      }
		}
	
	public static class EOFClass implements Serializable { 
		private static final long serialVersionUID = 4773733498081494931L;

		public EOFClass() {};
	}
}
