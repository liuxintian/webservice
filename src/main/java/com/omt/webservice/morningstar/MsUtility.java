package com.omt.webservice.morningstar;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.dao.MsDao;
import com.omt.webservice.morningstar.entity.ChartData;
import com.omt.webservice.morningstar.entity.Instrument;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.webservice.morningstar.entity.MsChartOriginal;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.webservice.morningstar.entity.ShareDataM;
import com.omt.websocket.WebSocketClient;
import com.omt.websocket.entity.CompanyList;
import com.omt.websocket.entity.SharePriceReq;
import com.omt.websocket.entity.light.LightLastClose;

/**
 * 
 * @author tonyliu
 *
 */
public class MsUtility {
	private static Logger omtlogger = Logger.getLogger(MsUtility.class);
	public static int totalRequestCount = 0;
	public static int totalResponseCount = 0;
	
	public static final String FIELD_LIST_NAME = "omtfieldlist";
	public static String PRICE_FIELD_LIST = "&fields=D2,D4,D5,D6,D7,D16,D17,D18,D19,D20,D30,D140,D141,D167,D185,D243,H1,H8,H14,S8,S9,S12,S1734,S1736,S3377";
	public static ArrayBlockingQueue<CompanyList> CODES_QUEUE = new ArrayBlockingQueue<CompanyList>(10000);
	
	private static boolean useHashMap = true;
	public static Hashtable<String, Instrument> SYMBOL_INSTRUMENT_MAP = new Hashtable<String, Instrument>();

	public static RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
	public static Hashtable<String, String> ExchangerHs = new Hashtable<String, String>();
	static{
		ExchangerHs.put("HKX", "134.1"); //HongKong
		ExchangerHs.put("SHX", "136.1"); //ShangHai
		ExchangerHs.put("SZX", "137.1"); //ShenZhen
		ExchangerHs.put("SGX", "143.1"); //Singapore
		ExchangerHs.put("ASX", "146.1"); //Australia
		ExchangerHs.put("NZX", "147.1"); //ShenZhen
	}
	
	private static ClientHttpRequestFactory getClientHttpRequestFactory() {
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(StaticConfig.MS_REQUEST_TIMEOUT * 1000);
	    clientHttpRequestFactory.setReadTimeout(StaticConfig.MS_REQUEST_TIMEOUT * 1000);
	    return clientHttpRequestFactory;
	}
	
