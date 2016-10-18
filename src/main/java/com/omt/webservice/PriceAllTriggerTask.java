package com.omt.webservice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.client.RestTemplate;

import com.omt.config.StaticConfig;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.webservice.morningstar.entity.ShareDataM;


public class PriceAllTriggerTask extends TimerTask{
	private Logger omtlogger = Logger.getLogger(PriceAllTriggerTask.class);

	private Timer timer;
	private int delay;
	
    public PriceAllTriggerTask(int delay)
    {
        timer = new Timer("PriceAllTriggerTask",true);
        this.delay = delay;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		scanAndProcess();
	}
	
    public void start()
    {
    	// Every 30 seconds
		if(timer == null){
			timer = new Timer("PriceAllTriggerTask",true);
		}
		timer.schedule(this, delay, StaticConfig.TriggerAllCallInterval*1000);
    }
    
    public void stop()
    {
    	if(timer != null)  {
    		timer.cancel();
    		timer = null;
    	}
    }
    
	private void scanAndProcess(){
		omtlogger.info("PriceAllTriggerTask start...");
		// 1. get old data 2. get new data 3. compare the two 4. generate the changed list
		List<ShareDataM> changedPriceList = new ArrayList<ShareDataM>();
		Query query = new Query();
		query.addCriteria(Criteria.where("dataChangedAll").is(true));
		List<MsSharePrice> sharePriceList = StaticMongoTemplate.getStaticMongoTemplate().find(query, MsSharePrice.class);
		
		if(sharePriceList != null){
			for (MsSharePrice msp: sharePriceList){
				changedPriceList.add(msp.getData());
			} 
		}
		
		// 5. send restful request
		if(changedPriceList != null){
			omtlogger.info("PriceAllTriggerTask...start with changedPriceList.size():"+changedPriceList.size());
			RestTempPostData(changedPriceList);
			
			Update update = new Update();
			update.set("dataChangedAll", false);
			StaticMongoTemplate.getStaticMongoTemplate().updateMulti(query, update, MsSharePrice.class);
		}
	}
	
	/**
	 * Current All price list to 
	 * @param changedPriceList
	 */
	private void RestTempPostData(List<ShareDataM> changedPriceList){
	    RestTemplate restTemplate = new RestTemplate();
	    if(changedPriceList == null || changedPriceList.size() == 0) return;
	    Map<String, String> params = new HashMap<String, String>();
	    ShareDataM[] aryinput = new ShareDataM[changedPriceList.size()];
	    aryinput = changedPriceList.toArray(aryinput);
	    
	    try{
	    	ShareDataM[] result = restTemplate.postForObject(StaticConfig.TriggerAllRestUrl, aryinput, ShareDataM[].class, params);
			if(result != null) omtlogger.info("PriceAllTriggerTask RestTempPostData ======= result size:"+result.length);
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	}

}
