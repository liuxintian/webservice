package com.omt.websocket;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;

import com.neovisionaries.ws.client.PayloadGenerator;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketState;
import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.MsUtility;
import com.omt.websocket.entity.CompanyList;
import com.omt.websocket.entity.HistoryPriceVO;
import com.omt.websocket.entity.NotifyMessage;
import com.omt.websocket.entity.NotifyMessageHistory;
import com.omt.websocket.entity.ShareData;

/**
 * WebSocket Client
 * 1. subscribe price data
 * 2. subscribe chart history data
 * Deal Request 
 * Deal Response
 * Operate Mongodb
 * 
 * @author tonyliu
 *
 */
@Controller
public class WebSocketClient {

	private static Logger omtlogger = Logger.getLogger(WebSocketClient.class);
	
	public static WebSocket websocket;
	public static String access_token;
	public static String refresh_token;
	public static final String DEFAULT_MARKT = "ASX";
	
	public static String SPLIT_STR = ",";
	// This varible can be changed only When load companies.csv in LoadCompaniesCSV.java
	public static String[] COMPANIES = {
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!WBCHA.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"WBCHA\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!WBCHB.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"WBCHB\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!WBCPC.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"WBCPC\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!WBCPD.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"WBCPD\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!WBCPE.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"WBCPE\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!WBCPF.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"WBCPF\"}}",

			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!INC.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"INC\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!OMTO.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"OMTO\"}}",

			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!WBC.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"WBC\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!MBE.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"MBE\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!BHP.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"BHP\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!TLS.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"TLS\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!OMT.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"OMT\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!SUN.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"SUN\"}}",
			"{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!ONT.ASX\", \"TransactionID\":1, \"Data\":{\"Market\":\"ASX\", \"Code\":\"ONT\"}}"
	};
	
	private static int MAX_QUEUE_SIZE = 10000;
	public static Hashtable<String, CompanyList> CODE_COMPANYLIST = new Hashtable<String, CompanyList>();
	public static Hashtable<Integer, String> currentHisPriceHt = new Hashtable<Integer, String>();
	public static ArrayBlockingQueue<String> MESSAGE_QUEUE = new ArrayBlockingQueue<String>(MAX_QUEUE_SIZE);
	public static int received;
	
	public static int retrytimes = 0;
	public static RefreshConnTimerTask refreshTimeTask;
	
	public static void clientRun(){
    	creatWSClient();
    }
	private static void newRefreshTimer(){
		if(refreshTimeTask == null){
			refreshTimeTask = new RefreshConnTimerTask();
			refreshTimeTask.start();
		}
	}
	/**
	 * Use for First time to get Token,RefreshToken from server
	 */
	private static void RestTempGetToken(){
    		AuthorizedRestTemplate restTemplate = new AuthorizedRestTemplate(
    				StaticConfig.wssClientId, 
    				StaticConfig.wssClientPwd, 
    				StaticConfig.wssConnUserName,
    				StaticConfig.wssConnPassword,
    				StaticConfig.wssConnGrantType,
    				StaticConfig.paritechWssScope);
    		
    		String retstr = restTemplate.getForObject(StaticConfig.paritechTokenUrl, "");
    		JSONObject json = new JSONObject(retstr);
    		access_token = json.getString("access_token");
    		refresh_token = json.getString("refresh_token");
    		
    		newRefreshTimer();
    		
    		isRefreshing = false;
    		if(StaticConfig.logswitcher == 2) omtlogger.info("------RestTempGetToken all return str:" + retstr);
	}

	/**
	 * Use for Refreshing Token to server, update token and refresh token locally
	 */
	public static boolean isRefreshing = false;
	public static boolean closedByOwn = false;
	public static void RestTempGetTokenRefresh(){
    		AuthorizedRestTemplate restTemplate = new AuthorizedRestTemplate(
    				StaticConfig.wssClientId, 
    				StaticConfig.wssClientPwd, 
    				StaticConfig.wssConnUserName,
    				StaticConfig.wssConnPassword,
    				StaticConfig.wssConnGrantRefresh,
    				StaticConfig.paritechWssScope);
    		
    		String retstr = restTemplate.getForObjectRefresh(StaticConfig.paritechTokenUrl, refresh_token);
    		JSONObject json = new JSONObject(retstr);
    		access_token = json.getString("access_token");
    		refresh_token = json.getString("refresh_token");
    		
    		isRefreshing = true;
    		if(StaticConfig.logswitcher == 2) omtlogger.info("------RestTempGetTokenRefresh all return str:" + retstr);
	}
	
