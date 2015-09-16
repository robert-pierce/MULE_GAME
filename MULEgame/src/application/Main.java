package application;
	
import controller.ScreensController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	// Initialize Screen names and files
	
	public static String startScreenID = "startScreen";
	public static String startScreenFile = "/view/StartScreen.fxml";
	public static String gameConfigID = "gameConfigScreen";
	public static String gameConfigFile = "/view/GameConfig.fxml";
	
	
	
	@Override
	public void start(Stage primaryStage) {
		ScreensController mainContainer = new ScreensController();
		mainContainer.loadScreen(startScreenID, startScreenFile);
		mainContainer.loadScreen(gameConfigID, gameConfigFile);
		
		mainContainer.setScreen(startScreenID);
		
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
