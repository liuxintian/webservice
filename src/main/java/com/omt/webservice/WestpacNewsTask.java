package com.omt.webservice;


import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.omt.asxdata.controller.WestWebClient;


public class WestpacNewsTask extends TimerTask{
	private Logger omtlogger = Logger.getLogger(WestpacNewsTask.class);

	private Timer timer;
	private int delay;
    public WestpacNewsTask(int delay)
    {
        timer = new Timer("WestpacNewsTask",true);
        this.delay = delay;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		scanAndProcess();
	}
	
	private void scanAndProcess(){
		omtlogger.info("WestpacNewsTask start...");
		WestWebClient.webPageLoad();
		omtlogger.info("WestpacNewsTask...End");
	}
	
    public void start()
    {
    	// Every 8 hour 
		if(timer == null){
			timer = new Timer("WestpacNewsTask",true);
		}
		timer.schedule(this, delay, 10*60*1000);
    }
    
    public void stop()
    {
    	if(timer != null) timer.cancel();
    }
}