	/**
	 * Create Websocket client with company
	 * @param company
	 * @return
	 */
	public static void creatWSClient(){
		
		if(websocket != null) return;
		RestTempGetToken();
		
		try{
			websocket = new WebSocketFactory()
			         .createSocket(StaticConfig.paritechWssUrl)
			         .addProtocol(StaticConfig.paritechWssProtocol)
			         .addListener(new WebSocketAdapter() {
			        	 @Override
			        	 //Note that in normal cases, you don't have to call sendClose method and sendPong method (or their variants) explicitly because they are called automatically when appropriate.
			        	 public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
			        		 //omtlogger.info("------onPingFrame---:"+frame);
			        		 //if(frame != null) omtlogger.info("------onPingFrame---:"+frame.getPayloadText());
			        		 websocket.sendPong(frame.getPayloadText());
			        	 }

			        	 @Override
			        	 public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception{
			        		 //omtlogger.info("------onPongFrame---:"+frame);
			        		 //if(frame != null) omtlogger.info("------onPongFrame---:"+frame.getPayloadText());
			        		 //websocket.sendPing((new Date().toString()+"-OmniMarketTide").getBytes());
			        	 }
			        	 
			        	 @Override
			        	 public void onStateChanged(WebSocket websocket, WebSocketState newState){
			        		 omtlogger.info("------WebSocketAdapter---onStateChanged, current status:"+newState);
			        	 }
			        	 
			        	 @Override
			        	 public void onDisconnected(WebSocket websocket,
			        			 WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame,
			        			 boolean closedByServer){
			        		 omtlogger.info("------WebSocketAdapter-onDisconnected--is closedByServer?:"+closedByServer);
			        		 omtlogger.info("------WebSocketAdapter-onDisconnected--current websocket:"+websocket);
			        		 omtlogger.info("------WebSocketAdapter-onDisconnected--current clientCloseFrame:"+clientCloseFrame);
			        		 omtlogger.info("------WebSocketAdapter-onDisconnected--current serverCloseFrame:"+serverCloseFrame);
			        		 if(websocket != null){
			        			 try {
			        				 if(clientCloseFrame != null){
			        					 omtlogger.info("------clientCloseFrame!=null:"+clientCloseFrame.getCloseReason());
			        				 }
			        				 if(serverCloseFrame != null){
			        					 omtlogger.info("------serverCloseFrame!=null:"+serverCloseFrame.getCloseReason());
			        				 }
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			        		 }
		        			 // Reconnect to the server;
			        		 if(!closedByOwn) positivelyReconnecct("Disconnected occurred!");

		        			 omtlogger.info("------WebSocketAdapter-onDisconnected--recreate connection websocket:"+websocket);
			        	 }

			             @Override
			             public void onTextMessage(WebSocket ws, String message) {
			                 // Received a response. Print the received message.
			            	 if(StaticConfig.logswitcher == 1) omtlogger.info("-------message received:"+message);
			            	 
			            	 try{
				            	 MESSAGE_QUEUE.add(message);
				            	 received++ ;
			            	 }catch(IllegalStateException ex){
			            		 omtlogger.warn("MESSAGE_QUEUE is full: reached max size:" +MAX_QUEUE_SIZE +"-- operate MESSAGE_QUEUE.clear()");
				            	 MESSAGE_QUEUE.clear();
				            	 ex.printStackTrace();
			            	 }catch(Exception ex){
				            	 MESSAGE_QUEUE.clear();
				            	 ex.printStackTrace();
			            	 }
			             }
			         })
			         .connect()
			         .sendText(onOpensendString())
			         .setPingInterval(30*1000)
			         .setPingPayloadGenerator(new PayloadGenerator(){
			        	    @Override
			        	    public byte[] generate() {
			        	        // The string representation of the current date.
			        	        return (new Date().toString()+"-OmniMarketTide").getBytes();
			        	    }
			         })
			         ;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		closedByOwn = false;

	}
	
	public static void MessageDealingRun(String message){
   	 	
		if(message == null || message.length() == 0) return;

   	 	JSONObject obj = new JSONObject(message);
        retrytimes = 0;
        
        // a. price data
        if(obj.getString("Controller").equals("Auth")){
            //omtlogger.info("-------result received:"+ obj.getJSONObject("Data").getString("Result"));
       	 if(obj.getJSONObject("Data").getString("Result").equals("Success")){
       		 // start to subscribe, [EXCEPT] send refreshing token.
       		 if(!isRefreshing)
       		 {
       			SendSubscribeTask sendtask = new SendSubscribeTask();
       			 sendtask.start();
       		 }
       		 
       		 if(currentHisPriceHt != null && currentHisPriceHt.size() > 0){
       			 Set<Integer> keys = currentHisPriceHt.keySet();
       			 Iterator<Integer> itr = keys.iterator();
       			 while (itr.hasNext()) { 
       			       int key = itr.next();
       			       String valuestr = currentHisPriceHt.get(key);
       			       itr.remove();
       			       if(valuestr != null && valuestr.length() > 0){
           			       String ret[] = valuestr.split(SPLIT_STR);
           			       try{
           			    	websocket.sendText(onHistorysendString(ret[0], Integer.parseInt(ret[1]), ret[2]));
           			       }catch(Exception ex){
           			    	   omtlogger.error("Met errors when send chart history subscribe request after reconnect.");
           			    	   ex.printStackTrace();
           			       }
       			       }
       			 } 
       		 }

       	 }else{
       		 // token expire will lead to a result of [Reject]
       		 // Here the process can be: 1. close the conn, 2. re-connect to websocket server
       		 positivelyReconnecct("Auth failed!");
       	 }
        }
        
        // b. Price data & chart history data
        if(obj.getString("Controller").equals("Market")){
            // 1. When receive error response, directly return to avoid exceptions.
            if((obj.has("Action")) && "Error".equalsIgnoreCase(obj.getString("Action"))){
           	 // omtlogger.error("-------received error response.");
           	 return;
            }

            if(obj.getString("Topic") != null && obj.getString("Topic").startsWith("Security")){
           	 String content = obj.getString("Topic");
           	 int a = content.indexOf("!");
           	 int b = content.indexOf(".");
           	 String code = content.substring(a+1, b);
           	 String market = content.substring(b+1);
    		
           	 NotifyMessage uvo = new NotifyMessage();
           	 NotifyMessage notifvo = findMarketOne(code,market);
           	 ShareData ssm = organizeData(code, obj, notifvo);
   
           	 uvo.setName(code);
           	 uvo.setData(ssm);
           	 uvo.setMarket(market);
           	 
           	 // more safety
           	 insertWithNo(uvo, notifvo);
           	 
       	 	}
       	 	else if(obj.getString("Topic") != null && obj.getString("Topic").equals("QueryChartHistory")){
       		 int index = obj.getInt("TransactionID");
       		 String valuestr = currentHisPriceHt.get(index);
       		 currentHisPriceHt.remove(index);
       		 
       		 if(valuestr != null && valuestr.length() > 0)
       		 {
       			 String ret[] = valuestr.split(SPLIT_STR);
           		 //omtlogger.info("-------QueryChartHistory with code:"+ret[0]+"--count:"+ret[1] +"--market:"+ret[2]);
           		 if(obj.has("Data")){
               		 JSONArray objdata = obj.getJSONArray("Data");
               		 if(objdata != null){
                   		 //omtlogger.info("-------QueryChartHistory message received Data[] count:"+objdata.length());
               			 //This would occur when the server has no data for special CODE like INC. Therefore, permanently remove this
               			 if(objdata.length() == 0){
               			 	 positivelyReconnecct("Received empty data for chart history!");
               			 	 return;
               			 }

               			 HistoryPriceVO uvo = new HistoryPriceVO();
               			 uvo.setCode(ret[0]);
               			 uvo.setMarket(ret[2]);
               			 uvo.setDate(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_DT_FMT, Constants.SYS_TZ_VIC));
               			 
               			 // Regardless the mode from GlobalConfigs.chartHistoryGetWay when Real count < should count, need to compensate data
               			 String insertvalue = objdata.toString();
               			 if(objdata.length() < Integer.parseInt(ret[1])){
               				 insertvalue = (UtilLibs.createAllItemsForEmpty(objdata.toString(), Integer.parseInt(ret[1]), null));
               			 }
               			 JSONArray insertary = new JSONArray(insertvalue);
               			 uvo.setValue(insertvalue);
               			 uvo.setCount(insertary.length());
               			 
               			 updateInsertChartHistory(uvo);
               		 }
           		 }
       		 }
       	 }
        }
	}
	
