package com.omt.webservice.morningstar;

import java.util.Timer;
import java.util.TimerTask;

import com.omt.config.StaticConfig;
import com.omt.webservice.UtilLibs;
import com.omt.websocket.WebSocketClient;

/**
 * Daemon task for getting share price timer task
 * @author tonyliu
 *
 */
public class DaemonTask extends TimerTask{

	private Timer timer;
	private MSRequestThread msRequestThread;
	
    public void start()
    {
		if(timer == null){
			timer = new Timer("DaemonTask",true);
		}
        timer.schedule(this, 10*1000, 10*1000);
    }
    /**
      	146,1, EDT,10:00,16:00,18:59
		146,2, EDT,10:00,16:00,17:38
		146,3, EDT,10:00,16:00,17:38
		146,10,EDT,10:00,16:11,18:59
		146,13,EDT,10:00,16:00,17:38
		146,16,EDT,10:00,16:00,18:59
		146,17,EDT,09:30,15:00,18:59
		146,18,EDT,09:30,15:00,18:59
		146|1|EDT|10:00:00|16:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|2|EDT|10:00:00|16:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|3|EDT|10:00:00|16:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|8|EDT|10:00:00|16:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|10|EDT|10:00:00|16:11:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|13|EDT|10:00:00|16:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|16|EDT|10:00:00|16:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|17|EDT|09:30:00|15:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
		146|18|EDT|09:30:00|15:00:00|19:30:00|16:45:00|Australia Stock Exchange|AUS Eastern Standard Time|Sat-Sun
     */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(UtilLibs.isNeedRunMsTask(StaticConfig.MS_TASK_24H_START, StaticConfig.MS_TASK_24H_STOP)){
			if(msRequestThread == null){
				WebSocketClient.loadTickerCodeList(true);
				msRequestThread = new MSRequestThread();
				msRequestThread.start();
			}
		}else{
			if(msRequestThread != null){
				msRequestThread.stop();
			}
			msRequestThread = null;
		}
	}
	
    public void stop()
    {
		if(timer != null){
			timer.cancel();
		}
		if(msRequestThread != null){
			msRequestThread.stop();
		}
		msRequestThread = null;
		timer = null;
    }	
}
