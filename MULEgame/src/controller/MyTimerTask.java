package controller;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import application.Map.MapSelection;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.AudioClip;

public class MyTimerTask extends TimerTask{
	MapController mapController;
    Timer myTimer;
    MyTimerTask myTimerTask;
	ProgressBar bar;
    int count;
    double timeLimit;
    private AudioClip audio;
    
    public MyTimerTask(double tmeLmt, ProgressBar b, Timer myTmr, MapController mapCntrl) {
        bar = b;
        count = 0;
        myTimer = myTmr;
        mapController = mapCntrl;
        timeLimit = tmeLmt;
    }

    @Override
    public void run() {
    	Platform.runLater(new Runnable() {
    		public void run() {
    			bar.setProgress(1 - (count++/timeLimit));
    	        if(count >= timeLimit){
    	        	playDing();
    	        	myTimer.cancel();
    	        	System.out.println("Time limit int timer task is " + timeLimit);
    	            mapController.timeExpired();
    	        }
    		}
    	});
    }
    
    public int stopTimer() {
    	myTimer.cancel();
    	return count;
    }
    
    private void playDing() {
    	new AudioClip(new File(Main.game.getDingURL()).toURI().toString()).play();
    }
    
    public ProgressBar getTimerBar() {
    	return bar;
    }
    
}