	/**
	 * Get the subscribe script from code
	 * @param code
	 * @return
	 */
	public static String onPriceSubscribe(String code, String market){
		StringBuffer strb = new StringBuffer();
		if(market == null || market.trim().length() == 0){
			market = DEFAULT_MARKT;
		}
		strb.append("{\"Controller\":\"Market\", \"Action\":\"Sub\", \"Topic\":\"Security!"+code+"."+market.toUpperCase()+"\", \"TransactionID\":1, \"Data\":{\"Market\":\""+market.toUpperCase()+"\", \"Code\":\""+code+"\"}}");
		return strb.toString();
	}
	/**
	 * Static supply onopen send string
	 * @return
	 */
	public static String onOpensendString(){
		StringBuffer ret = new StringBuffer();
		ret.append(" {\"Controller\":\"Auth\", \"Topic\":\"AuthToken\",\"TransactionID\":1,\"Data\":{\"Provider\":\"Bearer\", \"AccessToken\":\""+access_token+"\"}}");
		//omtlogger.info("-------onOpensendString message sent:"+ret.toString());

		return ret.toString();
	}
	
	public static String onHistorysendString(String code, int count, String market){
		if(market == null || market.trim().length() == 0){
			market = DEFAULT_MARKT;
		}
		
		StringBuffer ret = new StringBuffer();
		String transinfo = String.valueOf(code)+SPLIT_STR+String.valueOf(count)+SPLIT_STR+market;
		int transid = UtilLibs.GetSysRandomNumber();
		
		// there maybe something wrong happened
		if(currentHisPriceHt != null && currentHisPriceHt.size() > 3000){
			currentHisPriceHt.clear();
		}
		
		currentHisPriceHt.put(transid, transinfo);
		//omtlogger.info("-------onHistorysendString currentHisPriceHt current size:"+currentHisPriceHt.size());
		
		// This request for all chart history data
		switch(StaticConfig.charthistoryGetWay){
		case Constants.ALL_DATA:
			ret.append(" {\"Controller\":\"Market\", \"Topic\":\"QueryChartHistory\",\"TransactionID\":"+transid+",\"Data\":{\"Market\":\""+market.toUpperCase()+"\", \"Code\":\"" +code+ "\", \"Period\":\"1.00:00:00\"}}");
			break;
		case Constants.ONE_YEAR_DATA:
			ret.append(" {\"Controller\":\"Market\", \"Topic\":\"QueryChartHistory\",\"TransactionID\":"+transid+",\"Data\":{\"Market\":\""+market.toUpperCase()+"\", \"Code\":\"" +code+ "\", \"Count\":"+365+", \"Period\":\"1.00:00:00\"}}");
			break;
		case Constants.BY_COUNT_DATA:
			ret.append(" {\"Controller\":\"Market\", \"Topic\":\"QueryChartHistory\",\"TransactionID\":"+transid+",\"Data\":{\"Market\":\""+market.toUpperCase()+"\", \"Code\":\"" +code+ "\", \"Count\":"+count+", \"Period\":\"1.00:00:00\"}}");
			break;
		default:
			ret.append(" {\"Controller\":\"Market\", \"Topic\":\"QueryChartHistory\",\"TransactionID\":"+transid+",\"Data\":{\"Market\":\""+market.toUpperCase()+"\", \"Code\":\"" +code+ "\", \"Count\":"+365+", \"Period\":\"1.00:00:00\"}}");
			break;
		}
		if(StaticConfig.logswitcher == 2) omtlogger.info("-------onHistorysendString message sent:"+ret.toString());

		return ret.toString();
	}
	
