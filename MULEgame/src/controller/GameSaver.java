package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;

import application.Main;
import application.Saveable;

public class GameSaver implements Saveable {

	public GameSaver() {
		
	}
	
	@Override
	public void saveState() {
		Gson gson = new Gson();
		String json = gson.toJson(Main.game.getPlayerList().get(0));
		
		 try {
			   //write converted json data to a file named "CountryGSON.json"
			   Date date = new Date();
			   File file = new File("./SavedStates/", "test.txt");
			   
//			   File curDir = new File(".");
//			   File[] filesList = curDir.listFiles();
//			   for (int i = 0; i < filesList.length; i++) {
//				   System.out.println(filesList[i]);
//			   }
		            
			   
			  // file.mkdir();
			   BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			   writer.write(json);
			   writer.close();
			  
		 } catch (IOException e) {
			   e.printStackTrace();
		 }
			  
		System.out.println(json);

	}

}
