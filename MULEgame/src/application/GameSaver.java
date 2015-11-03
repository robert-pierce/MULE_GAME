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
	         
	         for (Player plyr : plyrList) {
	        	  plyr.prepSave();
	        	  out.writeObject(plyr);  
	         }
	         
	         out.writeObject(new EOFClass());
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
