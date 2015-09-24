package application;

import application.Map.MapSelection;
import controller.ScreensController;
import controller.playerConfigController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Map {
	//----------Enum--------------------------------------
	public enum MapSelection {STANDARD, EASTWEST, MAP3};
	//----------------------------------------------------
	
	//--------------Instance Variables--------------------
	private ScreensController mainController;
	final private MapSelection mapSelection;
	final private int XOFFSET = 127;
	final private int YOFFSET = 141;
	
	//----------------------------------------------------
	
	
	//------------Constructor------------------------------
	public Map(MapSelection map, ScreensController mainCntrl) {
		mainController = mainCntrl;
		mapSelection = map;
		showMap(mapSelection);
		
	}
	
	
	
	public void showMap(MapSelection mapSlct) {
			if (mapSelection == MapSelection.STANDARD) {
				System.out.println("Loading " + mapSelection + " map");
				mainController.setScreen(application.Main.standardMapID);	
			} else if (mapSelection == MapSelection.EASTWEST) {
				System.out.println("Loading " + mapSelection + " map");
				mainController.setScreen(application.Main.eastWestMapID);
			} else if (mapSelection == MapSelection.MAP3) {
				System.out.println("Loading " + mapSelection + " map");
				mainController.setScreen(application.Main.standardMapID);
			}
		}
	
	
	public int setXCoord(double xCor) {
		int xMouseCoord;
		
		xMouseCoord = (int) (xCor / XOFFSET); 
		System.out.println("X: " + xMouseCoord);
		
		return xMouseCoord;
	}
	
	public int setYCoord(double yCor) {
		int yMouseCoord;
		
		yMouseCoord = (int) (yCor / YOFFSET); 
		System.out.println("Y: " + yMouseCoord);
		
		return yMouseCoord;
	}
	
									
	}
	