	/**
	 * If the server returns a Reject result, the client will try to reconnect.
	 */
	public static void positivelyReconnecct(String reason){
		omtlogger.info("-------positivelyReconnecct start with reason:"+reason+" and retrytimes="+retrytimes);
		if(retrytimes > StaticConfig.wssReconnectTimes){
			return;
		}
		closedByOwn = true;
		closeWSClient();
		clientRun();
		retrytimes ++;
	}
	
	/**
	 * Tackle the data structure for business
	 * @param code
	 * @param obj
	 * @return
	 */
	public static ShareData organizeData(String code, JSONObject obj, NotifyMessage notifvo){
		ShareData ssm = new ShareData();
		try{
			JSONObject inobj = obj.getJSONObject("Data");
			ShareData oldobj = new ShareData();
			if(notifvo != null){
				oldobj = notifvo.getData();
			}
			//FIll the code mannually, as it doesnot exists in response data
			ssm.setCode(code);
			// Timestamp should always be in UTC 
			ssm.setTimestamp(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));

			if(inobj.has("Name") && !inobj.isNull("Name")){
				ssm.setName(inobj.getString("Name"));
			}else{
				ssm.setName(oldobj.getName());
			}
			if(inobj.has("Market") && !inobj.isNull("Market")){
				ssm.setMarket(inobj.getString("Market"));
			}else{
				ssm.setMarket(oldobj.getMarket());
			}
			if(inobj.has("Currency") && !inobj.isNull("Currency")){
				ssm.setCurrency(inobj.getString("Currency"));
			}else{
				ssm.setCurrency(oldobj.getCurrency());
			}

			if(inobj.has("BestBid") && !inobj.isNull("BestBid")) {
				ssm.setBestBid(inobj.getDouble("BestBid"));
			}else{
				ssm.setBestBid(oldobj.getBestBid());
			}
			
			if(inobj.has("BestAsk") && !inobj.isNull("BestAsk")) {
				ssm.setBestAsk(inobj.getDouble("BestAsk"));
			}else{
				ssm.setBestAsk(oldobj.getBestAsk());
			}
			//------------------------------------------------------------------
			if(inobj.has("BidCount") && !inobj.isNull("BidCount")) {
				ssm.setBidCount(inobj.getLong("BidCount"));
			}else{
				ssm.setBidCount(oldobj.getBidCount());
			}
			
			if(inobj.has("BidQuantity") && !inobj.isNull("BidQuantity")) {
				ssm.setBidQuantity(inobj.getLong("BidQuantity"));
			}else{
				ssm.setBidQuantity(oldobj.getBidQuantity());
			}
			if(inobj.has("BidUndisclosed") && !inobj.isNull("BidUndisclosed")) {
				ssm.setBidUndisclosed(inobj.getBoolean("BidUndisclosed"));
			}else{
				ssm.setBidUndisclosed(oldobj.isBidUndisclosed());
			}
			if(inobj.has("AskCount") && !inobj.isNull("AskCount")) {
				ssm.setAskCount(inobj.getLong("AskCount"));
			}else{
				ssm.setAskCount(oldobj.getAskCount());
			}
			if(inobj.has("AskQuantity") && !inobj.isNull("AskQuantity")) {
				ssm.setAskQuantity(inobj.getLong("AskQuantity"));
			}else{
				ssm.setAskQuantity(oldobj.getAskQuantity());
			}
			
			if(inobj.has("AskUndisclosed") && !inobj.isNull("AskUndisclosed")) {
				ssm.setAskUndisclosed(inobj.getBoolean("AskUndisclosed"));
			}else{
				ssm.setAskUndisclosed(oldobj.isAskUndisclosed());
			}
			if(inobj.has("High") && !inobj.isNull("High")) {
				ssm.setHigh(inobj.getDouble("High"));
			}else{
				ssm.setHigh(oldobj.getHigh());
			}
			if(inobj.has("Low") && !inobj.isNull("Low")) {
				ssm.setLow(inobj.getDouble("Low"));
			}else{
				ssm.setLow(oldobj.getLow());
			}
			if(inobj.has("Open") && !inobj.isNull("Open")) {
				ssm.setOpen(inobj.getDouble("Open"));
			}else{
				ssm.setOpen(oldobj.getOpen());
			}
			if(inobj.has("Close") && !inobj.isNull("Close")) {
				ssm.setClose(inobj.getDouble("Close"));
			}else{
				ssm.setClose(oldobj.getClose());
			}
			if(inobj.has("Settlement") && !inobj.isNull("Settlement")) {
				ssm.setSettlement(inobj.getDouble("Settlement"));
			}else{
				ssm.setSettlement(oldobj.getSettlement());
			}
			if(inobj.has("Last") && !inobj.isNull("Last")) {
				ssm.setLast(inobj.getDouble("Last"));
			}else{
				ssm.setLast(oldobj.getLast());
			}
			
			if(inobj.has("NumberOfTrades") && !inobj.isNull("NumberOfTrades")) {
				ssm.setNumberOfTrades(inobj.getLong("NumberOfTrades"));
			}else{
				ssm.setNumberOfTrades(oldobj.getNumberOfTrades());
			}
			if(inobj.has("Volume") && !inobj.isNull("Volume")) {
				ssm.setVolume(inobj.getLong("Volume"));
			}else{
				ssm.setVolume(oldobj.getVolume());
			}
			if(inobj.has("Trend") && !inobj.isNull("Trend")) {
				ssm.setTrend(inobj.getString("Trend"));
			}else{
				ssm.setTrend(oldobj.getTrend());
			}
			if(inobj.has("Clazz") && !inobj.isNull("Clazz")) {
				ssm.setClazz(inobj.getString("Clazz"));
			}else{
				ssm.setClazz(oldobj.getClazz());
			}
			if(inobj.has("Index") && !inobj.isNull("Index")) {
				ssm.setIndex(inobj.getBoolean("Index"));
			}else{
				ssm.setIndex(oldobj.isIndex());
			}
			if(inobj.has("TradingState") && !inobj.isNull("TradingState")) {
				ssm.setTradingState(inobj.getString("TradingState"));
			}else{
				ssm.setTradingState(oldobj.getTradingState());
			}
			
			if(inobj.has("StrikePrice") && !inobj.isNull("StrikePrice")) {
				ssm.setStrikePrice(inobj.getDouble("StrikePrice"));
			}else{
				ssm.setStrikePrice(oldobj.getStrikePrice());
			}
			if(inobj.has("CallOrPut") && !inobj.isNull("CallOrPut")) {
				ssm.setCallOrPut(inobj.getString("CallOrPut"));
			}else{
				ssm.setCallOrPut(oldobj.getCallOrPut());
			}
			if(inobj.has("ContractSize") && !inobj.isNull("ContractSize")) {
				ssm.setContractSize(inobj.getLong("ContractSize"));
			}else{
				ssm.setContractSize(oldobj.getContractSize());
			}
			if(inobj.has("SubscriptionData") && !inobj.isNull("SubscriptionData")) {
				ssm.setSubscriptionData(inobj.getString("SubscriptionData"));
			}else{
				ssm.setSubscriptionData(oldobj.getSubscriptionData());
			}
			if(inobj.has("AuctionPrice") && !inobj.isNull("AuctionPrice")) {
				ssm.setAuctionPrice(inobj.getDouble("AuctionPrice"));
			}else{
				ssm.setAuctionPrice(oldobj.getAuctionPrice());
			}
			
			if(inobj.has("AuctionQuantity") && !inobj.isNull("AuctionQuantity")) {
				ssm.setAuctionQuantity(inobj.getLong("AuctionQuantity"));
			}else{
				ssm.setAuctionQuantity(oldobj.getAuctionQuantity());
			}
			if(inobj.has("AuctionRemainder") && !inobj.isNull("AuctionRemainder")) {
				ssm.setAuctionRemainder(inobj.getLong("AuctionRemainder"));
			}else{
				ssm.setAuctionRemainder(oldobj.getAuctionRemainder());
			}
			if(inobj.has("VWAP") && !inobj.isNull("VWAP")) {
				ssm.setVWAP(inobj.getDouble("VWAP"));
			}else{
				ssm.setVWAP(oldobj.getVWAP());
			}
			if(inobj.has("OpenInterest") && !inobj.isNull("OpenInterest")) {
				ssm.setOpenInterest(inobj.getLong("OpenInterest"));
			}else{
				ssm.setOpenInterest(oldobj.getOpenInterest());
			}
			if(inobj.has("ShareIssue") && !inobj.isNull("ShareIssue")) {
				ssm.setShareIssue(inobj.getLong("ShareIssue"));
			}else{
				ssm.setShareIssue(oldobj.getShareIssue());
			}
			// close should not be null
			if(ssm.getLast() == null && ssm.getClose() != null){
				ssm.setLast(ssm.getClose());
			}
			// Business logic: calculate the ValueChanged
			if(ssm.getLast() != null && ssm.getClose() != null){
				ssm.setValueChanged(ssm.getLast() - ssm.getClose());
			}
			
			// Business logic: calculate the Percentage Change
			if(ssm.getClose() != null && ssm.getClose() != 0 && ssm.getLast() != null){
				ssm.setPercentChange((ssm.getLast() - ssm.getClose())*100/ssm.getClose());
			}
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return ssm;
	}
	
