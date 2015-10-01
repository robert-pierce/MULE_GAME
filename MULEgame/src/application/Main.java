package application;
	
import javafx.scene.input.MouseEvent;

import controller.ScreensController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.event.Event;


public class Main extends Application {
	// Initialize Screen names and files
	
	public static String startScreenID = "startScreen";
	public static String startScreenFile = "/view/StartScreen.fxml";
	public static String gameConfigID = "gameConfigScreen";
	public static String gameConfigFile = "/view/GameConfig.fxml";
	public static String mapConfigID = "mapConfigScreen";
	public static String mapConfigFile = "/view/MapConfig.fxml";
	public static String playerConfigID = "playerConfigScreen";
	public static String playerConfigFile = "/view/PlayerConfig.fxml";
	public static String standardMapID = "StandardScreen";
	public static String standardMapFile = "/view/StandardMap.fxml";
	public static String eastWestMapID = "EastWestScreen";
	public static String eastWestMapFile = "/view/EastWestMap.fxml";
	public static String townID = "TownScreen";
	public static String townFile = "/view/TownScreen.fxml";
	public static String buyMuleID = "BuyMuleScreen";
	public static String buyMuleFile = "/view/BuyMuleScreen.fxml";
	public static String equipMuleID = "EquipMuleScreen";
	public static String equipMuleFile = "/view/EquipMuleScreen.fxml";
	public static String pubID = "PubScreen";
	public static String pubFile = "/view/PubScreen.fxml";
	
	
	public static GameRunner game;
	private ScreensController mainContainer;
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		mainContainer = new ScreensController();
		mainContainer.loadScreen(startScreenID, startScreenFile);
		mainContainer.loadScreen(gameConfigID, gameConfigFile);
		mainContainer.loadScreen(mapConfigID, mapConfigFile);
		mainContainer.loadScreen(playerConfigID,  playerConfigFile);
		mainContainer.loadScreen(standardMapID, standardMapFile);
		mainContainer.loadScreen(eastWestMapID, eastWestMapFile);
		mainContainer.loadScreen(townID, townFile);
		mainContainer.loadScreen(buyMuleID, buyMuleFile);
		mainContainer.loadScreen(equipMuleID, equipMuleFile);
		mainContainer.loadScreen(pubID, pubFile);
		
		
		mainContainer.setScreen(startScreenID);
		
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		scene = new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		primaryStage.show();
		
		// create a GameRunner object
		game = new GameRunner(mainContainer, scene);
		 
	
	}
	
	
	
	
	
 	public static String getPlayerConfigID() {
 		return playerConfigID;
 	}
	
	
	// Don't necessarily need this
	public static void main(String[] args) {
		launch(args);
	}
}
