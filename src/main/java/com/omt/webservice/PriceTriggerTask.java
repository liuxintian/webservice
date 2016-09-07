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
import com.omt.webservice.entity.HashMessage;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.webservice.morningstar.entity.MsSharePriceOld;
import com.omt.webservice.morningstar.entity.ShareDataM;
import com.omt.websocket.entity.NotifyMessage;
import com.omt.websocket.entity.NotifyMessageHistory;
import com.omt.websocket.entity.ShareData;


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
		Hashtable<String, String> oldHisPriceHt = findAllOld();
		Hashtable<String, String> newHisPriceHt = findAllNew();
		List<HashMessage> changedPriceList = new ArrayList<HashMessage>();
		
		if(oldHisPriceHt != null && oldHisPriceHt.size() > 0 && newHisPriceHt != null && newHisPriceHt.size() > 0){
			Set<String> keys = newHisPriceHt.keySet();
			Iterator<String> itr = keys.iterator();
			while (itr.hasNext()) { 
				String key = itr.next();
				String newValue = newHisPriceHt.get(key);
				String oldValue = oldHisPriceHt.get(key);
				if(newValue == null || oldValue == null || newValue.equals("null") || oldValue.equals("null")) {
					continue;
				}
				if(!oldValue.equals(newValue)){
					HashMessage uvo = new HashMessage();
					uvo.setCode(key);
					uvo.setValue(newValue);
					//uvo.setValue(String.valueOf(Double.parseDouble(newBestAsk) - Double.parseDouble(oldBestAsk)));
					changedPriceList.add(uvo);
				}
			} 
		}
		
		// 5. send restful request
		if(changedPriceList != null){
			omtlogger.info("PriceTriggerTask...start with changedPriceList.size():"+changedPriceList.size());
			RestTempPostData(changedPriceList);
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
	private Hashtable<String, String> findAllNew(){
		Hashtable<String, String> currentHisPriceHt = new Hashtable<String, String>();
		
		if(StaticConfig.datasource == StaticConfig.DATA_SOURCE_MORNINGSTAR){
			List<MsSharePrice> result = StaticMongoTemplate.getStaticMongoTemplate().findAll(MsSharePrice.class);
			if(result != null && result.size() > 0){
				for(MsSharePrice msg : result){
					currentHisPriceHt.put(msg.getName(), MSGetCalculateValue(msg.getData())); 
				}
			}
		}else{
			List<NotifyMessage> result = StaticMongoTemplate.getStaticMongoTemplate().findAll(NotifyMessage.class);
			if(result != null && result.size() > 0){
				for(NotifyMessage msg : result){
					currentHisPriceHt.put(msg.getName(), PRGetCalculateValue(msg.getData())); 
				}
			}
		}
			
		return currentHisPriceHt;
	}
	
	private Hashtable<String, String> findAllOld(){
		Hashtable<String, String> currentHisPriceHt = new Hashtable<String, String>();
		
		if(StaticConfig.datasource == StaticConfig.DATA_SOURCE_MORNINGSTAR){
			Query query = new Query();
			query.addCriteria(Criteria.where("datetime").ne(""));
			List<MsSharePriceOld> result = StaticMongoTemplate.getStaticMongoTemplate().find(query, MsSharePriceOld.class);
			if(result != null && result.size() > 0){
				for(MsSharePriceOld msg : result){
					currentHisPriceHt.put(msg.getName(), MSGetCalculateValue(msg.getData())); 
				}
				
		    	Update update = new Update();
				update.set("datetime", "");
				StaticMongoTemplate.getStaticMongoTemplate().updateMulti(query, update, MsSharePriceOld.class);
			}
		}else{
			List<NotifyMessageHistory> result = StaticMongoTemplate.getStaticMongoTemplate().findAll(NotifyMessageHistory.class);
			if(result != null && result.size() > 0){
				for(NotifyMessageHistory msg : result){
					currentHisPriceHt.put(msg.getName(), PRGetCalculateValue(msg.getData())); 
				}
			}
		}

		return currentHisPriceHt;
	}
	
	/**
	 * Get the value for calculating
	 * @param data
	 * 1:Last, 2:BestBid, 3:BestAsk, 4:Open, 5:Close, 6:High, 7:Low
	 * @return
	 */
	private String MSGetCalculateValue(ShareDataM data){
		String ret = "";
		switch (StaticConfig.TIGGER_FIELD_DEFAUTL){
		case 1:
			ret = String.valueOf(data.getLast());
			break;
		case 2:
			ret = String.valueOf(data.getBestBid());
			break;
		case 3:
			ret = String.valueOf(data.getBestAsk());
			break;
		case 4:
			ret = String.valueOf(data.getOpen());
			break;
		case 5:
			ret = String.valueOf(data.getClose());
			break;
		case 6:
			ret = String.valueOf(data.getHigh());
			break;
		case 7:
			ret = String.valueOf(data.getLow());
			break;
		default:
			ret = String.valueOf(data.getLast());
			break;
		}
		return ret;
	}
	
	/**
	 * Get the value for calculating
	 * @param data
	 * 1:Last, 2:BestBid, 3:BestAsk, 4:Open, 5:Close, 6:High, 7:Low
	 * @return
	 */
	private String PRGetCalculateValue(ShareData data){
		String ret = "";
		switch (StaticConfig.TIGGER_FIELD_DEFAUTL){
		case 1:
			ret = String.valueOf(data.getLast());
			break;
		case 2:
			ret = String.valueOf(data.getBestBid());
			break;
		case 3:
			ret = String.valueOf(data.getBestAsk());
			break;
		case 4:
			ret = String.valueOf(data.getOpen());
			break;
		case 5:
			ret = String.valueOf(data.getClose());
			break;
		case 6:
			ret = String.valueOf(data.getHigh());
			break;
		case 7:
			ret = String.valueOf(data.getLow());
			break;
		default:
			ret = String.valueOf(data.getLast());
			break;
		}
		return ret;
	}
}
