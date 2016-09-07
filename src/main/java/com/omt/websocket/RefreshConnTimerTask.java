package com.omt.websocket;


import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.omt.config.StaticConfig;


public class RefreshConnTimerTask extends TimerTask{
	private Logger omtlogger = Logger.getLogger(RefreshConnTimerTask.class);

	private Timer timer;
	
    public RefreshConnTimerTask()
    {
        timer = new Timer("RefreshConnTimerTask",true);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 1. each item date time, if(overdue) close;
		//omtlogger.info("After 19 minutes, start to refresh token...");
		scanAndProcess();
	}
	
	private void scanAndProcess(){
		omtlogger.info("start refresh token ... with interval(m):"+StaticConfig.wssTokenInterval);
		WebSocketClient.RestTempGetTokenRefresh();
		if(WebSocketClient.websocket != null){
			WebSocketClient.websocket.sendText(WebSocketClient.onOpensendString());
		}else{
			WebSocketClient.positivelyReconnecct("Met null websocket client!");
		}
	}
	
    public void start()
    {
		if(timer == null){
			timer = new Timer("RefreshConnTimerTask",true);
		}
		timer.schedule(this, 5*1000, StaticConfig.wssTokenInterval * 60 * 1000);
    }

    public void stop()
    {
        if(timer != null) timer.cancel();
    }
}
