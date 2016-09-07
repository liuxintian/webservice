package com.omt.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.omt.config.entity.SysConfigVO;
import com.omt.webservice.StaticMongoTemplate;

public class StaticConfig {
	public static String filesBasePath = "omtfiles";
	public static String newslistfile = "westpac_newshistory.csv";
	// data source switcher
	public static int logswitcher = 2;
	public static int datasource = 2;
	public static final int DATA_SOURCE_PARITECH = 1;
	public static final int DATA_SOURCE_MORNINGSTAR = 2;
	
	// mail cfg start
	public static final String MAIL_SWITCHER_ON = "1";
	public static final String MAIL_SWITCHER_OFF = "0";
	public static String mailSwitcher = "1"; // 0:off--not send email, 1:on--send email
	
	public static String mailUsername = "admin@omnimarkettide.com";
	public static String mailPassword = "MSqHOt5bxJh5656wqPbR4A";

	public static String mailAlias = "virtualagm@omnimarkettide.com";
	public static String mailTitle = "Omni Market Tide Ltd";
	
	public static String mailSubject = "OMT 2016 AGM";
	public static String mailBody = "Thanks for lodging your vote via OmniLOOP for Omni Market Tide Ltd's 2016 Annual General Meeting. \n\nYour vote has been received and recorded.\n\nYour voting reference number is: [NUMBER] \n\nKind regards\n\nOmni Market Tide Ltd";
	public static String mailBodyReplacement = "[NUMBER]";
	
	public static String mailHost = "smtp.mandrillapp.com";
	public static String mailPort = "587";
	public static String mailTags = "OMTVEC";
	// mail cfg end
	
	// heroku cfg start
	public static String herokuRestUrl = "http://omt-custom.herokuapp.com:80/api/pricetracker";
	public static int herokuCallInterval = 60; //Time interval to send trigger event. unit:second

	public static String TriggerAllRestUrl = "http://omt-custom.herokuapp.com:80/api/pricetracker";
	public static int TriggerAllCallInterval = 180; //Time interval to send trigger event. unit:second
	// heroku cfg end

	// westpac news cfg start
	public static String westpacNewsUrl = "http://ir.iguana2.com/westpac/news-history";
	public static String westpacNewsContainer = "table";
	public static int westpacNewsContainerIndex = 2;
	// westpac news cfg end
	
	// asx cfg start
	public static String asxGainsUrl = "http://www.asx.com.au/asx/widget/topGains.do";
	public static String asxDeclinesUrl = "http://www.asx.com.au/asx/widget/topDeclines.do";
	public static String asxPageContainer = "top5-data";
	// asc cfg end
	
	
	// paritech cfg start
	public static String paritechTokenUrl = "https://api.paritech.com/Paritech.AuthServer/OAuth2/Token";
	public static String paritechWssUrl = "wss://wsapi2.paritech.com/Zenith";
	public static String paritechWssProtocol = "ZenithJson";
	public static String paritechWssScope = "http://api.paritech.com/wsapi/market/asset http://api.paritech.com/wsapi/market/depthfull http://api.paritech.com/wsapi/market/depthshort http://api.paritech.com/wsapi/market/trades http://api.paritech.com/wsapi/market/news http://api.paritech.com/wsapi/trading/accounts http://api.paritech.com/wsapi/trading/balances http://api.paritech.com/wsapi/trading/holdings http://api.paritech.com/wsapi/trading/orders http://api.paritech.com/wsapi/trading/transactions http://api.paritech.com/wsapi/market/symbols";
	
	public static String wssConnUserName = "OMNILOOP";
	public static String wssConnPassword = "qoz-why80";
	public static String wssConnGrantType = "password";
	public static String wssConnGrantRefresh = "refresh_token";
	
	public static String wssClientId = "omniloop";
	public static String wssClientPwd = "nm27o9";
	
	public static int wssTokenInterval = 19;
	// paritech cfg end
	
	// data exchanger cfg start
	public static String restCompaniesUrl = "http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/companies";
	public static String restLocalUserUrl = "http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/company-instances/{value}/managers";
	public static String restPostMsgUrl = "http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/users/{value}/stock-subscriptions";
	public static final String replaceValue = "{value}";
	
	// data exchanger cfg end
	
	// program system configuration start
	public static int wssReconnectTimes=5;  // wss client re-connect to wss server times
	public static int charthistoryGetWay=2; // charthistory get data way: 1:All 2: one year(365) 3: by count
	