	/**
	 * Send Request and parse response and insert into database;
	 * http://msxml.tenfore.com/index.php?username=OmOm15734&password=5LU5ZG&JSON&instrument=146.1.TLS&fields=HA,DA,SA
	 * http://msxml.tenfore.com/index.php?username=OmOm15734&password=5LU5ZG&JSONShort&instrument=146.1.TLS&fields=HA,DA,SA
	 * @param code
	 * @return
	 */
	public static void getSharePriceVO(Collection<CompanyList> codelist) {
		if(codelist == null || codelist.size() == 0){
			omtlogger.error("---getSharePriceVO met error: please check input parameter:"+codelist);
		}
		try{
			StringBuilder url = new StringBuilder();
			url.append(organizeRequestPrefix(StaticConfig.MS_REQUEST_URL_SP))
			   .append(PRICE_FIELD_LIST)
			   .append(organizeReqInstrument(codelist));
			
			restTemplate.setRequestFactory(getClientHttpRequestFactory());
	    	ResponseEntity<String> response = restTemplate.exchange(url.toString().replaceAll(" ", ""), HttpMethod.GET, null, String.class);
	    	String responseBody = response.getBody();
	    	JSONObject obj = new JSONObject(responseBody);
	    	ObjectMapper mapper = new ObjectMapper();
			if(StaticConfig.logswitcher == 2){
				omtlogger.info("---Morning star getSharePriceVO Request with url:"+ url);
				omtlogger.info("---Morning star getSharePriceVO Request totalcount:"+ totalRequestCount);
				//omtlogger.info("---Morning star getSharePriceVO Response body:"+ responseBody);
			}
			// Refresh the request Queue based on code and market.
			for(CompanyList company: codelist){
				//if( (company.getCode().equalsIgnoreCase(value.getCode()) ) &&  ( company.getMarket().equalsIgnoreCase(value.getMarket()) ) ) 
				{
					MsUtility.CODES_QUEUE.add(company);
				}
			}
//			if(url.toString().contains("ORA")){
//				omtlogger.info("\n\nORA----RSP:"+ responseBody+"\n\n");
//			}
			
	    	if(obj.has("quotes")){
	    		JSONObject quotes = obj.getJSONObject("quotes");
	    		if(quotes.has("error")){
	    			JSONArray errorAry = quotes.getJSONArray("error");
	    			if(errorAry != null && errorAry.length() > 0){
	    				omtlogger.error("Get share price met error response:"+errorAry.getString(0));
	    			}
	    		}
	    		// just parsing
	    		// else
	    		{
		    		if(quotes.has("results")){
		    			JSONArray resultAry = quotes.getJSONArray("results");
		    			totalResponseCount += resultAry.length();
		    			MsSharePrice retUVO = null;
		    			ShareDataM value = null;
		    			for(Object data: resultAry){
		    				retUVO = new MsSharePrice();
		    				
		    				value = mapper.readValue(data.toString(), ShareDataM.class);
		    				if(value != null){
			    				value.setTimestamp(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));
			    				retUVO.setData(value);
			    				retUVO.setName(value.getCode());
			    				retUVO.setDatetime(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));
			    				
			    				String market = WebSocketClient.DEFAULT_MARKT;
			    				if(WebSocketClient.CODE_COMPANYLIST.get(value.getCode()) != null){
			    					market = WebSocketClient.CODE_COMPANYLIST.get(value.getCode()).getMarket();
			    				}
			    				retUVO.setMarket(market);
			    				
			    				// For trigger using start
			    				{
				    				SharePriceReq spr = new SharePriceReq();
				    				spr.setCode(value.getCode());
				    				spr.setMarket(market);
				    				MsSharePrice oldone = MsDao.findOneSharePrice(spr);
				    				if(oldone != null){
					    				MsDao.updateInsertMsSharePriceOld(oldone);
				    				}else{
					    				MsDao.updateInsertMsSharePriceOld(retUVO);
				    				}
			    				}
			    				// For trigger using end
			    				
			    				MsDao.updateInsertMsSharePrice(retUVO);
		    				}
		    			}
		    		}
	    		}
	    	}
	    	if(StaticConfig.logswitcher == 2) omtlogger.info("---Morning star getSharePriceVO Response totalcount:"+ totalResponseCount);
			
		}catch(Exception ex){
			// if meet exception [for instance: network error] keep the queue full
			for(CompanyList company: codelist){
				MsUtility.CODES_QUEUE.add(company);
			}
			ex.printStackTrace();
		}
	}
	
	/**
	 * Send Request and parse response and insert into database;
	 * [Up to 10] instruments can now be requested in a single 90 Day T&S request, for example
	 * This has the following possible values minbar, houbar, dailybar, Weeklybar, Monthlybar, bar, and tick. Please note dailybar currently run midnight to midnight GMT
       http://mstxml.tenfore.com/IndexTS/?username=XXXX&password=XXXX&Instrument=151.1.VOD,151. 1.BARC&sdate=04-01-2013&stime=09:00:00&edate=04-01-2013&etime=10:02:04&type=minbar
	 * {"ts":{"results":[{"symbol":"VOD","exchangeid":"151","type":"1","data":[ {"D953":"04-01-2013","D952":"09:51","D16":"22520","D17":"157.7499","D18":"157.7762","D19":"157.7499","D2":"157.7762"}, {"D953":"04-01-2013","D952":"09:50","D16":"11500","D17":"157.75","D18":"157.75","D19":"157.7485","D2":"157.7485"}, {"D953":"04-01-2013","D952":"09:49","D16":"54729","D17":"157.777","D18":"157.8","D19":"157.75","D2":"157.8"}, {"D953":"04-01-2013","D952":"09:48","D16":"12830","D17":"157.8","D18":"157.8","D19":"157.787","D2":"157.8"},
	 * @param code
	 * @return
	 */
	public static void getChartHistory(Set<CompanyList> companylist, String stDate, String endDate){
		if(companylist == null || companylist.size() == 0){
			omtlogger.error("---getChartHistory met error: please check input parameter:"+companylist);
		}
		
		try{
			StringBuilder url = new StringBuilder();
			url.append(organizeRequestPrefix(StaticConfig.MS_REQUEST_URL_CH))
			   .append(organizeReqInstrument(companylist))
			   .append(organizeReqFiledsChartHistory(stDate, endDate));

			restTemplate.setRequestFactory(getClientHttpRequestFactory());
			ResponseEntity<String> response = restTemplate.exchange(url.toString().replaceAll(" ", ""), HttpMethod.GET, null, String.class);
			String responseBody = response.getBody();
			JSONObject obj = new JSONObject(responseBody);
			ObjectMapper mapper = new ObjectMapper();
			if(StaticConfig.logswitcher == 2) {
				omtlogger.info("---Morning star getChartHistory Request with url:"+ url);
				//omtlogger.info("---Morning star getChartHistory Response body:"+ responseBody);
			}
	   	
			if(obj.has("ts")){
				JSONObject ts = obj.getJSONObject("ts");
				if(ts.has("error")){
		   			JSONArray errorAry = ts.getJSONArray("error");
		   			if(errorAry != null && errorAry.length() > 0){
		   				omtlogger.error("getChartHistory met error response:"+errorAry.getString(0) +" -- with instrument list:"+organizeReqInstrument(companylist));
		   			}
		   		}
				// AAA Just log the error message above, but continuously parsing the results, as there should be some returns for the code list
				//else
				{
		   			if(ts.has("results")){
		   				JSONArray resultAry = ts.getJSONArray("results");
		   				MsChartHistory uvo = null;
		   				MsChartOriginal value = null;
			    			
		   				for(Object data: resultAry){
							value = mapper.readValue(data.toString(), MsChartOriginal.class);
		   					if(value != null){
		   						List<ChartData> sharedata = value.getData();
		   						// AAA Just log the empty message below, but continuously dealing other results
		   						if(sharedata == null || sharedata.size() == 0){
		   							omtlogger.warn("getChartHistory met empty data[] with code:"+ value.getSymbol());
		   							//omtlogger.warn("with responseBody:"+ responseBody);
		   							continue;
		   						}
		   						Collections.reverse(sharedata);
		   						uvo = new MsChartHistory();
		   						uvo.setCode(value.getSymbol());
		   						uvo.setDate(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC));
		   						uvo.setValue(mapper.writeValueAsString(sharedata));
		   						uvo.setCount(value.getData().size());
		   						
			    				String market = WebSocketClient.DEFAULT_MARKT;
			    				if(WebSocketClient.CODE_COMPANYLIST.get(value.getSymbol()) != null){
			    					market = WebSocketClient.CODE_COMPANYLIST.get(value.getSymbol()).getMarket();
			    				}
			    				uvo.setMarket(market);

			    				MsDao.updateInsertChartHistory(uvo);
		   					}
		   				}
		   			}
		   		}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Get only one day chart history for close price & store into database.
	 * @param instrumentlist
	 * @param stDate
	 * @param endDate
	 */
	public static void getOneDayChartHistory(List<Instrument> instrumentlist, String stDate, String endDate){
		if(instrumentlist == null || instrumentlist.size() == 0){
			omtlogger.error("---getOneDayChartHistory met error: please check input parameter:"+instrumentlist);
		}
		
		try{
			StringBuilder url = new StringBuilder();
			url.append(organizeRequestPrefix(StaticConfig.MS_REQUEST_URL_CH))
			   .append(organizePureInstrument(instrumentlist))
			   .append(organizeReqFiledsChartHistory(stDate, endDate));

			restTemplate.setRequestFactory(getClientHttpRequestFactory());
			ResponseEntity<String> response = restTemplate.exchange(url.toString().replaceAll(" ", ""), HttpMethod.GET, null, String.class);
			String responseBody = response.getBody();
			JSONObject obj = new JSONObject(responseBody);
			ObjectMapper mapper = new ObjectMapper();
			if(StaticConfig.logswitcher == 2) {
				omtlogger.info("---Morning star getOneDayChartHistory Request with url:"+ url);
				//omtlogger.info("---Morning star getChartHistory Response body:"+ responseBody);
			}
	   	
			if(obj.has("ts")){
				JSONObject ts = obj.getJSONObject("ts");
				if(ts.has("error")){
		   			JSONArray errorAry = ts.getJSONArray("error");
		   			if(errorAry != null && errorAry.length() > 0){
		   				omtlogger.error("getOneDayChartHistory met error response:"+errorAry.getString(0) +" -- with instrument list:"+organizePureInstrument(instrumentlist));
		   			}
		   		}
				// AAA Just log the error message above, but continuously parsing the results, as there should be some returns for the code list
				//else
				{
		   			if(ts.has("results")){
		   				JSONArray resultAry = ts.getJSONArray("results");
		   				LightLastClose uvo = null;
		   				MsChartOriginal value = null;
			    			
		   				for(Object data: resultAry){
							value = mapper.readValue(data.toString(), MsChartOriginal.class);
		   					if(value != null){
		   						List<ChartData> sharedata = value.getData();
		   						// AAA Just log the empty message below, but continuously dealing other results
		   						if(sharedata == null || sharedata.size() == 0){
		   							if(StaticConfig.logswitcher == 2) omtlogger.warn("getOneDayChartHistory met empty data[] with code:"+ value.getSymbol());
		   							
		   							continue;
		   						}
		   						Collections.reverse(sharedata);
		   						
		   						uvo = new LightLastClose();
		   						uvo.setCode(value.getSymbol());
		   						
			    				String exchange = WebSocketClient.DEFAULT_MARKT;
			    				if(WebSocketClient.CODE_COMPANYLIST.get(value.getSymbol()) != null){
			    					exchange = WebSocketClient.CODE_COMPANYLIST.get(value.getSymbol()).getMarket();
			    				}
		   						uvo.setExchange(exchange);
		   						uvo.setClose(sharedata.get(0).getClose());

			    				MsDao.updateInsertOneDayChartHistory(uvo);
		   					}
		   				}
		   			}
		   		}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static String organizePureInstrument(List<Instrument> instrumentlist){
		StringBuffer str = new StringBuffer();
		str.append("&instrument=");
		if(instrumentlist != null && instrumentlist.size() > 0){
			for(Instrument instrument: instrumentlist){
				str.append(instrument.getInstrument()).append(",");
			}
		}
		return str.toString().substring(0,str.toString().length()-1);
	}	
	
	/**
	 * Join the domain name and username password.
	 * @return
	 */
	public static String organizeRequestPrefix(String url){
		StringBuffer str = new StringBuffer();
		str.append(url)
		   .append("?username=").append(StaticConfig.MS_USERNAME)
		   .append("&password=").append(StaticConfig.MS_PASSWORD)
		   .append("&").append(StaticConfig.MS_JSONTAG);
		return str.toString();
	}
	
	/**
	 * Organize request instrument for all
	 *  this will be followed by one or more triplets of <Exchange>.<SecType>.<Symbol> separated by comma, max 500.
	 * @param codelist
	 * @return
	 */
	public static String organizeReqInstrument(Collection<CompanyList> companylist){
		StringBuffer str = new StringBuffer();
		str.append("&instrument=");
		if(companylist != null && companylist.size() > 0){
			for(CompanyList company: companylist){
				str.append(composeInstrument(company)).append(",");
			}
		}
		return str.toString().substring(0,str.toString().length()-1);
	}
	private static String composeInstrument(CompanyList company){
		StringBuffer str = new StringBuffer();
		str.append(QueryInstrument(company));
		return str.toString();
	}
	
	private static String QueryInstrument(CompanyList company){
		Instrument instrument = null;
		// 1. directly query mongo
		if(!useHashMap)
		{
			Query query = new Query();
			query.addCriteria(Criteria.where("market").is(company.getMarket()));
			query.addCriteria(Criteria.where("symbol").is(company.getCode()));
			instrument = StaticMongoTemplate.getStaticMongoTemplate().findOne(query, Instrument.class);
			
		}
		else
		// Or 2. use map hash
		{
			instrument = SYMBOL_INSTRUMENT_MAP.get(company.getUniqstr());
		}
		
		StringBuilder str = new StringBuilder();
		if(instrument != null){
			str.append(instrument.getInstrument());
		}else{
			str.append(ExchangerHs.get(company.getMarket())).append(".").append(company.getCode());
			if(StaticConfig.logswitcher == 2) omtlogger.error("Get error when QueryInstrument: cannot find needed instrument for symbol:"+company.getUniqstr() +"With the above error, the symbol list should be synchronised.");
		}
		return str.toString();
	}
	/**
	 * Organize request fields for chart history
	 * &sdate=14-03-2016&stime=09:30:00&edate=25-04-2016&etime=17:30:00&type=tick
	 * Type: has the following possible values minbar, houbar, dailybar, Weeklybar, Monthlybar, bar, and tick. Please note dailybar currently run midnight to midnight GMT
	 * @return
	 */
	public static String organizeReqFiledsChartHistory(String stDate, String endDate){
		String sttime = "09:30:00";
		String endtime = "17:30:00";
		String type = "dailybar"; //minbar, houbar, dailybar, Weeklybar, Monthlybar, bar, and tick
		
		StringBuffer str = new StringBuffer();
		str.append("&sdate=").append(stDate).append("&stime=").append(sttime)
		   .append("&edate=").append(endDate).append("&etime=").append(endtime)
		   .append("&type=").append(type);
		return str.toString();
	}
	/**
	 * Organize request fields for share price
	 *  this can come before or after &Instrument in the line and defines the fields for all the instruments in the line. Only one instance of &fields= can be used, fields are as defined in the Morningstar Exchange and fields codes document. The maximum number of fields that may be requested in either a list or directly is 100.
	 * Without: D8,D12
	 * @return
	 */
	
	public static boolean createFieldList(){
		boolean ret = false;
		String fields = "&fields=D2,D4,D5,D6,D7,D16,D17,D18,D19,D20,D30,D140,D141,D167,D185,D243,H1,H8,H14,S8,S9,S12,S1734,S1736,S3377";
		try{
			StringBuilder url = new StringBuilder();
			url.append(organizeRequestPrefix(StaticConfig.MS_REQUEST_URL_SP))
			   .append("&addfieldlist=").append(FIELD_LIST_NAME).append(fields);
			
			restTemplate.setRequestFactory(getClientHttpRequestFactory());
	    	ResponseEntity<String> response = restTemplate.exchange(url.toString().replaceAll(" ", ""), HttpMethod.GET, null, String.class);
	    	String responseBody = response.getBody();
	    	JSONObject obj = new JSONObject(responseBody);
			
	    	if(obj.has("quotes")){
	    		JSONObject quotes = obj.getJSONObject("quotes");
	    		if(quotes.has("error")){
	    			JSONArray errorAry = quotes.getJSONArray("error");
	    			if(errorAry != null && errorAry.length() > 0){
	    				omtlogger.error("CreateFieldList met error response:"+errorAry.getString(0));
	    			}
	    			ret = false;
	    		}else{
	    			ret = true;
	    		}
	    	}else{
    			ret = false;
	    	}
		}catch(Exception ex){
			ret = false;
			ex.printStackTrace();
		}
		
		if(ret){
			PRICE_FIELD_LIST = "&fieldlist=" + FIELD_LIST_NAME;
			omtlogger.info("CreateFieldList success use fieldlist:" + FIELD_LIST_NAME);
		}else{
			PRICE_FIELD_LIST = fields;
			omtlogger.info("CreateFieldList failed use fieldlist:" + PRICE_FIELD_LIST);
		}
		
		return ret;
	}
	
	public static boolean synchronizeCodeList(CompanyList company){
		if(company == null || company.getCode().trim().length() == 0 || company.getMarket().trim().length() == 0 ) return false;
		if(!UtilLibs.validCode(company.getCode())){
			return false;
		}
		
		try{
			Set<CompanyList> tt = new HashSet<CompanyList>();
			tt.add(company);
			String endDate = UtilLibs.GetCurrentTimeWithTMZ(Constants.MS_DT_FMT, Constants.SYS_TZ_VIC);
			String stDate = UtilLibs.GetPreferDay(StaticConfig.CHART_HISTORY_DEFAULT, Constants.MS_DT_FMT, Constants.SYS_TZ_VIC);//default one year request.
			getSharePriceVO(tt);
			getChartHistory(tt,stDate,endDate);
			
			CompanyList ret = WebSocketClient.findRightCompany(company);
			if(ret == null){
				//1 insert into db
				WebSocketClient.upsertCompanyList(company);
				//2 update memory
				WebSocketClient.loadTickerCodeList(false);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String args[]){
		
		createFieldList();
		Hashtable<String, String> ttest = new Hashtable<String, String>();
		ttest.put("a", "aa");
		ttest.put("a", "bb");
		//ttest.put(null, "aaa");
		Set<CompanyList> companylist = new HashSet<CompanyList>();
		CompanyList cmp1 = new CompanyList();
		cmp1.setCode("aa");
		cmp1.setMarket("asx");
		companylist.add(cmp1);
		CompanyList cmp2 = new CompanyList();
		cmp2.setCode("aa");
		cmp2.setMarket("asx");
		companylist.add(cmp2);
		
		System.out.println("--"+companylist.size());

		
//		String responseBody = "{\"ts\":{\"results\":[{\"symbol\":\"VOD\",\"exchangeid\":\"151\",\"type\":\"1\",\"data\":[ {\"D953\":\"04-01-2013\",\"D952\":\"09:51\",\"D16\":\"22520\",\"D17\":\"157.7499\",\"D18\":\"157.7762\",\"D19\":\"157.7499\",\"D2\":\"157.7762\"}, {\"D953\":\"04-01-2013\",\"D952\":\"09:50\",\"D16\":\"11500\",\"D17\":\"157.75\",\"D18\":\"157.75\",\"D19\":\"157.7485\",\"D2\":\"157.7485\"}]}]}}";
//		JSONObject obj = new JSONObject(responseBody);
//		ObjectMapper mapper = new ObjectMapper();
//   	
//		if(obj.has("ts")){
//			JSONObject ts = obj.getJSONObject("ts");
//			if(ts.has("error")){
//	   			JSONArray errorAry = ts.getJSONArray("error");
//	   			if(errorAry != null && errorAry.length() > 0){
//	   				omtlogger.error("Get share price met error response:"+errorAry.getString(0));
//	   			}
//	   		}else{
//	   			if(ts.has("results")){
//	   				JSONArray resultAry = ts.getJSONArray("results");
//	   				MsChartHistory uvo = null;
//	   				MsChartOriginal value = null;
//		    			
//	   				for(Object data: resultAry){
//	   					try {
//							value = mapper.readValue(data.toString(), MsChartOriginal.class);
//		   					if(value != null){
//		   						uvo = new MsChartHistory();
//		   						uvo.setCode(value.getSymbol());
//		   						uvo.setDate(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC));
//		   						uvo.setValue(mapper.writeValueAsString(value.getData()));
//		   						uvo.setCount(value.getData().size());
//		   					}
//						} catch (JsonParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (JsonMappingException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//	   				}
//	   			}
//	   		}
//		}
//
//		
//		
//		String aa = "a";
//		List<String> myList = new ArrayList<String>(Arrays.asList(aa.split(",")));
//		System.out.println("--"+myList.get(0));
//				
//		System.out.println(Integer.toBinaryString(0^255));
//		System.out.println(Integer.toBinaryString(1^255));
//		System.out.println(Integer.toBinaryString(2^255));
//		String ret = "11111111";
//		System.out.println(Integer.parseInt(ret, 2));
//		
//		//getChartHistory("TLS","2016-05-06", "2016-05-11");
//		if(("OK").equalsIgnoreCase("ok")){
//			System.out.println("ok");
//		}
//		List<String> codelist = new ArrayList<String>();
		//getSharePriceVO(codelist);
	}
}
