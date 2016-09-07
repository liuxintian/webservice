package com.omt.websocket;


import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;


public class SendSubscribeTask extends TimerTask{
	private Logger omtlogger = Logger.getLogger(SendSubscribeTask.class);

	private Timer timer;
	
    public SendSubscribeTask()
    {
        timer = new Timer("SendSubscribeTask", true);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendsubscribemessage();
	}
	
	private void sendsubscribemessage(){
		if(WebSocketClient.websocket == null) return;
		int count = 0;
		try{
	   		 for(final String company: WebSocketClient.COMPANIES){
	    		 // omtlogger.info("-------send subscribe message:"+ company);
	    		 count ++;
	    		 if((count % 100) == 0){
	    			 Thread.sleep(3000);
	    		 }
	    		 WebSocketClient.websocket.sendText(company);
			 }			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		omtlogger.info("sendsubscribemessage ... End with company count :" + count);
	}
	
    public void start()
    {
		omtlogger.info("sendsubscribemessage start ...");
		if(timer == null){
			timer = new Timer("SendSubscribeTask",true);
		}
        timer.schedule(this, 1 * 1000); 
    }

    public void stop()
    {
    	if(timer != null) timer.cancel();
    }
}