	// program system configuration end
	
	public static List<SysConfigVO> initlist = new ArrayList<SysConfigVO>();
	
	//------------------------------------------------------------------------------------------------------------
	// For morning star
	public static String MS_REQUEST_URL_SP = "http://msxml.tenfore.com/index.php";
	public static String MS_REQUEST_URL_CH = "http://msxml.tenfore.com/indexTS";
	public static String MS_USERNAME = "OmOm15734";
	public static String MS_PASSWORD = "5LU5ZG";
	public static String MS_JSONTAG  = "JSONShort";
	public static String MS_TASK_24H_START = "6"; //6am
	public static String MS_TASK_24H_STOP = "18"; //18am
	public static int    MS_DATA_SWITCHER = 1;// 1: same collections  2. different collections 
	public static int    MS_REQUEST_TIMEOUT = 2; // Http Request for moringstar timeout  Second
	
	public static int TIME_INTERVAL_REQ = 500; // time interval for each request to server in millisecond.
	public static int CHART_HISTORY_DEFAULT = -398; // default days request for chart history. A little bit more than one year.
	public static int    NO_DATA_API_TIMEOUT = 5; // Response timeout for APIs requests  Second
	
	//--------------------------------------------------
	// Morning star FTP server
	public static int    MS_SYMBOL_SYNC_FLAG = 1;
	public static String MS_FTPSERVER_IP = "83.244.214.45";
	public static int    MS_FTPSERVER_PORT = 21;
	public static String MS_FTPSERVER_USERNAME = "publicftp";
	public static String MS_FTPSERVER_PASSWORD = "publicftp";
	public static String MS_REMOTE_FILE = "/Symbol Guides/146 - Australia Stock Exchange.txt";
	public static String MS_LOCAL_FILE = "AustraliaStockExchange.txt";
	//--------------------------------------------------
	public static int    TIGGER_FIELD_DEFAUTL = 1; // 1:Last, 2:BestBid, 3:BestAsk, 4:Open, 5:Close, 6:High, 7:Low
	
