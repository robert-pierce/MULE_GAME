package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import application.Map;
import application.Plot;
import application.GameRunner.ActivePlayer;
import application.GameRunner.GameState;
import application.GameRunner.MuleType;
import application.Map.MapSelection;
import application.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import jfx.messagebox.MessageBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import java.awt.Point;

public class MapController implements Initializable, ControlledScreen, Loadable {
    ScreensController myController;
	private GameState gameState;
	boolean firstTwoRoundsFlag = true;
	private ActivePlayer activePlayerState;
	private double xCor, yCor;
	private MyTimerTask myTimerTask;
	private Timer myTimer;
	private int timeLimit;
	final int TIMERUPDATERATE = 10;   // milliseconds between update
	final int TIMEFACTOR = 1000 / TIMERUPDATERATE;  // adjust time limit passed to task 
	//private int numPlayers;
	private Map map;
	private double rectSize = 30;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@Override
	public void onLoad() {
		int msgBoxRslt;
		gameState = Main.game.getGameState();
		roundNumLBL.setText(Integer.toString(Main.game.getRoundNumber()));
		
		
		// what to do if in Land Purchase State
		if (gameState.equals(GameState.LANDPURCHASE)) {
			//numPlayers = Main.game.getNumPlayers();
			firstTwoRoundsFlag = Main.game.getFirstTwoRoundsFlag();
			Main.game.calculatePlayerOrder();
			msgBoxRslt = handleLandPurchaseTurn();
			updatePlayerPass(msgBoxRslt);
		}
		
		// what to do if in Resource Production Phase
		if (gameState.equals(GameState.RESOURCEPRODUCTION)) {
			endResourceProductionPhase();
		}
	}
	
	//-----------------FXML Injections---------------------------------------
	
	@FXML
	public Canvas plotMarkerCanvas;
	
	@FXML
	public Label roundNumLBL;
	
	@FXML
	public ProgressBar timerBar;
	
	@FXML
	public MenuBar menuBar;
	
	@FXML
	public MenuItem saveMenuItem;
	//----------------End FXML Injections------------------------------------
	
	//-------------FXML On-Action Methods------------------------------------
	
	@FXML	
	public void mouseClick(MouseEvent mouseEvent) {
		int msgBoxRslt;
		Plot currentPlot;
		gameState = retrieveGameState();
		activePlayerState = retrieveActivePlayerState();
		//numPlayers = retrieveNumPlayers();
		map = Main.game.getMap();
		
		// get (x,y) coordinates
		xCor = mouseEvent.getX();
		yCor = mouseEvent.getY();	
		
		
		//-------------------LAND PURCHASE STATE------------------------------------------------------------
		if (gameState.equals(GameState.LANDPURCHASE)) {	
				if (!Main.game.isPlayerPassing()) {	   
					currentPlot = map.purchasePlot(xCor, yCor, application.Main.game.getActivePlayer(), firstTwoRoundsFlag); // purchase plot	
					if (currentPlot != null) {
						markPlot(currentPlot);   // mark the purchased plot
					    
						// handle the players turn
	                    msgBoxRslt = handleLandPurchaseTurn();
	                    
	                    // update the next player's response to buy or pass
	                    updatePlayerPass(msgBoxRslt);						
					}
				} else {
					 // skip this player's turn and move to the next player
					 msgBoxRslt = handleLandPurchaseTurn();
					 
					 // update the next player's response
	                 updatePlayerPass(msgBoxRslt);	
				}
		}
			
		
		//----------------- MULE PURCHASE STATE-------------------------------------------------------------
		if (gameState.equals(GameState.MULEPURCHASE)) {
			currentPlot = map.placeMule( xCor,  yCor, Main.game.getActivePlayer());
			if (currentPlot != null) {
				markPlotMule(currentPlot);
			}
			
		}
		
		//--------------------------End of MULE PURCHASE STATE----------------------------------	
	}
	//----------------------------End On-Action Methods-------------------------------------------------
	
	
	//------------------------Helper Methods---------------------------------------------------
	
