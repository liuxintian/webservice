package com.omt.webservice;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.webservice.morningstar.entity.SmaHistory;
import com.omt.websocket.entity.HistoryPriceVO;


public class SmaChartHistoryTask extends TimerTask{
	private static Logger omtlogger = Logger.getLogger(SmaChartHistoryTask.class);

	private Timer timer;
	private int interval = 24; 
	
    public SmaChartHistoryTask()
    {
        timer = new Timer("SmaChartHistoryTask", true);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		smaChartHistoryRun();
	}
	
    public void start()
    {
		if(timer == null){
			timer = new Timer("SmaChartHistoryTask",true);
		}
		//smaChartHistoryRun();
		timer.scheduleAtFixedRate(this, UtilLibs.GetNextDay6Am(), interval*60*60*1000);
    }
    
	public void smaChartHistoryRun(){
		// if on weekends or australia holiday, do nothing
		if(UtilLibs.isWeekend(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC)) ||
		   UtilLibs.isAuHoliday(UtilLibs.GetCurrentTimeWithTMZ(Constants.DATE_DT_FMT, Constants.SYS_TZ_VIC))){
			return;
		}
		StaticMongoTemplate.getStaticMongoTemplate().dropCollection(SmaHistory.class);
		try{
			omtlogger.info("smaChartHistoryRun start ...");
			if(StaticConfig.datasource == StaticConfig.DATA_SOURCE_PARITECH){
				List<HistoryPriceVO> retlist = StaticMongoTemplate.getStaticMongoTemplate().findAll(HistoryPriceVO.class);
				SmaHistory smahistory = new SmaHistory();
				for(HistoryPriceVO uvo: retlist){
					smahistory = new SmaHistory();
					smahistory.setCode(uvo.getCode());
					smahistory.setMarket(uvo.getMarket());
					for(String type: Constants.CHART_REQ_TYPE_LIST){
						smahistory.setType(type);
						smahistory.setValue(UtilLibs.createSMAForType(uvo.getValue(), type));
						StaticMongoTemplate.getStaticMongoTemplate().insert(smahistory);
					}
				}
			}else{
				List<MsChartHistory> retlist = StaticMongoTemplate.getStaticMongoTemplate().findAll(MsChartHistory.class);
				SmaHistory smahistory = new SmaHistory();
				omtlogger.info("sma----retlist.size:"+retlist.size());
				int count = 0;
				for(MsChartHistory uvo: retlist){
					count ++;
					smahistory = new SmaHistory();
					smahistory.setCode(uvo.getCode());
					smahistory.setMarket(uvo.getMarket());
					for(String type: Constants.CHART_REQ_TYPE_LIST){
						smahistory.setType(type);
						smahistory.setValue(UtilLibs.createSMAForType(uvo.getValue(), type));
						StaticMongoTemplate.getStaticMongoTemplate().insert(smahistory);
					}
				}
				omtlogger.info("sma----count:"+count);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		omtlogger.info("smaChartHistoryRun ...End");
	}	
	
    public void stop()
    {
    	if(timer != null){ 
    		timer.cancel();
    		timer = null;
    	}
    }
}
