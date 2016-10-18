package com.omt.webservice.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.omt.asxdata.controller.AsxGetTimerTask;
import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.LoadCompaniesCSV;
import com.omt.webservice.PriceAllTriggerTask;
import com.omt.webservice.PriceTriggerTask;
import com.omt.webservice.SmaChartHistoryTask;
import com.omt.webservice.SmaDiffChartHistoryTask;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.WestpacNewsTask;
import com.omt.webservice.morningstar.DaemonTask;
import com.omt.webservice.morningstar.GetAllCloseThread;
import com.omt.webservice.morningstar.MsSymbolSynThread;
import com.omt.webservice.morningstar.UpdateMSChartHistTask;
import com.omt.websocket.MessageQueueThread;
import com.omt.websocket.UpdateChartHistTask;
import com.omt.websocket.WebSocketClient;

/**
 * Desc: System initialize
 * Author: TonyLiu
 */
 
public class InitSys implements ServletContextListener{
	
	public static final String companyfile = "companies.csv";
	public static final String msInstrument = "AustraliaStockExchange.txt";
	
	private AsxGetTimerTask asxGetTimerTask;
	private WestpacNewsTask westpacNewsTask;
	private static PriceTriggerTask priceTriggerTask;
	private static PriceAllTriggerTask priceAllTriggerTask;

	private static UpdateChartHistTask updateChartHistorytask;
	private static MessageQueueThread messageQueueThread;
	
	private static DaemonTask daemonTask;
	private static UpdateMSChartHistTask updateMSChartHistorytask;
	private static MsSymbolSynThread msSymbolSynThread;
	
	private static SmaDiffChartHistoryTask smaDiffChartHistoryTask;
	private static SmaChartHistoryTask smaChartHistoryTask;
	
	private static GetAllCloseThread getAllCloseThread;
	
	private static Logger logger = Logger.getLogger(InitSys.class.getName());
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("ServletContextListener contextDestroyed... start");
		// release All timerTasks;
		if(updateChartHistorytask != null) updateChartHistorytask.stop();
		if(asxGetTimerTask != null) asxGetTimerTask.stop(); 
		if(westpacNewsTask != null) westpacNewsTask.stop(); 
		if(priceTriggerTask != null) priceTriggerTask.stop();
		if(priceAllTriggerTask != null) priceAllTriggerTask.stop();
		if(messageQueueThread != null) messageQueueThread.stop();
		if(daemonTask != null) daemonTask.stop();	
		if(updateMSChartHistorytask != null) updateMSChartHistorytask.stop();	
		if(smaDiffChartHistoryTask != null) smaDiffChartHistoryTask.stop();	
		if(smaChartHistoryTask != null) smaChartHistoryTask.stop();	
		if(getAllCloseThread != null) getAllCloseThread.stop();	

		// close database connection
		StaticMongoTemplate.releaseMongoDBTemplate();
		//--------------------------------------------------------------
		System.gc();
		do {
			try { // https://jira.mongodb.org/browse/JAVA-400
				Thread.sleep(5000);
				break;
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		} while (true);
     
		java.beans.Introspector.flushCaches();
		logger.info("ServletContextListener contextDestroyed... end");
		//--------------------------------------------------------------
	}
	
	public void contextInitialized(ServletContextEvent context) {
		// TODO Auto-generated method stub
		logger.info("Start contextInitializing...");
		
		StaticConfig.filesBasePath = context.getServletContext().getRealPath(StaticConfig.filesBasePath);
		// T1 initialize system
		initSys();
		
		// T2 initialize Data Source for Morning Star
		switchDS();
		
		logger.info("initSys contextInitialized.");
	}
	// initialize configuration or parameters here 
	public void initSys(){
		
		// S1 initialize email configurations
		StaticConfig.initSysCfgDb();
		
		// S1.1 load companies file if there is one
		WebSocketClient.loadTickerCodeList(true);

		// S2 Get asx data from url
		asxGetTimerTask = new AsxGetTimerTask(Constants.START_THREE);
		asxGetTimerTask.start();
		
		// S3 Run task for getting westpac news-history
		westpacNewsTask = new WestpacNewsTask(Constants.START_FOUR);
		westpacNewsTask.start();
		
		if(getClass().getClassLoader().getResource(companyfile) != null){
			String fullpath = getClass().getClassLoader().getResource(companyfile).getPath();
			LoadCompaniesCSV.loadCSVCompaniesTmp(fullpath.toString());
		}else{
			logger.error("--can not load the file:"+companyfile+", please check the logs.");
		}

		if(getClass().getClassLoader().getResource(msInstrument) != null){
			String fullpath = getClass().getClassLoader().getResource(msInstrument).getPath();
			LoadCompaniesCSV.loadInstrumentForMS(fullpath.toString());
		}else{
			logger.error("--can not load the file:"+msInstrument+", please check the logs.");
		}		
		
		if(smaChartHistoryTask == null){
			smaChartHistoryTask = new SmaChartHistoryTask();
			smaChartHistoryTask.start();
		}
		if(smaDiffChartHistoryTask == null){
			smaDiffChartHistoryTask = new SmaDiffChartHistoryTask();
			smaDiffChartHistoryTask.start();
		}
	}
	
	private static void initMorningStar(){
		
		//M1 Running task to check current time, then make the decision.
		//MsUtility.createFieldList();
		//M2 run dealing message task
		if(daemonTask == null){
			daemonTask = new DaemonTask();
			daemonTask.start();
		}

		//M3. running the delete overdue chart history data 
		if(updateMSChartHistorytask == null){
			updateMSChartHistorytask = new UpdateMSChartHistTask(Constants.START_TWO);
			updateMSChartHistorytask.start();
		}
		
		if(msSymbolSynThread == null){
			msSymbolSynThread = new MsSymbolSynThread();
			msSymbolSynThread.start();
		}

		if(getAllCloseThread == null){
			getAllCloseThread = new GetAllCloseThread();
			getAllCloseThread.start();
		}
	}

	public static void switchDS(){
		logger.info("start switchDS ... with StaticConfig.datasource(PR:1, MS:2):" + StaticConfig.datasource);
		initMorningStar();
		switchTrigger();
		switchAllTrigger();
	}
	
	/**
	 * Current Price Trigger
	 */
	public static void switchTrigger(){
		triggerClose();
		triggerStart();
	}
	
	private static void triggerStart(){
		if(priceTriggerTask == null){
			priceTriggerTask = new PriceTriggerTask(Constants.START_FIVE);
			priceTriggerTask.start();
		}
	}
	private static void triggerClose(){
		if(priceTriggerTask != null){
			priceTriggerTask.stop();;
			priceTriggerTask = null;
		}
	}

	/**
	 * Share Price Trigger
	 */
	public static void switchAllTrigger(){
		triggerAllClose();
		triggerAllStart();
	}
	
	private static void triggerAllStart(){
		if(priceAllTriggerTask == null){
			priceAllTriggerTask = new PriceAllTriggerTask(Constants.START_ONE);
			priceAllTriggerTask.start();
		}
	}
	private static void triggerAllClose(){
		if(priceAllTriggerTask != null){
			priceAllTriggerTask.stop();
			priceAllTriggerTask = null;
		}
	}
	
	/**
	 db.companyList.drop()
	 db.sysConfigVO.drop()
	 db.userStatus.drop()
	 db.notifyMessage.drop()
	 db.notifyMessageHistory.drop()
	 db.historyPriceVO.drop()
	 */
}