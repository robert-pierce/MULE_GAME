package application;


import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;

import com.google.gson.Gson;

import application.GameSaver.EOFClass;
import application.Map.MapSelection;
import controller.MapController;
import controller.ScreensController;

import javafx.scene.Scene;



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
		GameSaver gameSave = new GameSaver();
		gameSave.saveState();
	}
	
	public void loadState() {
	  //Store store = null;
      Player player = null;
      //ArrayList<Player> plyrList = new ArrayList<>();
		
      try (
    		  FileInputStream fileIn = new FileInputStream("./SavedStates/test.ser");
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