	private void updatePlayerPass(int msgBoxRslt) {
		if (msgBoxRslt == MessageBox.NO) {
			Main.game.setPlayerPassing(true);
		} else {
			Main.game.setPlayerPassing(false);
		}
	}
	
	private int handleLandPurchaseTurn() {
		int msgBoxRslt;
		int playerNum;
		int ENDPURCHASEPHASE = -1;
		StringBuilder announcement = new StringBuilder();
		Main.game.updateActivePlayerState();
		activePlayerState = Main.game.getActivePlayerState();
		
		if (activePlayerState != null) {
			switch (activePlayerState) {
				case PLAYER4:
					playerNum = 4;
					break;
				case PLAYER3:
					playerNum = 3;
					break;
				case PLAYER2:
					playerNum = 2;
					break;
				default:
					playerNum = 1;	
			}
	
			System.out.println("makeAnnouncement method firstTwoRoundsFlag state is: " + firstTwoRoundsFlag);
		if (firstTwoRoundsFlag) {
			announcement.append("Player " + playerNum + " would you like to buy a plot? Plots are FREE!");
													
		} else {
			announcement.append("Player " + playerNum +  " would you like to buy a plot? Plots are $300");
		}
	
		// present popUp message and get results
		msgBoxRslt = MessageBox.show(Main.game.getScene().getWindow(),
									announcement.toString(),
									"Information dialog",
									MessageBox.ICON_INFORMATION | MessageBox.YES | MessageBox.NO);
		
		if (!firstTwoRoundsFlag && Main.game.getActivePlayer().getMoney() < 300 && msgBoxRslt == MessageBox.YES) {
			MessageBox.show(Main.game.getScene().getWindow(),
							"Sorry, you do not have enough money to purchase a plot",
							"Information dialog",
							MessageBox.ICON_INFORMATION | MessageBox.OK);
			
			msgBoxRslt = MessageBox.NO;

		}
		
		return msgBoxRslt;
		
		} else {  // if we are at the last player the end land purchase round
		endLandPurchasePhase();
		return ENDPURCHASEPHASE;
		}	
	}
	
