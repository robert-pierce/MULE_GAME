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
	public static String map1ID = "map1Screen";
	public static String map1File = "/view/Map1.fxml";
	
	public static GameRunner game;
	 
	
	@Override
	public void start(Stage primaryStage) {
		ScreensController mainContainer = new ScreensController();
		mainContainer.loadScreen(startScreenID, startScreenFile);
		mainContainer.loadScreen(gameConfigID, gameConfigFile);
		mainContainer.loadScreen(mapConfigID, mapConfigFile);
		mainContainer.loadScreen(playerConfigID,  playerConfigFile);
		mainContainer.loadScreen(map1ID, map1File);
		
		
		
		mainContainer.setScreen(startScreenID);
		
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		primaryStage.show();
		
		game = new GameRunner();
		 
	
		 scene.setOnMousePressed(new EventHandler<MouseEvent>() { 
			@Override
			public void handle(MouseEvent mouseEvent) {
				game.setXCoord(mouseEvent.getX());
				game.setYCoord(mouseEvent.getY());
			}
		 });
		 
		 
		 
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
