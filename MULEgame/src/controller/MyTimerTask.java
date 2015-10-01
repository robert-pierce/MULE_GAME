package controller;

import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.ProgressBar;

public class MyTimerTask extends TimerTask{
	final int MAX = 15;
    Timer myTimer;
    MyTimerTask myTimerTask;
	ProgressBar bar;
    double count;

    public MyTimerTask(ProgressBar b) {
        bar = b;
        count = 0;
    }

    @Override
    public void run() {

        bar.setProgress(count++/MAX);
        //timeLeft--;
        //changeTime();
        //myLabel.textProperty().bind(tLeft);

        if(count >= MAX){
            myTimer.cancel();
            System.out.println("Finished!");
            //changeTime();

        }
    }
}