	/**
	 * close the ws client
	 * @param ws
	 */
	public static void closeWSClient(){
		try{
			if(WebSocketClient.refreshTimeTask != null) {
				WebSocketClient.refreshTimeTask.stop();
				WebSocketClient.refreshTimeTask = null;
			}
			
			if(WebSocketClient.websocket != null){
				WebSocketClient.websocket.sendClose();

				WebSocketClient.websocket.clearHeaders();
				WebSocketClient.websocket.clearProtocols();
				WebSocketClient.websocket.clearUserInfo();
				WebSocketClient.websocket.clearExtensions();
				WebSocketClient.websocket.clearListeners();
				
				WebSocketClient.websocket.disconnect();
				WebSocketClient.websocket = null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void insertWithNo(NotifyMessage message, NotifyMessage current){
		
    	if(current == null)
    	{
    		insertSharePrice(message);
    	}
    	else
    	{
    		updateInsertSharePrice(message);
    		
    		// copy the existed one to history table
    		NotifyMessageHistory uvo = new NotifyMessageHistory();
    		uvo.setId(current.getId());
    		uvo.setDatetime(current.getDatetime());
    		uvo.setName(current.getName());
    		uvo.setData(current.getData());
    		
    		updateInsertSharePriceHistory(uvo);
    	}
	}
	
	private static void updateInsertSharePrice(NotifyMessage entity) {
		// TODO Auto-generated method stub
    	Update update = new Update();
		update.set("data", entity.getData());
		update.set("datetime", UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(entity.getName()));
		query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		
		StaticMongoTemplate.getStaticMongoTemplate().upsert(query, update, NotifyMessage.class);
	}
	private static void insertSharePrice(NotifyMessage entity) {
		// TODO Auto-generated method stub
		StaticMongoTemplate.getStaticMongoTemplate().insert(entity);   
	}

	private static NotifyMessage findMarketOne(String code, String market) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(code));
		query.addCriteria(Criteria.where("market").is(market));
		return (NotifyMessage) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, NotifyMessage.class);   
	}
	