	private void endLandPurchasePhase() {
		MessageBox.show(Main.game.getScene().getWindow(),
		         "End of Land Purchase Phase",
		         "Information dialog",
		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		
		beginMulePurchasePhase();
	}
	
	
	
	private void beginMulePurchasePhase() {
		Main.game.setGameState(GameState.MULEPURCHASE);
		Main.game.calculatePlayerOrder();
		
		MessageBox.show(Main.game.getScene().getWindow(),
		         "Begin of Mule Purchase Phase",
		         "Information dialog",
		         MessageBox.ICON_INFORMATION | MessageBox.OK);
	
		handleMulePurchasePhaseTurn();
	}
	
	private void handleMulePurchasePhaseTurn() {
		int playerNum;
		StringBuilder announcement = new StringBuilder();
		Main.game.updateActivePlayerState();
		activePlayerState = Main.game.getActivePlayerState();
		
		
		
		if (activePlayerState != null) {
//			switch (activePlayerState) {
//			case PLAYER4:
//				playerNum = 4;
//				break;
//			case PLAYER3:
//				playerNum = 3;
//				break;
//			case PLAYER2:
//				playerNum = 2;
//				break;
//			default:
//				playerNum = 1;	
//			}
			
			announcement.append("Player " + Main.game.getActivePlayer().getPlayerNum() +  " it is your turn to acquire goods!");
			MessageBox.show(Main.game.getScene().getWindow(),
						announcement.toString(),
						"Information dialog",
						MessageBox.ICON_INFORMATION | MessageBox.OK);
			
			timeLimit = Main.game.getActivePlayer().computeTime();
			System.out.println("Time Limit is: " + timeLimit);
			startTimer();
			System.out.println("Start Timer Called");
			
		} else {  // if we are at the last player then end Mule purchase round
			endMulePurchasePhase();
			
		}	
	}
	
	private void handleRandomEvent() {
		System.out.println("handleRandomEvent called in MapController");
		
		int playerIdx = 0;
        Player currPlayer;

        if (activePlayerState != null) {
            switch (activePlayerState) {
                case PLAYER4:
                    playerIdx = 3;
                    break;
                case PLAYER3:
                    playerIdx = 2;
                    break;
                case PLAYER2:
                    playerIdx = 1;
                    break;
                default:
                    playerIdx = 0;
            }
        }

        currPlayer = Main.game.getPlayerList().get(playerIdx);
        double willRandomEventHappen = Math.random();
        if (willRandomEventHappen <= 0.27) {
            String message = currPlayer.runRandomEvent();
            MessageBox.show(Main.game.getScene().getWindow(),
                    message,
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }
	}
	
	// implement end of round method
	public void endMulePurchasePhase() {
		//System.out.println("End Round method called in MapController");
		timerBar.setProgress(0);
		Main.game.setGameState(GameState.RESOURCEPRODUCTION);
		System.out.println("Game State set to: " + Main.game.getGameState());
		
		handleResourceProductionPhase();	
	}
	
	private void handleResourceProductionPhase() {
		calculateProduction();
       // List<Player> players = Main.game.getPlayerList();
        
//        MessageBox.show(Main.game.getScene().getWindow(),
//  		         "Calculating Production",
//        		"Calculating Production",
//  		         MessageBox.ICON_INFORMATION | MessageBox.OK);
        
        
        // WOULD LIKE TO SHOW A NEW SCREEN HERE!
        myController.setScreen(Main.resourceProductionID);
        
        
//		if (players.size() > 0) {
//        	MessageBox.show(Main.game.getScene().getWindow(),
//   		         "Player 1 - Food: " + players.get(0).getFood() +
//   		         	"  Energy: " + players.get(0).getEnergy() +
//   		         	"  Ore: " + players.get(0).getSmithore() +
//   		         	"  Crystite: " + players.get(0).getCrystite(),
//   		         "Player 1 End of Round",
//   		         MessageBox.ICON_INFORMATION | MessageBox.OK);
//        }
//        if (players.size() > 1) {
//        	MessageBox.show(Main.game.getScene().getWindow(),
//        			"Player 2 - Food: " + players.get(1).getFood() +
//				 		"  Energy: " + players.get(1).getEnergy() +
//				 		"  Ore: " + players.get(1).getSmithore() +
//				 		"  Crystite: " + players.get(1).getCrystite(),
//      		         "Player 2 End of Round",
//      		         MessageBox.ICON_INFORMATION | MessageBox.OK);
//        }
//        if (players.size() > 2) {
//        	MessageBox.show(Main.game.getScene().getWindow(),
//        			"Player 3 - Food: " + players.get(2).getFood() +
//				 		"  Energy: " + players.get(2).getEnergy() +
//				 		"  Ore: " + players.get(2).getSmithore() +
//				 		"  Crystite: " + players.get(2).getCrystite(),
//      		         "Player 3 End of Round",
//      		         MessageBox.ICON_INFORMATION | MessageBox.OK);
//        }
//        if (players.size() > 3) {
//        	MessageBox.show(Main.game.getScene().getWindow(),
//        			"Player 4 - Food: " + players.get(3).getFood() +
//				 		"  Energy: " + players.get(3).getEnergy() +
//				 		"  Ore: " + players.get(3).getSmithore() +
//				 		"  Crystite: " + players.get(3).getCrystite(),
//      		         "Player 4 End of Round",
//      		         MessageBox.ICON_INFORMATION | MessageBox.OK);
//        }
        
        //endResourceProductionPhase();
	}
	
	public void endResourceProductionPhase() {
		//Main.game.setGameState(GameState.RESOURCEPRODUCTION);
		
		endRound();
	}
	
	public void endRound() {
		System.out.println("End Round called in MapController");
		Main.game.incrementRound();
		System.out.println("Round Incremented to round " + Main.game.getRoundNumber());
		Main.game.setGameState(GameState.LANDPURCHASE);
		System.out.println("Game State set to " + Main.game.getGameState());
		onLoad();
	}
	
	// implement http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=150end of turn method
	public void endTurn() {
		String mapID = getMapID();
		System.out.println("End of Turn method called in MapController");
		handleRandomEvent();
		if (!myController.getChildren().get(0).equals(myController.getScreen(mapID))) {
			myController.setScreen(mapID);
		}
		handleMulePurchasePhaseTurn(); 
	}
	
	private void calculateProduction() {
        List<Player> players = Main.game.getPlayerList();
        for (Player player : players) {
            player.calculateProduction();
        }
    }
	
	private String getMapID() {
		String mapID= "";
		MapSelection mapSelection = Main.game.getMap().getMapSelection();
		
		switch (mapSelection) {
		case STANDARD:
			mapID = Main.standardMapID;
			break;
		case EASTWEST:
			mapID = Main.eastWestMapID;
			break;
		case MAP3:
			mapID = ""; 
		}
		return mapID;
	}


	// marks a plot with the appropriate color
	public void markPlot(Plot currentPlot) {
		GraphicsContext gc = plotMarkerCanvas.getGraphicsContext2D();
		
		 
		gc.setFill(currentPlot.getOwner().getColor());
		//gc.setFill(Main.game.getActivePlayer().getColor());
         Point center = currentPlot.getCenter();
         gc.fillRect(center.getX()-rectSize/2, center.getY()-rectSize/2, rectSize, rectSize);   
	}

	public void markPlotMule(Plot currentPlot) {
		MuleType muleType = currentPlot.getMule().getMuleType();
		GraphicsContext gc = plotMarkerCanvas.getGraphicsContext2D();
		Point center = currentPlot.getCenter();
		Image img;
		
		switch (muleType) {
			case FOOD:
				 img = new Image("file:images/wheat.jpg");
				break;
			case ENERGY:
				img = new Image("file:images/energy.png");
				break;
			case SMITHORE:
				img = new Image("file:images/mining.png");
				break;
			case CRYSTITE:
				img = new Image("file:images/diamond.png");
				break;
			default:
				img = new Image("file:images/muleEmpty.png");
			
		}
		
		gc.clearRect( (center.getX()-rectSize/2)+ 2.5, (center.getY()-rectSize/2) + 2.5, rectSize-5, rectSize-5);
        gc.drawImage(img, (center.getX()-rectSize/2)+ 2.5, (center.getY()-rectSize/2) + 2.5, rectSize-5, rectSize-5);
	}

	
	/**
	 * starts a timer
	 * @param timeLimit the number of seconds the timer 
	 * should run for
	 */
	
	public void startTimer() {
		myTimer = new Timer();
		timerBar.setProgress(0);
        myTimerTask = new MyTimerTask(timeLimit*TIMEFACTOR, timerBar, myTimer, this);
        myTimer.scheduleAtFixedRate(myTimerTask, 0, TIMERUPDATERATE);    
	}
	
	public double stopTimer() {
		int count = myTimerTask.stopTimer();
		timerBar.setProgress(0);
		return timeLimit - (count/TIMEFACTOR);   // returns the number of seconds the timer ran for
	}

	public void timeExpired() {
		timerBar.setProgress(0);
		System.out.println("MapController - Time is Up!");
		endTurn();
	}
	
	private GameState retrieveGameState() {
		return Main.game.getGameState();
	}

	private ActivePlayer retrieveActivePlayerState() {
		return Main.game.getActivePlayerState();
	}

	public MyTimerTask getTimerTask() {
		return myTimerTask;
	}
	
	public Timer getTimer() {
		return myTimer;
	}

	public void saveState() {
		Main.game.saveState();
	}


	
	
	
}
