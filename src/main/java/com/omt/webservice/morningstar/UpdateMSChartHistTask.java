package com.omt.webservice.morningstar;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.websocket.WebSocketClient;
import com.omt.websocket.entity.CompanyList;


public class UpdateMSChartHistTask extends TimerTask{
	private static Logger omtlogger = Logger.getLogger(UpdateMSChartHistTask.class);

	private Timer timer;
	private int interval = 24; 
	
    public UpdateMSChartHistTask(int delay)
    {
        timer = new Timer("Morningstar UpdateChartHistTask", true);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		omtlogger.info("Morningstar updateChartHistorytask start scanAndProcess...");
		sendChartHistoryReq();
		omtlogger.info("Morningstar updateChartHistorytask start scanAndProcess...End");
	}
	
    public void start()
    {
		if(timer == null){
			timer = new Timer("Morningstar UpdateChartHistTask",true);
		}
		timer.scheduleAtFixedRate(this, UtilLibs.GetNextDay5Am(), interval*60*60*1000);
    }
    
	public void sendChartHistoryReq(){
		// if on weekends or australia holiday, do nothing
		if(UtilLibs.isWeekend(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)) ||
		   UtilLibs.isAuHoliday(UtilLibs.GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC))){
			return;
		}

		// Delete expired data[standard australia time]
		deleteExpireHist();
		
		try{
			omtlogger.info("Morningstar sendChartHistoryReq start ...");
			String endDate = UtilLibs.GetCurrentTimeWithTMZ(Constants.MS_DT_FMT, Constants.SYS_TZ_VIC);
			String stDate = UtilLibs.GetPreferDay(StaticConfig.CHART_HISTORY_DEFAULT, Constants.MS_DT_FMT, Constants.SYS_TZ_VIC);//default one year request.

			int count = 0;
			Set<CompanyList> tmplist = new HashSet<CompanyList>();
			for (Map.Entry<String, CompanyList> entry : WebSocketClient.CODE_COMPANYLIST.entrySet()){
				if(timer == null) break;
				if(findRightOneChartHistory(entry.getValue()) == null){
					count ++;
					tmplist.add(entry.getValue());
					// Up to 10 instruments can now be requested in a single 90 Day T&S request
					if((count % 10) == 0){
						MsUtility.getChartHistory(tmplist, stDate, endDate);
						Thread.sleep(StaticConfig.TIME_INTERVAL_REQ);
						tmplist = new HashSet<CompanyList>();
					}
					if(count == WebSocketClient.CODE_COMPANYLIST.size()){
						MsUtility.getChartHistory(tmplist, stDate, endDate);
						Thread.sleep(StaticConfig.TIME_INTERVAL_REQ);
						tmplist = new HashSet<CompanyList>();
					}
				}
			}
			MsUtility.getChartHistory(tmplist, stDate, endDate);
			omtlogger.info("Morningstar sendChartHistoryReq ...total count:"+ count);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		omtlogger.info("Morningstar sendChartHistoryReq ...End");
	}	
	
  	
	public static void deleteExpireHist(){
    	
		Query query = new Query();
		query.addCriteria(Criteria.where("date").ne(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)));
		StaticMongoTemplate.getStaticMongoTemplate().remove(query, MsChartHistory.class);
	}
	
	private static MsChartHistory findRightOneChartHistory(CompanyList company) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.addCriteria(Criteria.where("code").is(company.getCode()));
		 query.addCriteria(Criteria.where("market").is(company.getMarket()));
		 return (MsChartHistory) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, MsChartHistory.class);		
	}
	
    public void stop()
    {
    	if(timer != null){ 
    		timer.cancel();
    		timer = null;
    	}
    }
}
