package controller;

import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import application.Map.MapSelection;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class MyTimerTask extends TimerTask{
	MapController mapController;
    Timer myTimer;
    MyTimerTask myTimerTask;
	ProgressBar bar;
    int count;
    double timeLimit;
    
    
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
    			bar.setProgress(count++/timeLimit);
    	        if(count >= timeLimit){
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
}
