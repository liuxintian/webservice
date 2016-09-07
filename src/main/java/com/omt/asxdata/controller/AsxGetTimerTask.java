package com.omt.asxdata.controller;


import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

/**
 * Get data from ASX via web page url
 * @author tonyliu
 *
 */
public class AsxGetTimerTask extends TimerTask{
	private Logger omtlogger = Logger.getLogger(AsxGetTimerTask.class);

	private Timer timer;
	
	private int delay;
	
    public AsxGetTimerTask(int delay)
    {
        timer = new Timer("AsxGetTimerTask",true);
        this.delay = delay;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		startProcess();
	}
	
	private void startProcess(){
		
		omtlogger.info("AsxGetTimerTask start ...");
		AsxWebClient.webclientPage();
		omtlogger.info("AsxGetTimerTask ...End");
	}
	
    public void start()
    {
		if(timer == null){
			timer = new Timer("AsxGetTimerTask",true);
		}
        timer.schedule(this, delay, 5*60*1000); // 5 minutes to pull from the asx web site
    }

    public void stop()
    {
    	if(timer != null) timer.cancel();
    }
}