	public static void updateSysConfig(){
		HashMap<String,String> syscfgmap = findAll();
		
		if(syscfgmap != null){
			//datasource = Integer.parseInt(GetValidValue(syscfgmap.get("datasource"), String.valueOf(datasource)));
			logswitcher = Integer.parseInt(GetValidValue(syscfgmap.get("logswitcher"), String.valueOf(logswitcher)));
			//------------------------------------------------------------------------------------------------------------
			mailSwitcher = GetValidValue(syscfgmap.get("mailSwitcher"), mailSwitcher);

			mailUsername = GetValidValue(syscfgmap.get("mailUsername"), mailUsername);
			mailPassword = GetValidValue(syscfgmap.get("mailPassword"), mailPassword);

			mailAlias = GetValidValue(syscfgmap.get("mailAlias"), mailAlias);
			mailTitle = GetValidValue(syscfgmap.get("mailTitle"), mailTitle);
			
			mailSubject = GetValidValue(syscfgmap.get("mailSubject"), mailSubject);
			mailBody = GetValidValue(syscfgmap.get("mailBody"), mailBody);

			mailHost = GetValidValue(syscfgmap.get("mailHost"), mailHost);
			mailPort = GetValidValue(syscfgmap.get("mailPort"), mailPort);
			mailTags = GetValidValue(syscfgmap.get("mailTags"), mailTags);
			
			
			//------------------------------------------------------------------------------------------------------------
			herokuRestUrl = GetValidValue(syscfgmap.get("herokuRestUrl"), herokuRestUrl);
			herokuCallInterval = Integer.parseInt(GetValidValue(syscfgmap.get("herokuCallInterval"), String.valueOf(herokuCallInterval)));
			
			TriggerAllRestUrl = GetValidValue(syscfgmap.get("TriggerAllRestUrl"), TriggerAllRestUrl);
			TriggerAllCallInterval = Integer.parseInt(GetValidValue(syscfgmap.get("TriggerAllCallInterval"), String.valueOf(TriggerAllCallInterval)));

			//------------------------------------------------------------------------------------------------------------
			westpacNewsUrl = GetValidValue(syscfgmap.get("westpacNewsUrl"), westpacNewsUrl);
			westpacNewsContainer = GetValidValue(syscfgmap.get("westpacNewsContainer"), westpacNewsContainer);
			westpacNewsContainerIndex = Integer.parseInt(GetValidValue(syscfgmap.get("westpacNewsContainerIndex"), String.valueOf(westpacNewsContainerIndex)));
			
			//------------------------------------------------------------------------------------------------------------
			asxGainsUrl = GetValidValue(syscfgmap.get("asxGainsUrl"), asxGainsUrl);
			asxDeclinesUrl = GetValidValue(syscfgmap.get("asxDeclinesUrl"), asxDeclinesUrl);
			asxPageContainer = GetValidValue(syscfgmap.get("asxPageContainer"), asxPageContainer);
			
			//------------------------------------------------------------------------------------------------------------
			paritechTokenUrl = GetValidValue(syscfgmap.get("paritechTokenUrl"), paritechTokenUrl);
			paritechWssUrl = GetValidValue(syscfgmap.get("paritechWssUrl"), paritechWssUrl);
			paritechWssProtocol = GetValidValue(syscfgmap.get("paritechWssProtocol"), paritechWssProtocol);
			paritechWssScope = GetValidValue(syscfgmap.get("paritechWssScope"), paritechWssScope);
			
			wssConnUserName = GetValidValue(syscfgmap.get("wssConnUserName"), wssConnUserName);
			wssConnPassword = GetValidValue(syscfgmap.get("wssConnPassword"), wssConnPassword);
			wssConnGrantType = GetValidValue(syscfgmap.get("wssConnGrantType"), wssConnGrantType);
			wssConnGrantRefresh = GetValidValue(syscfgmap.get("wssConnGrantRefresh"), wssConnGrantRefresh);
			
			wssClientId = GetValidValue(syscfgmap.get("wssClientId"), wssClientId);
			wssClientPwd = GetValidValue(syscfgmap.get("wssClientPwd"), wssClientPwd);
			
			wssTokenInterval = Integer.parseInt(GetValidValue(syscfgmap.get("wssTokenInterval"), String.valueOf(wssTokenInterval)));
			//------------------------------------------------------------------------------------------------------------
			restCompaniesUrl = GetValidValue(syscfgmap.get("restCompaniesUrl"), restCompaniesUrl);
			restLocalUserUrl = GetValidValue(syscfgmap.get("restLocalUserUrl"), restLocalUserUrl);
			restPostMsgUrl = GetValidValue(syscfgmap.get("restPostMsgUrl"), restPostMsgUrl);
			
			//------------------------------------------------------------------------------------------------------------
			wssReconnectTimes = Integer.parseInt(GetValidValue(syscfgmap.get("wssReconnectTimes"), String.valueOf(wssReconnectTimes)));
			charthistoryGetWay = Integer.parseInt(GetValidValue(syscfgmap.get("charthistoryGetWay"), String.valueOf(charthistoryGetWay)));

			//------------------------------------------------------------------------------------------------------------
			MS_REQUEST_URL_SP = GetValidValue(syscfgmap.get("MS_REQUEST_URL_SP"), MS_REQUEST_URL_SP);
			MS_REQUEST_URL_CH = GetValidValue(syscfgmap.get("MS_REQUEST_URL_CH"), MS_REQUEST_URL_CH);
			MS_USERNAME = GetValidValue(syscfgmap.get("MS_USERNAME"), MS_USERNAME);
			MS_PASSWORD = GetValidValue(syscfgmap.get("MS_PASSWORD"), MS_PASSWORD);
			MS_JSONTAG  = GetValidValue(syscfgmap.get("MS_JSONTAG"), MS_JSONTAG);

			MS_TASK_24H_START = GetValidValue(syscfgmap.get("MS_TASK_24H_START"), MS_TASK_24H_START);
			MS_TASK_24H_STOP  = GetValidValue(syscfgmap.get("MS_TASK_24H_STOP"), MS_TASK_24H_STOP);
			
			MS_DATA_SWITCHER  = Integer.parseInt(GetValidValue(syscfgmap.get("MS_DATA_SWITCHER"), String.valueOf(MS_DATA_SWITCHER)));
			//------------------------------------------------------------------------------------------------------------
			TIME_INTERVAL_REQ  = Integer.parseInt(GetValidValue(syscfgmap.get("TIME_INTERVAL_REQ"), String.valueOf(TIME_INTERVAL_REQ)));
			CHART_HISTORY_DEFAULT  = Integer.parseInt(GetValidValue(syscfgmap.get("CHART_HISTORY_DEFAULT"), String.valueOf(CHART_HISTORY_DEFAULT)));
			
			MS_REQUEST_TIMEOUT  = Integer.parseInt(GetValidValue(syscfgmap.get("MS_REQUEST_TIMEOUT"), String.valueOf(MS_REQUEST_TIMEOUT)));
			NO_DATA_API_TIMEOUT  = Integer.parseInt(GetValidValue(syscfgmap.get("NO_DATA_API_TIMEOUT"), String.valueOf(NO_DATA_API_TIMEOUT)));
			
			//------------------------------------------------------------------------------------------------------------
			MS_SYMBOL_SYNC_FLAG = Integer.parseInt(GetValidValue(syscfgmap.get("MS_SYMBOL_SYNC_FLAG"), String.valueOf(MS_SYMBOL_SYNC_FLAG)));;
			MS_FTPSERVER_IP = GetValidValue(syscfgmap.get("MS_FTPSERVER_IP"), MS_FTPSERVER_IP);
			MS_FTPSERVER_PORT = Integer.parseInt(GetValidValue(syscfgmap.get("MS_FTPSERVER_PORT"), String.valueOf(MS_FTPSERVER_PORT)));;
			MS_FTPSERVER_USERNAME = GetValidValue(syscfgmap.get("MS_FTPSERVER_USERNAME"), MS_FTPSERVER_USERNAME);
			MS_FTPSERVER_PASSWORD = GetValidValue(syscfgmap.get("MS_FTPSERVER_PASSWORD"), MS_FTPSERVER_PASSWORD);
			MS_REMOTE_FILE = GetValidValue(syscfgmap.get("MS_REMOTE_FILE"), MS_REMOTE_FILE);
			MS_LOCAL_FILE = GetValidValue(syscfgmap.get("MS_LOCAL_FILE"), MS_LOCAL_FILE);
			
			TIGGER_FIELD_DEFAUTL = Integer.parseInt(GetValidValue(syscfgmap.get("TIGGER_FIELD_DEFAUTL"), String.valueOf(TIGGER_FIELD_DEFAUTL)));;
		}
		
	}
	
