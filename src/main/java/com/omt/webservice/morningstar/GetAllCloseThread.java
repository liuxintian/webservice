package com.omt.webservice.morningstar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.entity.Instrument;
import com.omt.websocket.entity.light.LightLastClose;

/**
 * Get all instruments' close price and store into database
 * @author tonyliu
 *
 */
public class GetAllCloseThread extends TimerTask{
	private Timer timer;
	private static Logger omtlogger = Logger.getLogger(GetAllCloseThread.class);

	public GetAllCloseThread() {
    	timer = new Timer("MSRequestThread", true);
    }

    public void start()
    {
    	omtlogger.info("Starting GetAllCloseThread...");
		if(timer == null){
			timer = new Timer("GetAllCloseThread",true);
		}
		//timer.schedule(this, 10000, 24*60*60*1000);
		timer.scheduleAtFixedRate(this, UtilLibs.GetNextDay6Am(), 24*60*60*1000);
    }
    
    @Override
	public void run() {
		// TODO Auto-generated method stub
    	omtlogger.info("run updateOnedayClose...");
    	updateOnedayClose();
    }
    
    /**
     * Run the update task;
     */
    public static void updateOnedayClose(){
		if(UtilLibs.isWeekend(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)) ||
				   UtilLibs.isAuHoliday(UtilLibs.GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC))){
			omtlogger.info("updateOnedayClose returned as is weekend or holiday.");
			return;
		}
		int day = -1; // Tuesday -- Friday
		if(UtilLibs.isMonday(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC))){
			omtlogger.info("updateOnedayClose getting last Friday on Monday.");
			day = -3; // Monday
		}
		
		String endDate = UtilLibs.GetCurrentTimeWithTMZ(Constants.MS_DT_FMT, Constants.SYS_TZ_VIC);
		String stDate = UtilLibs.GetPreferDay(day, Constants.MS_DT_FMT, Constants.SYS_TZ_VIC);
		
 	omtlogger.info("Getting all close price ...");
 	deleteExpireClose();
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("sectype").ne("2"));
			List<Instrument> histlist = StaticMongoTemplate.getStaticMongoTemplate().find(query, Instrument.class);
			if(histlist != null){
		    	omtlogger.info("End Getting all close price ... with total:"+ histlist.size());
				List<Instrument> tmplist = new ArrayList<Instrument>();
			 	int count = 0;
		    	for(Instrument instrument: histlist){
		    		tmplist.add(instrument);
					count ++;
					if((count % 10) == 0){
				    	MsUtility.getOneDayChartHistory(tmplist,stDate,endDate);
				    	Thread.sleep(StaticConfig.TIME_INTERVAL_REQ);
				    	tmplist = new ArrayList<Instrument>();
					}
					if(count == histlist.size()){
				    	MsUtility.getOneDayChartHistory(tmplist,stDate,endDate);
				    	Thread.sleep(StaticConfig.TIME_INTERVAL_REQ);
				    	tmplist = new ArrayList<Instrument>();
					}
		    	}
			}
			int total = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), LightLastClose.class);
	    	omtlogger.info("End Getting all close price ... with result:"+ total);

		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
    
	public static void deleteExpireClose(){
		StaticMongoTemplate.getStaticMongoTemplate().dropCollection(LightLastClose.class);
	}
    
    public void stop()
    {
    	omtlogger.info("Stopping GetAllCloseThread...");
    	if(timer != null) {
    		timer.cancel();
    		timer = null;
    	}
    }
}
