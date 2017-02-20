package com.learningHelper.timing;

import java.util.Timer;
import java.util.TimerTask;

import com.learningHelper.gui.FrameManager;

public class TimeCatcher {

	private Timer timer;
	private int time;
	private byte TIME_DELAY=10;
	private boolean isRunning;
	private long maxBreakTime = 5_000;
	private FrameManager manager;
	
	
	public TimeCatcher(FrameManager frame){
		isRunning = false;
		manager=frame;
	}
	
	public boolean cleanStopWatch(){
		time=0;    	
		boolean wasRunning = isRunning;
		if (!isRunning){
			startTimer();
		}
		return wasRunning;
		
    }
	
	private void startTimer() {
		timer=new Timer();  
		isRunning=true;
	    TimerTask task=new TimerTask(){
	        @Override
	        public void run(){       
	            time+=TIME_DELAY; 
	            if (time>maxBreakTime){
	            	stopTimer();
	            	manager.timePassed();
	            }
	        }
	    };
	    timer.scheduleAtFixedRate(task, 0, TIME_DELAY);
	}

	public void stopTimer(){		
		if (!isRunning){
			return;
		}
		isRunning = false;
		timer.cancel();
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
}
