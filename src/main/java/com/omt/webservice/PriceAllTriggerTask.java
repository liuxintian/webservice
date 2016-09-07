package com.omt.webservice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.client.RestTemplate;

import com.omt.config.StaticConfig;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.webservice.morningstar.entity.MsSharePriceOld;
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
		Hashtable<String, ShareDataM> oldHisPriceHt = findAllOld();
		Hashtable<String, ShareDataM> newHisPriceHt = findAllNew();
		List<ShareDataM> changedPriceList = new ArrayList<ShareDataM>();
		
		if(oldHisPriceHt != null && oldHisPriceHt.size() > 0 && newHisPriceHt != null && newHisPriceHt.size() > 0){
			Set<String> keys = newHisPriceHt.keySet();
			Iterator<String> itr = keys.iterator();
			while (itr.hasNext()) { 
				String key = itr.next();
				ShareDataM newValue = newHisPriceHt.get(key);
				ShareDataM oldValue = oldHisPriceHt.get(key);
				if(newValue == null || oldValue == null) {
					continue;
				}
				if((!(oldValue.toTriggerString().equals(newValue.toTriggerString())))){
					changedPriceList.add(newValue);
				}
			} 
		}
		
		// 5. send restful request
		if(changedPriceList != null){
			omtlogger.info("PriceAllTriggerTask...start with changedPriceList.size():"+changedPriceList.size());
			RestTempPostData(changedPriceList);
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
	
	private Hashtable<String, ShareDataM> findAllNew(){
		Hashtable<String, ShareDataM> currentHisPriceHt = new Hashtable<String, ShareDataM>();
		
		if(StaticConfig.datasource == StaticConfig.DATA_SOURCE_MORNINGSTAR){
			List<MsSharePrice> result = StaticMongoTemplate.getStaticMongoTemplate().findAll(MsSharePrice.class);
			if(result != null && result.size() > 0){
				for(MsSharePrice msg : result){
					currentHisPriceHt.put(msg.getName(), msg.getData()); 
				}
			}
		}
			
		return currentHisPriceHt;
	}
	
	private Hashtable<String, ShareDataM> findAllOld(){
		Hashtable<String, ShareDataM> currentHisPriceHt = new Hashtable<String, ShareDataM>();
		
		if(StaticConfig.datasource == StaticConfig.DATA_SOURCE_MORNINGSTAR){
			Query query = new Query();
			query.addCriteria(Criteria.where("datetime").ne(""));
			List<MsSharePriceOld> result = StaticMongoTemplate.getStaticMongoTemplate().find(query, MsSharePriceOld.class);
			if(result != null && result.size() > 0){
				for(MsSharePriceOld msg : result){
					currentHisPriceHt.put(msg.getName(), msg.getData()); 
				}
				
		    	Update update = new Update();
				update.set("datetime", "");
				StaticMongoTemplate.getStaticMongoTemplate().updateMulti(query, update, MsSharePriceOld.class);
			}
		}

		return currentHisPriceHt;
	}

}
