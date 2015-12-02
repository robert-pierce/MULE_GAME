package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Player;
import application.Store;
import application.Map.MapSelection;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.AudioClip;
import jfx.messagebox.MessageBox;

public class GeneralStoreController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	
	
	@Override
	public void onLoad() {
		Store store = Main.game.getStore();
		String mapID = Main.game.getMap().getMapID();
		MapController mapController;
		MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		mapController = (MapController) myController.getController(mapID);
		
		ProgressBar mapBar = mapController.getTimerTask().getTimerBar();
		DoubleProperty progProp = mapBar.progressProperty();
		timerBarGeneralStore.progressProperty().bind(progProp);	

		Player player = Main.game.getActivePlayer();
		
		SimpleIntegerProperty playerNumProperty = player.getPlayerIdProperty();
		playerIdLBL.textProperty().bind(Bindings.convert(playerNumProperty));
		
		SimpleIntegerProperty playerMoneyProperty = player.getMoneyProperty();
		playerMoneyLBL.textProperty().bind(Bindings.convert(playerMoneyProperty));
		
		SimpleIntegerProperty playerFoodProperty = player.getFoodProperty();
		playerFoodLBL.textProperty().bind(Bindings.convert(playerFoodProperty));
		
		SimpleIntegerProperty playerEnergyProperty = player.getEnergyProperty();
		playerEnergyLBL.textProperty().bind(Bindings.convert(playerEnergyProperty));
		
		SimpleIntegerProperty playerSmithoreProperty = player.getSmithoreProperty();
		playerSmithoreLBL.textProperty().bind(Bindings.convert(playerSmithoreProperty));
		
		SimpleIntegerProperty storeFoodStockProperty = store.getFoodStockProperty();
		storeFoodStockLBL.textProperty().bind(Bindings.convert(storeFoodStockProperty));
		
		SimpleIntegerProperty storeEnergyStockProperty = store.getEnergyStockProperty();
		storeEnergyStockLBL.textProperty().bind(Bindings.convert(storeEnergyStockProperty));
		
		SimpleIntegerProperty storeSmithoreStockProperty = store.getSmithoreStockProperty();
		storeSmithoreStockLBL.textProperty().bind(Bindings.convert(storeSmithoreStockProperty));
	
		SimpleIntegerProperty storeCrystiteStockProperty = store.getCrystiteStockProperty();
		storeCrytiteStockLBL.textProperty().bind(Bindings.convert(storeCrystiteStockProperty));
		
		SimpleIntegerProperty storeFoodPriceProperty = store.getFoodPriceProperty();
		storeFoodPriceLBL.textProperty().bind(Bindings.convert(storeFoodPriceProperty));
		
		SimpleIntegerProperty storeEnergyPriceProperty = store.getEnergyPriceProperty();
		storeEnergyPriceLBL.textProperty().bind(Bindings.convert(storeEnergyPriceProperty));
		
		SimpleIntegerProperty storeSmithorePriceProperty = store.getSmithorePriceProperty();
		storeSmithorePriceLBL.textProperty().bind(Bindings.convert(storeSmithorePriceProperty));
		
		SimpleIntegerProperty storeCrystitePriceProperty = store.getCrystitePriceProperty();
		storeCrystitePriceLBL.textProperty().bind(Bindings.convert(storeCrystitePriceProperty));
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML 
	public ProgressBar timerBarGeneralStore;
	
	@FXML 
	private Button sellFoodBTN;
	
	@FXML 
	private Button sellEnergyBTN;
	
	@FXML 
	private Button  sellSmithoreBTN;
	
	@FXML 
	private Button  sellCrystiteBTN;
	
	@FXML 
	private Button  buyFoodBTN;
	
	@FXML 
	private Button  buyEnergyBTN;
	
	@FXML 
	private Button  buySmithoreBTN;
	
	@FXML 
	private Button  buyCrystiteBTN;
	
	@FXML 
	private Button returnToTownBTN;
	
	@FXML
	private Label playerIdLBL;
	
	@FXML
	private Label playerMoneyLBL;
	
	@FXML
	private Label playerFoodLBL;
	
	@FXML
	private Label playerEnergyLBL;
	
	@FXML
	private Label playerSmithoreLBL;
	
	@FXML
	private Label storeFoodStockLBL;
	
	@FXML
	private Label storeEnergyStockLBL;
	
	@FXML
	private Label storeSmithoreStockLBL;
	
	@FXML
	private Label storeCrytiteStockLBL;
	
	@FXML
	private Label storeFoodPriceLBL;
	
	@FXML
	private Label storeEnergyPriceLBL;
	
	@FXML
	private Label storeSmithorePriceLBL;
	
	@FXML
	private Label storeCrystitePriceLBL;
	
	@FXML
	public MenuBar menuBar;
	
	@FXML
	public MenuItem saveMenuItem;
	
	
	public void sellFoodClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Sell Food BTN Clicked ");
		
		player.sellFood();
		
	}
	
	public void sellEnergyClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Sell Energy BTN Clicked ");
		
		player.sellEnergy();
	}
	
	public void sellSmithoreClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Sell Smithore BTN Clicked ");
		
		player.sellSmithore();
	}
	
	public void sellCrystiteClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Sell Crystite BTN Clicked ");
		
		player.sellCrystite();
	}
	
	public void buyFoodClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Buy Food BTN Clicked ");
		
		player.buyFood();
	}
	
	public void buyEnergyClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Buy Energy BTN Clicked ");
		
		player.buyEnergy();
	}
	
	public void buySmithoreClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Buy Smithore BTN Clicked ");
		
		player.buySmithore();
	}
	
	public void buyCrystiteClick() {
		Player player = Main.game.getActivePlayer();
		System.out.println("Buy Crystite BTN Clicked ");
		
		player.buyCrystite();
	}
	
	
	public void returnToTown() {
		new AudioClip(new File(Main.game.getClickURL()).toURI().toString()).play();
		System.out.println("Returning to the town");
		myController.setScreen(application.Main.townID);
	}
	
	public void saveState() {
		MessageBox.show(Main.game.getScene().getWindow(),
   			 "Game Saves are only allowed at the End of a Round",
 		         "Save Game",
 		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		//Main.game.saveState();
	}
}
