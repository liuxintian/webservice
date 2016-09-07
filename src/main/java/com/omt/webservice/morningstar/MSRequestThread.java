package com.omt.webservice.morningstar;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.omt.config.StaticConfig;
import com.omt.websocket.entity.CompanyList;

/**
 * Each startprocess would start one thread processtask
 * @author tonyliu
 *
 */
public class MSRequestThread extends TimerTask{
	private Timer timer;
	private boolean running = false;
	private static Logger omtlogger = Logger.getLogger(MSRequestThread.class);
	private boolean needRun = true;
    public MSRequestThread() {
    	timer = new Timer("MSRequestThread", true);
    }

    public void start()
    {
    	omtlogger.info("Starting MSRequestThread...");
		if(timer == null){
			timer = new Timer("MSRequestThread",true);
		}
        timer.schedule(this, 5*1000, 1*1000);
    }
    
    @Override
	public void run() {
		// TODO Auto-generated method stub
    	if(StaticConfig.logswitcher == 2) omtlogger.info("----- MsUtility before dealing-- queue size is:" + MsUtility.CODES_QUEUE.size() + "--total send count:"+ MsUtility.totalRequestCount);
        //ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 200, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());  
   	
    	if(running || MsUtility.CODES_QUEUE.size() == 0) return;
    	int count = 0;
    	Map<String, CompanyList> companymap = new HashMap<String, CompanyList>();
    	Collection<CompanyList> companylist = new HashSet<CompanyList>();
    	while((MsUtility.CODES_QUEUE.size() > 0) && (needRun)){
    		running = true;
    		try{
    			CompanyList uvo = MsUtility.CODES_QUEUE.take();
//    			if(uvo.getCode().equalsIgnoreCase("ORA")) {
//    				omtlogger.info("\n\nTack ORA-----\n\n");
//    			}
    			companymap.put(uvo.getUniqstr(), uvo);
    			count ++;
    			if(count == 100){
    				MsUtility.totalRequestCount += count;
    				companylist = companymap.values();
    				MsUtility.getSharePriceVO(companylist);
    				
    				companymap = new HashMap<String, CompanyList>();
    				count = 0;
    				Thread.sleep(StaticConfig.TIME_INTERVAL_REQ);
    			}
    			if(MsUtility.CODES_QUEUE.size() == 0){
    				MsUtility.totalRequestCount += count;
    				companylist = companymap.values();
    				MsUtility.getSharePriceVO(companylist);
    				
    				companymap = new HashMap<String, CompanyList>();
    				count = 0;
    				Thread.sleep(StaticConfig.TIME_INTERVAL_REQ);
    			}
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    	}
    	running = false;
    	if(StaticConfig.logswitcher == 2) omtlogger.info("===== MsUtility after dealing-- queue size is:" + MsUtility.CODES_QUEUE.size() + "--total send count:" + MsUtility.totalRequestCount);
    }
    public void stop()
    {
    	omtlogger.info("Stopping MSRequestThread...");
    	if(timer != null) timer.cancel();
    	needRun = false;
    	//    	MsUtility.CODES_QUEUE.clear();
    }
}
