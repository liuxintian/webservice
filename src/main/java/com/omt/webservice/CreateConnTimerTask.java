package com.omt.webservice;


import java.util.Timer;
import java.util.TimerTask;

import com.omt.websocket.WebSocketClient;

/**
 * CreateConnTimerTask
 * @author tonyliu
 *
 */
public class CreateConnTimerTask extends TimerTask{

	private Timer timer;
	
	private int delay;
	
    public CreateConnTimerTask(int delay)
    {
        timer = new Timer("CreateConnTimerTask", true);   
        this.delay = delay; 
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		WebSocketClient.clientRun();
	}
	
    public void start() 
    {
		if(timer == null){
			timer = new Timer("CreateConnTimerTask",true);
		}
		timer.schedule(this, delay); 
    }

    public void stop()
    {
    	if(timer != null) timer.cancel();
        WebSocketClient.closeWSClient();
    }
}
