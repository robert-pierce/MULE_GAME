package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import application.GameSaver.EOFClass;
import application.Map.MapSelection;
import controller.MapController;
import controller.ScreensController;
import controller.startScreenController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;



public class GameRunner  {
	//---------Enum----------------------------------------
	public enum GameState{LANDPURCHASE, MULEPURCHASE, RESOURCEPRODUCTION, RANDOMEVENT, ENDROUND}
	public enum Difficulty {BEGINNER, STANDARD, TOURNAMENT}
	public enum ActivePlayer{PLAYER1, PLAYER2, PLAYER3, PLAYER4} 
	public enum PlotType {M1, M2, M3, RIVER, PLAIN, TOWN}
	public enum MuleType {FOOD, ENERGY, SMITHORE, CRYSTITE, EMPTY}
	//-----------------------------------------------------
	
	//----------------Instance Variables-------------------
	private GameState gameState;
	private ActivePlayer activePlayer;
	private Deque<ActivePlayer> playerOrderDeque;
	private Difficulty difficulty;
	private Map gameMap;
	private Scene scene;
	private Store store;
	private ArrayList<Player> playerList;
	private ScreensController mainController;
	private ArrayList<RandomEvent> randomEvents;
	private int numRounds;
	private int round = 0;
	private boolean playerPass = false;
	private boolean firstTwoRounds = true;
	private String clickURL = "Sounds/BTN_click.mp3";
	private String radioClickURL = "Sounds/Radio_Click.wav";
	private String textBarClickURL = "Sounds/TextBar_Click.mp3";
	private String swooshURL = "Sounds/swoosh.mp3";
	private String clankURL = "Sounds/Clank.mp3"; 
	private String townMusicURL = "Sounds/Town_Music.mp3";
	private String dingURL = "Sounds/ding.wav";
	private String desertAmbianceURL = "Sounds/DesertAmbiance.mp3";
	
	
	//------------------------------------------------------------------------------
	
	//--------------Constructor-----------------------------------------------------
	public GameRunner(ScreensController mainCntrl, Scene scne) {
		scene = scne;
		playerList = new ArrayList<Player>();
		playerOrderDeque = new ArrayDeque<ActivePlayer>();
		mainController = mainCntrl;
		numRounds = 12;
	}
	//----------------End Constructor-----------------------------------------------
	
	//----------------Update and Calculate Methods----------------------------------
	public void startGame(MapSelection mapSlct) {
		setLandPurchaseState();
		incrementRound();
		addMap(mapSlct);
		createStore();
	}
	
	public void incrementRound() {
		round++;
		if (round > 2) {
			firstTwoRounds = false;
		}
		
		System.out.println("Round incremented to: " + round);
		System.out.println("First two rounds flag is " + firstTwoRounds);
	}
	
	public void calculatePlayerOrder() {
		int numPlayers = playerList.size();
		ArrayList<Player> tempPlayerList = new ArrayList<Player>(playerList);
		Collections.sort(tempPlayerList);
		Player currPlayer;
		ActivePlayer currActivePlayer;
		
		for (int i = 0; i < numPlayers; ++i) {
			currPlayer = tempPlayerList.get(i);
			
			if (i == 0) {
                currPlayer.setLowestPlayer(true);
            } else {
                currPlayer.setLowestPlayer(false);
            }

			switch (currPlayer.getPlayerNum()) {
			case 1: 
				currActivePlayer = ActivePlayer.PLAYER1;
				break;
			case 2:  
				currActivePlayer = ActivePlayer.PLAYER2;
				break;
			case 3:
				currActivePlayer = ActivePlayer.PLAYER3;
				break;
			case 4:
				currActivePlayer = ActivePlayer.PLAYER4;
				break;
			default:
				currActivePlayer = ActivePlayer.PLAYER1;
			}
			playerOrderDeque.addLast(currActivePlayer);
		}	
	}
	
	public ActivePlayer updateActivePlayerState() {
		ActivePlayer nextPlayer;
		
		if (!playerOrderDeque.isEmpty()) {
			 nextPlayer = playerOrderDeque.pop();
		} else {
			nextPlayer = null;
		}
		
		activePlayer = nextPlayer;
		return nextPlayer;
	}
	
	//----------------End Update and Calculate Methods-----------------------------------
	
	//---------------------Setter Methods------------------------------------------------
	
	public void addDifficulty(Difficulty diffclt) {
		difficulty = diffclt;
	}
	
	public void addMap(MapSelection mapTyp) {
		switch (mapTyp) {
		case STANDARD:
			gameMap = new StandardMap(mainController);
			break;
		case EASTWEST:
			gameMap = new EastWestMap(mainController);
			break;
		case MAP3:
			break;
		default:
			break;
		}
		
	}
	
	public void addPlayer(Player plyr) {
		playerList.add(plyr);
	}
	
	public void setLandPurchaseState() {
		 gameState = GameState.LANDPURCHASE;
	 }
	