	private static String GetValidValue(String value, String DefaultValue){
		if(value != null && value.length() > 0){
			return value;
		}else{
			return DefaultValue;
		}
	}
	
	public static HashMap<String, String> findAll(){
		HashMap<String, String> map = new HashMap<String, String>();
		List<SysConfigVO> alllist = StaticMongoTemplate.getStaticMongoTemplate().findAll(SysConfigVO.class);
		if(alllist != null){
			for(SysConfigVO uvo: alllist){
				map.put(uvo.getCfgName(), uvo.getCfgValue());
			}
		}
		return map;
	}
	
	public static void initSysCfgDb(){
		
		// mail cfg start
		SysConfigVO uvo = new SysConfigVO();

		uvo.setCfgName("logswitcher");
		uvo.setCfgAlias("Massive Log Printer (1: open  0:close)");
		uvo.setCfgValue("0");
		uvo.setCfgDefaultValue("0");
		initlist.add(uvo);

//		uvo = new SysConfigVO();
//		uvo.setCfgName("datasource");
//		uvo.setCfgAlias("Data Source (1:Paritech 2:Morningstar)");
//		uvo.setCfgValue("1");
//		uvo.setCfgDefaultValue("1");
//		initlist.add(uvo);
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailSwitcher");
		uvo.setCfgAlias("Mail Switcher (1: open  0:close)");
		uvo.setCfgValue("1");
		uvo.setCfgDefaultValue("1");
		initlist.add(uvo);
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailUsername");
		uvo.setCfgAlias("Mail Username");
		uvo.setCfgValue("admin@omnimarkettide.com");
		uvo.setCfgDefaultValue("admin@omnimarkettide.com");
		initlist.add(uvo);
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailPassword");
		uvo.setCfgAlias("Mail Password");
		uvo.setCfgValue("MSqHOt5bxJh5656wqPbR4A");
		uvo.setCfgDefaultValue("MSqHOt5bxJh5656wqPbR4A");
		initlist.add(uvo);
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailAlias");
		uvo.setCfgAlias("Mail From");
		uvo.setCfgValue("virtualagm@omnimarkettide.com");
		uvo.setCfgDefaultValue("virtualagm@omnimarkettide.com");
		initlist.add(uvo);
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailTitle");
		uvo.setCfgAlias("Mail Title");
		uvo.setCfgValue("Omni Market Tide Ltd");
		uvo.setCfgDefaultValue("Omni Market Tide Ltd");
		initlist.add(uvo);		
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailSubject");
		uvo.setCfgAlias("Mail Subject");
		uvo.setCfgValue("OMT 2016 AGM");
		uvo.setCfgDefaultValue("OMT 2016 AGM");
		initlist.add(uvo);		
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailBody");
		uvo.setCfgAlias("Mail Content");
		uvo.setCfgValue("Thanks for lodging your vote via OmniLOOP for Omni Market Tide Ltd's 2016 Annual General Meeting. \n\nYour vote has been received and recorded.\n\nYour voting reference number is: [NUMBER] \n\nKind regards\n\nOmni Market Tide Ltd");
		uvo.setCfgDefaultValue("Thanks for lodging your vote via OmniLOOP for Omni Market Tide Ltd's 2016 Annual General Meeting. \n\nYour vote has been received and recorded.\n\nYour voting reference number is: [NUMBER] \n\nKind regards\n\nOmni Market Tide Ltd");
		initlist.add(uvo);
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailTags");
		uvo.setCfgAlias("Mail Tag");
		uvo.setCfgValue("OMTVEC");
		uvo.setCfgDefaultValue("OMTVEC");
		initlist.add(uvo);	
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailHost");
		uvo.setCfgAlias("Mail Host");
		uvo.setCfgValue("smtp.mandrillapp.com");
		uvo.setCfgDefaultValue("smtp.mandrillapp.com");
		initlist.add(uvo);		
		
		uvo = new SysConfigVO();
		uvo.setCfgName("mailPort");
		uvo.setCfgAlias("Mail Port");
		uvo.setCfgValue("587");
		uvo.setCfgDefaultValue("587");
		initlist.add(uvo);		
		// mail cfg end
		
		// heroku cfg start
		uvo = new SysConfigVO();
		uvo.setCfgName("herokuRestUrl");
		uvo.setCfgAlias("Heroku Rest URL");
		uvo.setCfgValue("http://omt-custom.herokuapp.com:80/api/pricetracker");
		uvo.setCfgDefaultValue("http://omt-custom.herokuapp.com:80/api/pricetracker");
		initlist.add(uvo);		

		uvo = new SysConfigVO();
		uvo.setCfgName("herokuCallInterval");
		uvo.setCfgAlias("Heroku Call Interval");
		uvo.setCfgValue("60");
		uvo.setCfgDefaultValue("60");
		initlist.add(uvo);		

		uvo = new SysConfigVO();
		uvo.setCfgName("TriggerAllRestUrl");
		uvo.setCfgAlias("Trigger All Share Price");
		uvo.setCfgValue("http://omt-custom.herokuapp.com:80/api/allpricetracker");
		uvo.setCfgDefaultValue("http://omt-custom.herokuapp.com:80/api/allpricetracker");
		initlist.add(uvo);		

		uvo = new SysConfigVO();
		uvo.setCfgName("TriggerAllCallInterval");
		uvo.setCfgAlias("Trigger All Share Price Interval");
		uvo.setCfgValue("180");
		uvo.setCfgDefaultValue("180");
		initlist.add(uvo);		

		uvo = new SysConfigVO();
		uvo.setCfgName("TIGGER_FIELD_DEFAUTL");
		uvo.setCfgAlias("Default Trigger Field(1:Last, 2:BestBid, 3:BestAsk, 4:Open, 5:Close, 6:High, 7:Low)");
		uvo.setCfgValue("1");
		uvo.setCfgDefaultValue("1");
		initlist.add(uvo);		
		// heroku cfg end
		
		// asx cfg start
		uvo = new SysConfigVO();
		uvo.setCfgName("asxGainsUrl");
		uvo.setCfgAlias("ASX Gains URL");
		uvo.setCfgValue("http://www.asx.com.au/asx/widget/topGains.do");
		uvo.setCfgDefaultValue("http://www.asx.com.au/asx/widget/topGains.do");
		initlist.add(uvo);		
		uvo = new SysConfigVO();
		uvo.setCfgName("asxDeclinesUrl");
		uvo.setCfgAlias("ASX Declines URL");
		uvo.setCfgValue("http://www.asx.com.au/asx/widget/topDeclines.do");
		uvo.setCfgDefaultValue("http://www.asx.com.au/asx/widget/topDeclines.do");
		initlist.add(uvo);		
		uvo = new SysConfigVO();
		uvo.setCfgName("asxPageContainer");
		uvo.setCfgAlias("ASX Page Container");
		uvo.setCfgValue("top5-data");
		uvo.setCfgDefaultValue("top5-data");
		initlist.add(uvo);		

		// asc cfg end
		
		// westpac news cfg start
		uvo = new SysConfigVO();
		uvo.setCfgName("westpacNewsUrl");
		uvo.setCfgAlias("Westpac News URL");
		uvo.setCfgValue("http://ir.iguana2.com/westpac/news-history");
		uvo.setCfgDefaultValue("http://ir.iguana2.com/westpac/news-history");
		initlist.add(uvo);		
		
		uvo = new SysConfigVO();
		uvo.setCfgName("westpacNewsContainer");
		uvo.setCfgAlias("Westpac News Container");
		uvo.setCfgValue("table");
		uvo.setCfgDefaultValue("table");
		initlist.add(uvo);		
		
		uvo = new SysConfigVO();
		uvo.setCfgName("westpacNewsContainerIndex");
		uvo.setCfgAlias("Westpac News Container Index");
		uvo.setCfgValue("2");
		uvo.setCfgDefaultValue("2");
		initlist.add(uvo);		
		// westpac news cfg end
		
		// paritech cfg start
//		uvo = new SysConfigVO();
//		uvo.setCfgName("paritechTokenUrl");
//		uvo.setCfgAlias("Paritech Token URL");
//		uvo.setCfgValue("https://api.paritech.com/Paritech.AuthServer/OAuth2/Token");
//		uvo.setCfgDefaultValue("https://api.paritech.com/Paritech.AuthServer/OAuth2/Token");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("paritechWssUrl");
//		uvo.setCfgAlias("Paritech Wssocket URL");
//		uvo.setCfgValue("wss://wsapi2.paritech.com/Zenith");
//		uvo.setCfgDefaultValue("wss://wsapi2.paritech.com/Zenith");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("paritechWssProtocol");
//		uvo.setCfgAlias("Paritech Wssocket Protocol");
//		uvo.setCfgValue("ZenithJson");
//		uvo.setCfgDefaultValue("ZenithJson");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("paritechWssScope");
//		uvo.setCfgAlias("Paritech Wssocket Scope");
//		uvo.setCfgValue("http://api.paritech.com/wsapi/market/asset http://api.paritech.com/wsapi/market/depthfull http://api.paritech.com/wsapi/market/depthshort http://api.paritech.com/wsapi/market/trades http://api.paritech.com/wsapi/market/news http://api.paritech.com/wsapi/trading/accounts http://api.paritech.com/wsapi/trading/balances http://api.paritech.com/wsapi/trading/holdings http://api.paritech.com/wsapi/trading/orders http://api.paritech.com/wsapi/trading/transactions http://api.paritech.com/wsapi/market/symbols");
//		uvo.setCfgDefaultValue("http://api.paritech.com/wsapi/market/asset http://api.paritech.com/wsapi/market/depthfull http://api.paritech.com/wsapi/market/depthshort http://api.paritech.com/wsapi/market/trades http://api.paritech.com/wsapi/market/news http://api.paritech.com/wsapi/trading/accounts http://api.paritech.com/wsapi/trading/balances http://api.paritech.com/wsapi/trading/holdings http://api.paritech.com/wsapi/trading/orders http://api.paritech.com/wsapi/trading/transactions http://api.paritech.com/wsapi/market/symbols");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("wssConnUserName");
//		uvo.setCfgAlias("Paritech Wssocket UserName");
//		uvo.setCfgValue("OMNILOOP");
//		uvo.setCfgDefaultValue("OMNILOOP");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("wssConnPassword");
//		uvo.setCfgAlias("Paritech Wssocket Password");
//		uvo.setCfgValue("qoz-why80");
//		uvo.setCfgDefaultValue("qoz-why80");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("wssConnGrantType");
//		uvo.setCfgAlias("Paritech Wssocket Grant Type");
//		uvo.setCfgValue("password");
//		uvo.setCfgDefaultValue("password");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("wssConnGrantRefresh");
//		uvo.setCfgAlias("Paritech Wssocket Refresh");
//		uvo.setCfgValue("refresh_token");
//		uvo.setCfgDefaultValue("refresh_token");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("wssClientId");
//		uvo.setCfgAlias("Paritech Wssocket ClientId");
//		uvo.setCfgValue("omniloop");
//		uvo.setCfgDefaultValue("omniloop");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("wssClientPwd");
//		uvo.setCfgAlias("Paritech Wssocket ClientSecurity");
//		uvo.setCfgValue("nm27o9");
//		uvo.setCfgDefaultValue("nm27o9");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("wssTokenInterval");
//		uvo.setCfgAlias("Paritech Wssocket Refresh Interval");
//		uvo.setCfgValue("19");
//		uvo.setCfgDefaultValue("19");
//		initlist.add(uvo);		
		// paritech cfg end

		// data exchanger cfg start
//		uvo = new SysConfigVO();
//		uvo.setCfgName("restCompaniesUrl");
//		uvo.setCfgAlias("REST Companies URL");
//		uvo.setCfgValue("http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/companies");
//		uvo.setCfgDefaultValue("http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/companies");
//		initlist.add(uvo);		
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("restLocalUserUrl");
//		uvo.setCfgAlias("REST LocalUser URL");
//		uvo.setCfgValue("http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/company-instances/{value}/managers");
//		uvo.setCfgDefaultValue("http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/company-instances/{value}/managers");
//		initlist.add(uvo);	
//		
//		uvo = new SysConfigVO();
//		uvo.setCfgName("restPostMsgUrl");
//		uvo.setCfgAlias("REST PostData URL");
//		uvo.setCfgValue("http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/users/{value}/stock-subscriptions");
//		uvo.setCfgDefaultValue("http://cmsmobileapi-env.elasticbeanstalk.com/mobile/api/users/{value}/stock-subscriptions");
//		initlist.add(uvo);		
		
		// data exchanger cfg end
		
		// program system configuration start
		uvo = new SysConfigVO();
		uvo.setCfgName("wssReconnectTimes");
		uvo.setCfgAlias("Reconnect Times");
		uvo.setCfgValue("3");
		uvo.setCfgDefaultValue("3");
		initlist.add(uvo);		
		
		uvo = new SysConfigVO();
		uvo.setCfgName("charthistoryGetWay");
		uvo.setCfgAlias("Get ChartHistory Type");
		uvo.setCfgValue("2");
		uvo.setCfgDefaultValue("2");
		initlist.add(uvo);		
		
		// For morningstar
		uvo = new SysConfigVO();
		uvo.setCfgName("MS_REQUEST_URL_SP");
		uvo.setCfgAlias("SharePrice Request URL prefix of Morningstar");
		uvo.setCfgValue("http://msxml.tenfore.com/index.php");
		uvo.setCfgDefaultValue("http://msxml.tenfore.com/index.php");
		initlist.add(uvo);
		
		uvo = new SysConfigVO();
		uvo.setCfgName("MS_REQUEST_URL_CH");
		uvo.setCfgAlias("ChartHistory Request URL prefix of Morningstar");
		uvo.setCfgValue("http://msxml.tenfore.com/indexTS");
		uvo.setCfgDefaultValue("http://msxml.tenfore.com/indexTS");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_USERNAME");
		uvo.setCfgAlias("Username of Morningstar");
		uvo.setCfgValue("OmOm15734");
		uvo.setCfgDefaultValue("OmOm15734");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_PASSWORD");
		uvo.setCfgAlias("Password of Morningstar");
		uvo.setCfgValue("5LU5ZG");
		uvo.setCfgDefaultValue("5LU5ZG");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_JSONTAG");
		uvo.setCfgAlias("JsonShort of Morningstar");
		uvo.setCfgValue("JSONShort");
		uvo.setCfgDefaultValue("JSONShort");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_TASK_24H_START");
		uvo.setCfgAlias("TimerTask start time (24H)");
		uvo.setCfgValue("6");
		uvo.setCfgDefaultValue("6");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_TASK_24H_STOP");
		uvo.setCfgAlias("TimerTask stop time (24H)");
		uvo.setCfgValue("18");
		uvo.setCfgDefaultValue("18");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_DATA_SWITCHER");
		uvo.setCfgAlias("Question List store type(1: same table;2: different table");
		uvo.setCfgValue("1");
		uvo.setCfgDefaultValue("1");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("TIME_INTERVAL_REQ");
		uvo.setCfgAlias("Time Interval Morningstar(in millisecond)");
		uvo.setCfgValue("100");
		uvo.setCfgDefaultValue("100");
		initlist.add(uvo);

		uvo = new SysConfigVO();
		uvo.setCfgName("CHART_HISTORY_DEFAULT");
		uvo.setCfgAlias("Chart History days(one year)");
		uvo.setCfgValue("-398");
		uvo.setCfgDefaultValue("-398");
		initlist.add(uvo); 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_REQUEST_TIMEOUT");
		uvo.setCfgAlias("HTTP Request Timeout(Second)");
		uvo.setCfgValue("2");
		uvo.setCfgDefaultValue("2");
		initlist.add(uvo); 
		
		uvo = new SysConfigVO();
		uvo.setCfgName("NO_DATA_API_TIMEOUT");
		uvo.setCfgAlias("APIs Request Timeout(Second)");
		uvo.setCfgValue("5");
		uvo.setCfgDefaultValue("5");
		initlist.add(uvo); 

		//------------------------------------------------------------
		//Morningstar FTP 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_SYMBOL_SYNC_FLAG");
		uvo.setCfgAlias("MS Apply Symbol Synchronization");
		uvo.setCfgValue("1");
		uvo.setCfgDefaultValue("1");
		initlist.add(uvo); 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_FTPSERVER_IP");
		uvo.setCfgAlias("MS FTP Server IP");
		uvo.setCfgValue("83.244.214.45");
		uvo.setCfgDefaultValue("83.244.214.45");
		initlist.add(uvo); 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_FTPSERVER_PORT");
		uvo.setCfgAlias("MS FTP Server Port");
		uvo.setCfgValue("21");
		uvo.setCfgDefaultValue("21");
		initlist.add(uvo); 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_FTPSERVER_USERNAME");
		uvo.setCfgAlias("MS FTP Server Username");
		uvo.setCfgValue("publicftp");
		uvo.setCfgDefaultValue("publicftp");
		initlist.add(uvo); 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_FTPSERVER_PASSWORD");
		uvo.setCfgAlias("MS FTP Server Password");
		uvo.setCfgValue("publicftp");
		uvo.setCfgDefaultValue("publicftp");
		initlist.add(uvo); 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_REMOTE_FILE");
		uvo.setCfgAlias("MS Symbol file name");
		uvo.setCfgValue("/Symbol Guides/146 - Australia Stock Exchange.txt");
		uvo.setCfgDefaultValue("/Symbol Guides/146 - Australia Stock Exchange.txt");
		initlist.add(uvo); 

		uvo = new SysConfigVO();
		uvo.setCfgName("MS_LOCAL_FILE");
		uvo.setCfgAlias("MS Local file name");
		uvo.setCfgValue("AustraliaStockExchange.txt");
		uvo.setCfgDefaultValue("AustraliaStockExchange.txt");
		initlist.add(uvo); 
		//------------------------------------------------------------

		// program system configuration end

		for(SysConfigVO entity: initlist){
			if(IsExist(entity)){
				continue;
			}
			Query query = new Query();
			query.addCriteria(Criteria.where("cfgName").is(entity.getCfgName()));
			query.addCriteria(Criteria.where("cfgAlias").is(entity.getCfgAlias()));
			query.addCriteria(Criteria.where("cfgDefaultValue").is(entity.getCfgDefaultValue()));
			
	    	Update update = new Update();
	    	update.set("cfgName", entity.getCfgName());
	    	update.set("cfgAlias", entity.getCfgAlias());
	    	update.set("cfgValue", entity.getCfgValue());
			
			StaticMongoTemplate.getStaticMongoTemplate().upsert(query, update, SysConfigVO.class);
		}
		
		// update the value from database as starting...
		updateSysConfig();
	}
	
	private static boolean IsExist(SysConfigVO entity){
		Query query = new Query();
		query.addCriteria(Criteria.where("cfgName").is(entity.getCfgName()));
		query.addCriteria(Criteria.where("cfgAlias").is(entity.getCfgAlias()));
		query.addCriteria(Criteria.where("cfgDefaultValue").is(entity.getCfgDefaultValue()));
		
    	SysConfigVO ret = (SysConfigVO) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, SysConfigVO.class);
    	if(ret != null){
    		return true;
    	}else{
    		return false;
    	}
	}
}
