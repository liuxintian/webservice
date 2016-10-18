package com.omt.webservice;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.webservice.morningstar.entity.SmaPercent;
import com.omt.webservice.morningstar.entity.SmaResult;


public class SmaDiffChartHistoryTask extends TimerTask{
	private static Logger omtlogger = Logger.getLogger(SmaDiffChartHistoryTask.class);

	private Timer timer;
	private int interval = 24; 
	
    public SmaDiffChartHistoryTask()
    {
        timer = new Timer("SmaDiffChartHistoryTask", true);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		smaDiffChartHistoryRun();
	}
	
    public void start()
    {
		if(timer == null){
			timer = new Timer("SmaDiffChartHistoryTask",true);
		}
		//smaDiffChartHistoryRun();
		timer.scheduleAtFixedRate(this, UtilLibs.GetNextDay9Am(), interval*60*60*1000);
    }
    
	public void smaDiffChartHistoryRun(){
		// if on weekends or australia holiday, do nothing
		if(UtilLibs.isWeekend(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)) ||
		   UtilLibs.isAuHoliday(UtilLibs.GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC))){
			return;
		}
		StaticMongoTemplate.getStaticMongoTemplate().dropCollection(SmaPercent.class);
		try{
			omtlogger.info("smaChartHistoryRun start ...");
				List<MsChartHistory> retlist = StaticMongoTemplate.getStaticMongoTemplate().findAll(MsChartHistory.class);
				SmaPercent smapercent = new SmaPercent();
				omtlogger.info("smadiff----retlist.size:"+retlist.size());
				int count = 0;
				for(MsChartHistory uvo: retlist){
					smapercent = new SmaPercent();
					smapercent.setCode(uvo.getCode());
					smapercent.setMarket(uvo.getMarket());
					SmaResult today = new SmaResult();
					MsSharePrice message = findOneMS(uvo); 
		    		if(message != null){
						count ++;
		    			// If and only if there have today's share price data
		    			if(message.getDatetime() != null && (UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_UTC).equals(message.getDatetime().substring(0, 10)))){
							today.setClose(message.getData().getClose());
							today.setOpen(message.getData().getOpen());
							today.setLow(message.getData().getLow());
							today.setHigh(message.getData().getHigh());
							today.setVolume(message.getData().getVolume() == null? null:message.getData().getVolume().doubleValue());
				    		for(String type: Constants.CHART_REQ_TYPE_LIST){
								smapercent.setType(type);
								smapercent.setValue(UtilLibs.createSMADiffForType(uvo.getValue(), type, today));
								StaticMongoTemplate.getStaticMongoTemplate().insert(smapercent);
							}
		    			}else{
		    				omtlogger.info("smadiff----code:"+uvo.getCode() +" has no today's ChartHistory!");
		    			}
		    		}else{
						omtlogger.info("smadiff----code:"+uvo.getCode() +" is not in MsChartHistory!");
		    		}
				}
				omtlogger.info("smadiff----count:"+count);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		omtlogger.info("smaChartHistoryRun ...End");
	}
	
	private static MsSharePrice findOneMS(MsChartHistory entity) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(entity.getCode()));
		query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		return (MsSharePrice) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, MsSharePrice.class);   
	}	
    public void stop()
    {
    	if(timer != null){ 
    		timer.cancel();
    		timer = null;
    	}
    }
}