	public void setGameState(GameState gmeSte) {
		gameState = gmeSte;
	}
	
	public void setPlayerPassing(boolean isPassing) {
		 playerPass = isPassing;
	}
	
	//------------------End of Setter Methods-----------------------------------
	
	//-------------Getter Methods-----------------------------------------------
	
	public Scene getScene() {
		return scene;
	}
	
	public Store getStore() {
		return store;
	}
	
	public ScreensController getMainController() {
		return mainController;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public ActivePlayer getActivePlayerState() {
		return activePlayer;
	}
	
	public boolean getFirstTwoRoundsFlag() {
		return firstTwoRounds;
	}
	
	public int getRoundNumber() {
		return round;
	}
	
	public int getNumRounds() {
		return numRounds;
	}
	
	public Map getMap() {
		return gameMap;
	}
	
	public int getNumPlayers() {
		return playerList.size();
	}
	
	public Player getActivePlayer() {
		if (activePlayer.equals(ActivePlayer.PLAYER1)) {
			return playerList.get(0);	
		} else if (activePlayer.equals(ActivePlayer.PLAYER2)) {
			return playerList.get(1);	
		} else if (activePlayer.equals(ActivePlayer.PLAYER3)) {
			return playerList.get(2);	
		} else {
			return playerList.get(3);	
		} 	
	}
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	
	public String getClickURL() {
		return clickURL;
	}
	
	public String getRadioClickURL() {
		return radioClickURL;
	}
	
	public String getTextBarClickURL() {
		return textBarClickURL;
	}
	
	public String getSwooshURL() {
		return swooshURL;
	}
	
	public String getClankURL() {
		return clankURL;
	}
	
	public String getTownMusicURL() {
		return townMusicURL;
	}
	
	public String getDingURL() {
		return dingURL;
	}
	
	public String getDesertAmbianceURL() {
		return desertAmbianceURL;
	}
	
	public void stopThemeMusic() {
		startScreenController startScreenCntrl = (startScreenController) mainController.getController(Main.startScreenID);
		final DoubleProperty volume = startScreenCntrl.getSoundPlayer().volumeProperty();
		
		Timeline fadeMusic = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(volume, 1.0)),
				new KeyFrame(new Duration(2000), new KeyValue(volume, 0.0)));
		
		fadeMusic.play();
		
		
		//startScreenCntrl.stopMusic();
	}
	
	//--------------End of Getter Methods----------------------------------------
	
	//----------------Query Methods----------------------------------------------
	
	public boolean isPlayerPassing() {
		return playerPass;
	}

	public boolean outOfPlayers() {
		return playerOrderDeque.isEmpty();
	}

	private void createStore() {
	    store =  new Store(difficulty);
	    System.out.println(store);
	}
	
	//-------------------End of Query Methods------------------------------------

	public void saveState() {
		//incrementRound();
		GameSaver gameSave = new GameSaver();
		gameSave.saveState();
	}
	
	public void loadState() {
	  final FileChooser fileChooser = new FileChooser();
      Player player = null;
     
      fileChooser.getExtensionFilters().add(new  FileChooser.ExtensionFilter("SER", "*.ser"));
      fileChooser.setInitialDirectory(
              new File("./SavedStates/")
          ); 
      File file = fileChooser.showOpenDialog( Main.game.getScene().getWindow());
      
      try (
    		  FileInputStream fileIn = new FileInputStream(file);
    		  ObjectInputStream in = new ObjectInputStream(fileIn)  
    	) {  
        Object obj = in.readObject();
        while ( !(obj instanceof EOFClass) ) {
        	if (obj instanceof Map) {
        		gameMap = (Map) obj;
        		gameMap.restoreSave();
        		
        		obj = in.readObject();
        	} else if (obj instanceof Store) {
        		store = (Store) obj;
        		store.restoreSave();
      
        		obj = in.readObject();
        	
        	} else if (obj instanceof Player) { 
	        	 player = (Player) obj;
	        	 player.restoreSave();
	        	 playerList.add(player); 
	        	 
	        	 obj = in.readObject();
        	}
        }
         
         in.close();
         fileIn.close();
      
      } catch(IOException e) {
         e.printStackTrace();
         return;
      
      } catch(ClassNotFoundException c) {
         c.printStackTrace();
         return;
      }
      
     setLandPurchaseState(); 
     round = playerList.get(0).getRound();
     addMap(playerList.get(0).getMapSelction());
     
     for (Player plyr : playerList) {
    	  Main.game.getMap().getPlots().putAll(plyr.getPlots());
    	  
    	  for (Plot plot : plyr.getPlots().values()) {
    		 MapController mapCntrl = (MapController) mainController.getController(Main.game.getMap().getMapID());
    		 mapCntrl.markPlot(plot);
    		 mapCntrl.markPlotMule(plot);
    	  }
     }
     
    
     
    
	
     
      
	}
	
	
}
