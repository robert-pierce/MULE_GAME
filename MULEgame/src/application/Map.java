package application;

import application.Map.MapSelection;
import controller.ScreensController;
import controller.playerConfigController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Map {
	//----------Enum--------------------------------------
	public enum MapSelection {STANDARD, MAP2, MAP3};
	//----------------------------------------------------
	
	//--------------Instance Variables--------------------
	private ScreensController mainController;
	private MapSelection gameMap;
	//----------------------------------------------------
	
	
	//------------Constructor------------------------------
	public Map(MapSelection map, ScreensController mainCntrl) {
		mainController = mainCntrl;
		gameMap = map;
		showMap(gameMap);
	}
	
	
	
	private void showMap(MapSelection mapSlct) {
			if (mapSlct == MapSelection.STANDARD) {
				System.out.println("Loading " + mapSlct + " map");
				mainController.setScreen(application.Main.standardMapID);	
			} else if (mapSlct == MapSelection.MAP2) {
				System.out.println("Loading " + mapSlct + " map");
				mainController.setScreen(application.Main.standardMapID);
			} else if (mapSlct == MapSelection.MAP3) {
				System.out.println("Loading " + mapSlct + " map");
				mainController.setScreen(application.Main.standardMapID);
			}
		}
									
	}
	
