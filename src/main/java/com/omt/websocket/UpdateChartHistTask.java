package com.omt.websocket;


import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.websocket.entity.CmpChartHistory;
import com.omt.websocket.entity.HistoryPriceVO;
import com.omt.websocket.entity.NotifyMessage;
import com.omt.websocket.entity.ShareData;


public class UpdateChartHistTask extends TimerTask{
	private static Logger omtlogger = Logger.getLogger(UpdateChartHistTask.class);

	private Timer timer;
	private int delay;
	private int interval = 2; 
	
    public UpdateChartHistTask(int delay)
    {
    	this.delay = delay;
        timer = new Timer("UpdateChartHistTask", true);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		omtlogger.info("updateChartHistorytask start scanAndProcess...");
		sendChartHistoryReq();
		omtlogger.info("updateChartHistorytask start scanAndProcess...End");
	}
	
    public void start()
    {
		if(timer == null){
			timer = new Timer("UpdateChartHistTask",true);
		}
        timer.schedule(this, delay, interval*60*60*1000);
        //timer.scheduleAtFixedRate(this, UtilLibs.GetNextDay5Am(), interval*60*60*1000);
    }
    
	public static void sendChartHistoryReq(){
		// if on weekends or australia holiday, do nothing
		if(UtilLibs.isWeekend(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)) ||
		   UtilLibs.isAuHoliday(UtilLibs.GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC))){
			return;
		}
		
		if(WebSocketClient.websocket == null) {
			omtlogger.fatal("sendChartHistoryReq met fatal error: websocket is null.");
			WebSocketClient.positivelyReconnecct("sendChartHistoryReq met error: websocket is null.");
			return;
		}
		
		// Delete expired data[standard australia time]
		deleteExpireHist();
		
		try{
			omtlogger.info("sendChartHistoryReq start ...");

			 int count = 0;
	   		 for(final String company: WebSocketClient.COMPANIES){
	    		 JSONObject obj = new JSONObject(company);
	    		 JSONObject objdata = obj.getJSONObject("Data");
	    		 
	    		 if(findRightOneChartHistory(objdata.getString("Code"), objdata.getString("Market")) == null){
	    			 if(WebSocketClient.websocket == null || WebSocketClient.closedByOwn) {
	    				 break;
	    			 }
		    		 WebSocketClient.websocket.sendText(WebSocketClient.onHistorysendString(objdata.getString("Code"), 365, objdata.getString("Market")));
		    		 count ++;
		    		 if((count % 50) == 0){
		    			 Thread.sleep(5000);
		    		 }
	    		 }
			 }
	   		omtlogger.info("sendChartHistoryReq ...total count:"+ count);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		omtlogger.info("sendChartHistoryReq ...End");
	}	
	
    // pending new history price into chart history
	public void pendingCharHistory(){
		// 1. diff current date with charthistory date, if equal->skip, if not equal->
		// 2. timeof australia afternoon 8pm, start update current share price into charthistory
		// 3. append to charthistory data: "Low": 100.45,"Open": 100.5,"Close": 100.699,"Date": "2016-04-28Z","Volume": 7853,"High": 101.15
		Hashtable<String, CmpChartHistory> newSharePriceTable = findSharePriceAllNew();
		Hashtable<String, CmpChartHistory> chartHistoryTable = findAllChartHistory();
		
		if(newSharePriceTable != null && newSharePriceTable.size() > 0){
			Set<String> keys = newSharePriceTable.keySet();
			Iterator<String> itr = keys.iterator();
			CmpChartHistory uvoPrice = new CmpChartHistory();
			CmpChartHistory uvoChart = new CmpChartHistory();
			while (itr.hasNext()) { 
				String key = itr.next();
				uvoPrice = newSharePriceTable.get(key);
				uvoChart = chartHistoryTable.get(key);
				if(uvoPrice == null || uvoChart == null){
					continue;
				}
				{
					updateChartHistory(key, uvoPrice.getSharedata(), uvoChart.getValue());
				}
			 } 
		}
	}
	
	public static void deleteExpireHist(){
    	
		Query query = new Query();
		query.addCriteria(Criteria.where("date").ne(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)));
		
		StaticMongoTemplate.getStaticMongoTemplate().remove(query, HistoryPriceVO.class);
	}
	
	/**
	 * key: "code,date", value:"data"
	 * data: arylist, use map.put to avoid same record of a day.
	 * @return
	 */
	private Hashtable<String, CmpChartHistory> findSharePriceAllNew(){
		Hashtable<String, CmpChartHistory> currentHisPriceHt = new Hashtable<String, CmpChartHistory>();
		List<NotifyMessage> result = StaticMongoTemplate.getStaticMongoTemplate().find(new Query(), NotifyMessage.class);
		if(result != null && result.size() > 0){
			CmpChartHistory uvo = new CmpChartHistory();
			for(NotifyMessage msg : result){
				uvo = new CmpChartHistory();
				uvo.setCode(msg.getName());
				uvo.setSharedata(msg.getData());
				if(msg.getDatetime() != null && msg.getDatetime().length() > 10){
					uvo.setDate(msg.getDatetime().substring(0, 10));
				}
				
				currentHisPriceHt.put(msg.getName(), uvo); 
			}
		}
		return currentHisPriceHt;
	}
	
	private Hashtable<String, CmpChartHistory> findAllChartHistory(){
		Hashtable<String, CmpChartHistory> currentHisPriceHt = new Hashtable<String, CmpChartHistory>();
		List<HistoryPriceVO> result = StaticMongoTemplate.getStaticMongoTemplate().find(new Query(), HistoryPriceVO.class);
		if(result != null && result.size() > 0){
			CmpChartHistory uvo = new CmpChartHistory();
			for(HistoryPriceVO msg : result){
				uvo = new CmpChartHistory();
				uvo.setCode(msg.getCode());
				uvo.setDate(msg.getDate());
				uvo.setValue(msg.getValue());
				
				currentHisPriceHt.put(msg.getCode(), uvo); 
			}
		}
		return currentHisPriceHt;
	}
	
	private void updateChartHistory(String code, ShareData value, String newprice){
		omtlogger.info("updateChartHistorytask ... start update with code:"+code);
	   	Update update = new Update();
	   	String date = UtilLibs.GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC);
	   	String updatevalue  =  combineValue(newprice,  value, date);
	   	update.set("value", updatevalue);
	   	update.set("date", date);
	   	JSONArray dmgary = new JSONArray(updatevalue);
	   	update.set("count", dmgary.length());
			
	   	StaticMongoTemplate.getStaticMongoTemplate().updateFirst(new Query(Criteria.where("code").is(code)), update, HistoryPriceVO.class);
	}
	
	
	private static HistoryPriceVO findRightOneChartHistory(String code, String market) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("code").is(code));
		 query.addCriteria(Criteria.where("market").is(market));
		 return (HistoryPriceVO) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, HistoryPriceVO.class);		
	}
	
	private String combineValue(String value, ShareData newprice, String date){
		StringBuffer ret = new StringBuffer();
		StringBuffer str = new StringBuffer();
		str.append("{\"Low\":").append(newprice.getLow()).append(",\"Open\":").append(newprice.getOpen()).append(",\"Close\":").append(newprice.getClose()).append(",\"Date\":").append("\"").append(date).append("Z\",\"Volume\":").append(newprice.getVolume()).append(",\"High\":").append(newprice.getHigh()).append("}");
		ret.append(value.substring(0, value.length()-1)).append(",").append(str.toString()).append("]");
		return ret.toString();
	}
	
    public void stop()
    {
    	if(timer != null) timer.cancel();
    }
}
