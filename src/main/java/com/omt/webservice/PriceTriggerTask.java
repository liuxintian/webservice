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
import com.omt.webservice.entity.HashMessage;
import com.omt.webservice.morningstar.entity.MsSharePrice;


public class PriceTriggerTask extends TimerTask{
	private Logger omtlogger = Logger.getLogger(PriceTriggerTask.class);

	private Timer timer;
	private int delay;
	
    public PriceTriggerTask(int delay)
    {
        timer = new Timer("PriceTriggerTask",true);
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
			timer = new Timer("PriceTriggerTask",true);
		}
		timer.schedule(this, delay, StaticConfig.herokuCallInterval*1000);
    }
    
    public void stop()
    {
    	if(timer != null)  {
    		timer.cancel();
    		timer = null;
    	}
    }
    
	private void scanAndProcess(){
		omtlogger.info("PriceTriggerTask start...");
		// 1. get old data 2. get new data 3. compare the two 4. generate the changed list
		try{
			List<HashMessage> changedPriceList = new ArrayList<HashMessage>();
			Query query = new Query();
			query.addCriteria(Criteria.where("dataChangedLast").is(true));
			List<MsSharePrice> sharePriceList = StaticMongoTemplate.getStaticMongoTemplate().find(query, MsSharePrice.class);
			
			if(sharePriceList != null){
				for (MsSharePrice msp: sharePriceList){
					HashMessage uvo = new HashMessage();
					uvo.setCode(msp.getData().getCode());
					uvo.setValue(String.valueOf(msp.getData().getLast()));
					changedPriceList.add(uvo);
				}
			} 
			
			// 5. send restful request
			if(changedPriceList != null){
				omtlogger.info("PriceTriggerTask...start with changedPriceList.size():"+changedPriceList.size());
				RestTempPostData(changedPriceList);

				Update update = new Update();
				update.set("dataChangedLast", false);
				StaticMongoTemplate.getStaticMongoTemplate().updateMulti(query, update, MsSharePrice.class);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void RestTempPostData(List<HashMessage> changedPriceList){
	    RestTemplate restTemplate = new RestTemplate();
	    if(changedPriceList == null || changedPriceList.size() == 0) return;
	    Map<String, String> params = new HashMap<String, String>();
	    HashMessage[] aryinput = new HashMessage[changedPriceList.size()];
	    aryinput = changedPriceList.toArray(aryinput);
	    
	    try{
	    	//omtlogger.info("PriceTriggerTask RestTempPostData string:" + Arrays.toString(aryinput));
	    	HashMessage[] result = restTemplate.postForObject(StaticConfig.herokuRestUrl, aryinput, HashMessage[].class, params);
			if(result != null) omtlogger.info("PriceTriggerTask RestTempPostData ------- result size:"+result.length);
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	}	
	
}
