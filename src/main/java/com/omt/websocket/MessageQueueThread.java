package com.omt.websocket;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.omt.config.StaticConfig;

/**
 * Each startprocess would start one thread processtask
 * @author tonyliu
 *
 */
public class MessageQueueThread extends TimerTask{
	private Logger omtlogger = Logger.getLogger(MessageQueueThread.class);
	private Timer timer;
	private int count;
	private boolean running = false;
    public MessageQueueThread() {
    	timer = new Timer("MessageQueueThread", true);
    }

    public void start()
    {
		if(timer == null){
			timer = new Timer("MessageQueueThread",true);
		}
        timer.schedule(this, 5*1000, 2000);
    }
    
    @Override
	public void run() {
		// TODO Auto-generated method stub
    	
    	if(StaticConfig.logswitcher == 2) omtlogger.info("-----before dealing-- queue size is:" + WebSocketClient.MESSAGE_QUEUE.size() + " running flag:"+running +"--total count tackled:" + count +"---total received:"+ WebSocketClient.received);
    	if(running || WebSocketClient.MESSAGE_QUEUE.size() == 0) return;

    	while(WebSocketClient.MESSAGE_QUEUE.size() > 0){
    		running = true;
    		try{
    			WebSocketClient.MessageDealingRun(WebSocketClient.MESSAGE_QUEUE.take());
    			count ++;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    	}
    	running = false;
    	if(StaticConfig.logswitcher == 2) omtlogger.info("=====after dealing-- queue size is:" + WebSocketClient.MESSAGE_QUEUE.size() +"--total count tackled:" + count +"---total received:"+ WebSocketClient.received);
    }
    public void stop()
    {
    	if(timer != null) {
    		timer.cancel();
    	}
    	WebSocketClient.MESSAGE_QUEUE.clear();
    }
}
