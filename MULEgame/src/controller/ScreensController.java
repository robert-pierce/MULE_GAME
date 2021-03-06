package controller;

import java.util.HashMap;

import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {

	private HashMap<String, Node> screens = new HashMap<>();
	private HashMap<String, FXMLLoader> loaders = new HashMap<>();
	
	/** Constructor */
	public ScreensController() {
		super();
	}
	
	/** adds a screen */
	public void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}
	
	/** adds a loader */
	public void addLoader(String name, FXMLLoader loader) {
		loaders.put(name, loader);
	}
	
	
	/** returns a screen */
	public Node getScreen(String name) {
		return screens.get(name);
	}
	
	/** Loads FXML file, adds the screen to the screens collection, 
	 	injects the screenPane to the controller
	*/
	public boolean loadScreen(String name, String resource) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent loadScreen = (Parent) loader.load();
			ControlledScreen myScreenController = (ControlledScreen) loader.getController();
			myScreenController.setScreenParent(this);
			addScreen(name, loadScreen);
			addLoader(name, loader);
			
			return true;
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}

	public boolean setScreen(final String name) {
		// check if a screen is loaded
		if (screens.get(name) != null) {
			
			
			final DoubleProperty opacity = opacityProperty();
			
			// check if there is more than one screen
			if (!getChildren().isEmpty()) {
				
				
				// this block makes the current scene disappear
				Timeline fade = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent t) {
								getChildren().remove(0);   // removes the displayed screen
								getChildren().add(0, screens.get(name));  // adds the screen
								Timeline fadeIn = new Timeline(
										new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
										new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
								fadeIn.play();		
							}
						}, new KeyValue(opacity, 0.0)));
						fade.play();
						
			} else {
				// no screen has been displayed yet, show this screen w/o any transition effects
				setOpacity(0.0);
				getChildren().add(screens.get(name));
				Timeline fadeIn = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
						new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			
			
			//make call to onLoad method for the screen just loaded
			onLoadScreen(name);
			
			return true;
		} else { 
			System.out.println("No screen loaded yet! \n");
			return false;
		}
	}
	
	// unloads a screen from the hash map
	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Screen did not exist");
			return false;
		} else {
			return true;
		}
		
	}
	
	private void onLoadScreen(String name) {
		try{
			
			FXMLLoader loader = loaders.get(name);
			ControlledScreen controller = loader.getController();
			if (controller instanceof Loadable) {
				((Loadable) controller).onLoad();
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public ControlledScreen getController(String name) {
		try{
			FXMLLoader loader = loaders.get(name);
			ControlledScreen controller = loader.getController();
			return controller;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
