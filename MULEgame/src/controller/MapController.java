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
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
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
import java.io.File;

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
	private MediaPlayer soundPlayer;
	private AudioClip click;
	private File soundFile;
	
	
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
		
		if (soundPlayer == null) {
			soundFile = new File(Main.game.getDesertAmbianceURL());
			soundPlayer = new MediaPlayer(new Media(soundFile.toURI().toString()));
			soundPlayer.setCycleCount(soundPlayer.INDEFINITE);
			soundPlayer.setVolume(0);
			soundPlayer.play();
		} 
		startMusic();
		
	}
	
	private void startMusic() {
		if (soundPlayer.getVolume() == 0.0) {
			final DoubleProperty volume = soundPlayer.volumeProperty();
			Timeline fadeMusic = new Timeline(
					new KeyFrame(Duration.ZERO, new KeyValue(volume, 0)),
					new KeyFrame(new Duration(2000), new KeyValue(volume, 1.0)));
			
			fadeMusic.play();	
		}
	}

	public void silenceMusic() {
		final DoubleProperty volume = soundPlayer.volumeProperty();		
		Timeline fadeMusic = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(volume, 1.0)),
				new KeyFrame(new Duration(2000), new KeyValue(volume, 0.0)));
		
		fadeMusic.play();
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
		System.out.println(xCor);
		
		
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
           
            new AudioClip(new File(Main.game.getDingURL()).toURI().toString()).play();
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
		
		String message;
		double willRoundRandomHappen = Math.random();
        if (willRoundRandomHappen > 0.9) {
            if (willRoundRandomHappen > 0.95) {
                for (Player player : Main.game.getPlayerList()) {
                    player.changeFoodProperty(1);
                }
                message = "A FREAK STORM DROPPED FISH ALL OVER THE TOWN! EVERYONE GAINS 1 FOOD UNIT";
            } else {
                for (Player player : Main.game.getPlayerList()) {
                    player.changeMoneyProperty(-100);
                }
                message = "THE STOCK MARKET CRASHED! EVERYONE LOSES $100";
            }
            MessageBox.show(Main.game.getScene().getWindow(),
                    message,
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }
		
		
		handleResourceProductionPhase();	
	}
	
	private void handleResourceProductionPhase() {
		calculateProduction();
                
        // WOULD LIKE TO SHOW A NEW SCREEN HERE!
        myController.setScreen(Main.resourceProductionID);
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
	
	// implement end of turn method
	public void endTurn() {
		String mapID = Main.game.getMap().getMapID();
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
	
//	private String getMapID() {
//		String mapID= "";
//		MapSelection mapSelection = Main.game.getMap().getMapSelection();
//		
//		switch (mapSelection) {
//		case STANDARD:
//			mapID = Main.standardMapID;
//			break;
//		case EASTWEST:
//			mapID = Main.eastWestMapID;
//			break;
//		case MAP3:
//			mapID = ""; 
//		}
//		return mapID;
//	}


	// marks a plot with the appropriate color
	public void markPlot(Plot currentPlot) {
		GraphicsContext gc = plotMarkerCanvas.getGraphicsContext2D();
		new AudioClip(new File(Main.game.getSwooshURL()).toURI().toString()).play();
		 
		gc.setFill(currentPlot.getOwner().getColor());
		//gc.setFill(Main.game.getActivePlayer().getColor());
         Point center = currentPlot.getCenter();
         gc.fillRect(center.getX()-rectSize/2, center.getY()-rectSize/2, rectSize, rectSize);   
	}
	
	

	public void markPlotMule(Plot currentPlot) {
		if (currentPlot.getMule() != null) {
			new AudioClip(new File(Main.game.getClankURL()).toURI().toString()).play();
			
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
		townController townCntrl = (townController)Main.game.getMainController().getController(Main.townID);
		townCntrl.silenceMusic();
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
		MessageBox.show(Main.game.getScene().getWindow(),
    			 "Game Saves are only allowed at the End of a Round",
  		         "Save Game",
  		         MessageBox.ICON_INFORMATION | MessageBox.OK);
		
		//Main.game.saveState();
	}


	
	
	
}