	private static void updateInsertSharePriceHistory(NotifyMessageHistory entity){
		// TODO Auto-generated method stub
		
    	Update update = new Update();
		update.set("data", entity.getData());
		update.set("datetime", UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_UTC));
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(entity.getName()));
		query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		
		StaticMongoTemplate.getStaticMongoTemplate().upsert(query, update, NotifyMessageHistory.class);
	}	
	//--------------------------------------------------------------------------------------------------
	private static void updateInsertChartHistory(HistoryPriceVO entity){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(entity.getCode()));
		query.addCriteria(Criteria.where("date").is(entity.getDate()));
		query.addCriteria(Criteria.where("count").is(entity.getCount()));
		query.addCriteria(Criteria.where("market").is(entity.getMarket()));
		
		// omtlogger.info("----upate name:"+entity.getName() +"----data:"+entity.getData());
    	Update update = new Update();
		update.set("value", entity.getValue());
		
		StaticMongoTemplate.getStaticMongoTemplate().upsert(query , update, HistoryPriceVO.class);
	}

	//--------------------------------------------------------------------------------------------------
	// Load default code list from history request db
	public static void loadTickerCodeList(boolean init){
 		try {
			Map<String, String> maps = new HashMap<String, String>();
			List<CompanyList> ret = loadCompanyListFromDB();
			if(ret != null && ret.size() > 0){
				for(CompanyList company: ret){
					if((company != null)  &&  (company.getCode() != null) && (company.getCode().trim().length() > 0)){
						maps.put(company.getCode(),company.getMarket());
						
						// For MorningStar
						{
							CODE_COMPANYLIST.put(company.getCode(), company);
						}
					}
				}
			}

			// Add other companies for Project using
			CompanyList uvo = null;
			for(final String company: WebSocketClient.COMPANIES){
				try{
					JSONObject inobj = new JSONObject(company);
		   			String code = inobj.getJSONObject("Data").getString("Code");
		   			String market = inobj.getJSONObject("Data").getString("Market");
		   			maps.put(code, market);
		   			
					// For MorningStar
		   			uvo = new CompanyList();
		   			uvo.setCode(code);
		   			uvo.setMarket(market);
		   			CODE_COMPANYLIST.put(code, uvo);
				}catch(Exception exj){
					exj.printStackTrace();
					continue;
				}
			}
			
			// For MorningStar
			if(init){
				MsUtility.CODES_QUEUE.clear();
				for (Map.Entry<String, CompanyList> entry : CODE_COMPANYLIST.entrySet()) {
					MsUtility.CODES_QUEUE.add(entry.getValue());
				}
			}

			String[] newcompanies = new String[maps.size()];
			int index = 0;
			for (Map.Entry<String, String> entry : maps.entrySet()) {
				//System.out.println("company [code= " + entry.getKey() + " , name="+ entry.getValue() + "]");
				newcompanies[index] = WebSocketClient.onPriceSubscribe(entry.getKey().toUpperCase().trim(), entry.getValue().toUpperCase().trim());
				index ++;
			}
			WebSocketClient.COMPANIES = newcompanies;
			
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void synchronizeCompanyList(CompanyList company){
		if(company == null || company.getCode().trim().length() == 0) return;
		if(WebSocketClient.websocket != null){
			//a send subscription request
			WebSocketClient.websocket.sendText(WebSocketClient.onPriceSubscribe(company.getCode().toUpperCase().trim(), company.getMarket().toUpperCase()));
			//b send chart history request
			WebSocketClient.websocket.sendText(WebSocketClient.onHistorysendString(company.getCode().toUpperCase().trim(), 365, company.getMarket().toUpperCase()));
		}else{
			WebSocketClient.positivelyReconnecct("synchronizeCompanyList met error: websocket is null.");
		}

		CompanyList ret = findRightCompany(company);
		if(ret == null){
			//1 insert into db
			upsertCompanyList(company);
			//2 update memory
			loadTickerCodeList(false);
		}
	}
	
	public static void upsertCompanyList(CompanyList company){
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(company.getCode()));
		query.addCriteria(Criteria.where("market").is(company.getMarket()));
		
    	Update update = new Update();
		update.set("code", company.getCode());
		
		StaticMongoTemplate.getStaticMongoTemplate().upsert(query , update, CompanyList.class);
	}
	
	public static CompanyList findRightCompany(CompanyList company){
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(company.getCode()));
		query.addCriteria(Criteria.where("market").is(company.getMarket()));
		
		return (CompanyList) StaticMongoTemplate.getStaticMongoTemplate().findOne(query, CompanyList.class);   
	}
	
	public static List<CompanyList> loadCompanyListFromDB(){
		return StaticMongoTemplate.getStaticMongoTemplate().findAll(CompanyList.class);
	}

}
