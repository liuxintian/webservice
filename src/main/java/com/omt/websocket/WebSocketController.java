package com.omt.websocket;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.config.StaticConfig;
import com.omt.statistics.controller.StatisticsUtil;
import com.omt.webservice.Constants;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.MsUtility;
import com.omt.webservice.morningstar.dao.MsDao;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.websocket.dao.HistoryDaoRepImpl;
import com.omt.websocket.dao.SocketDaoRepImpl;
import com.omt.websocket.entity.ChartHistoryVO;
import com.omt.websocket.entity.CompanyList;
import com.omt.websocket.entity.HistRequestVO;
import com.omt.websocket.entity.PriceRequestVO;
import com.omt.websocket.entity.light.LightCHCode;
import com.omt.websocket.entity.light.LightCHRequest;
import com.omt.websocket.entity.light.LightCHResponse;
import com.omt.websocket.entity.light.LightLastClose;
import com.omt.webservice.morningstar.entity.ShareDataM;
import com.omt.webservice.morningstar.entity.SmaResult;

/**
 * Deal RESTFul Request 
 * from companies
 * And Response
 * to: mobile via Restful
 * 
 * @author tonyliu
 *
 */
@RestController
@RequestMapping(value=RestUriConstant.WSS_REST)
public class WebSocketController {

	private Logger omtlogger = Logger.getLogger(WebSocketController.class);
	
	@Autowired
	@Qualifier("daoServiceSocket")
	public SocketDaoRepImpl daoServiceSocket;
	
	@Autowired
	@Qualifier("daoServiceHistory")
	public HistoryDaoRepImpl daoServiceHistory;
	
	/**
	 * Query message by code and return JSON data result to mobile via /wssrequestcode
	 * @param inreqList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_QUERYPRICE, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<Object> wsRequest(@RequestBody List<PriceRequestVO> inreqList, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(inreqList == null || inreqList.size() == 0){
			return null;
		}
		
		List<Object> retlist = new ArrayList<Object>();
		for(PriceRequestVO uvo: inreqList){
			CompanyList company = new CompanyList();
			company.setCode(uvo.getCode().toUpperCase());
			company.setMarket(WebSocketClient.DEFAULT_MARKT);
			if(StaticConfig.logswitcher == 4){
				if(uvo.getCode().toUpperCase().equals("AZJ")){
					response.reset();
					response.setStatus(Constants.HTTP_STATUS_420);
					response.sendError(Constants.HTTP_STATUS_420, Constants.ERROR_INFO_420);
					return null; 
				}
			} 
			omtlogger.info("------Share price request with code:"+uvo.getCode());
			
			//---------------------------------------------------------------------
				MsSharePrice message = MsDao.findOneSharePrice(uvo.getCode().toUpperCase());
				
	    		if(message != null) {
	        		message.getData().setCode(uvo.getCode().toUpperCase());
	        		message.getData().setTimestamp(UtilLibs.ConvertTimeToTMZ(message.getData().getTimestamp(), Constants.SYS_TM_FMT, uvo.getTimezone()));;
	        		retlist.add(message.getData());
	        	}else{
					if(MsUtility.synchronizeCodeList(company)){
		    			try{
		    				int count = 0;
		    				while(count < StaticConfig.NO_DATA_API_TIMEOUT){
			    				Thread.sleep(1000);
			    				message = MsDao.findOneSharePrice(uvo.getCode().toUpperCase());
			    				if(message != null){
			    					break;
			    				}
		    					count ++;
		    				}
		    			}catch(Exception ex){
		    				ex.printStackTrace();
		    			}
			    		if(message != null) {
			        		message.getData().setCode(uvo.getCode().toUpperCase());
			        		message.getData().setTimestamp(UtilLibs.ConvertTimeToTMZ(message.getData().getTimestamp(), Constants.SYS_TM_FMT, uvo.getTimezone()));;
			        		retlist.add(message.getData());
			    		}else{
			        		message = new MsSharePrice();
			        		message.setData(new ShareDataM());
			        		message.getData().setCode(uvo.getCode().toUpperCase());
			        		retlist.add(message.getData());
			    		}
					}
	        	}
       	
    		// --- Log the statistics info START
    		StatisticsUtil.statistics(RestUriConstant.STATIC_SHARE_PRICE_API, 
    				uvo.getCode().toUpperCase(),
    				"Request with inreqList size:"+inreqList.size(), 
    				request);
    		// --- Log the statistics info END
		}

    	return retlist;
    }

	/**
	 * Query message by code and return JSON data result to mobile via /wssreqhistory
	 * @param ruvo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_QUERYCHARTHISTORY_COUNT, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody String wssreqhistory(@RequestBody HistRequestVO ruvo, HttpServletRequest request, HttpServletResponse response) {
		omtlogger.info("------Chart history request code:"+ruvo.getCode() +" and count:"+ ruvo.getCount());
		
		if(ruvo == null || ruvo.getCode() == null || ruvo.getCount() == 0){
			omtlogger.error("wssreqhistory met errors, please check the logs.");
			return "";
		}
		String code = ruvo.getCode().toUpperCase();
		Integer count = ruvo.getCount();
		String retstr = "";
		
		CompanyList company = new CompanyList();
		company.setCode(code.toUpperCase());
		company.setMarket(WebSocketClient.DEFAULT_MARKT);

		//---------------------------------------------------------------------
		String value = "";
			
			MsChartHistory requestvo = new MsChartHistory();
			requestvo.setCode(code);
			requestvo.setCount(count);
			
			MsChartHistory uvo = MsDao.findOneMsChartHistory(requestvo);
			if(uvo != null) {
				value = uvo.getValue();
			}else{
				if(MsUtility.synchronizeCodeList(company)){
	    			try{
	    				
	    				int second = 0;
	    				while(second < StaticConfig.NO_DATA_API_TIMEOUT){
		    				Thread.sleep(1000);
		    				uvo = MsDao.findOneMsChartHistory(requestvo);
		    				if(uvo != null){
		    					break;
		    				}
		    				second ++;
	    				}

	    				if(uvo != null){
	    					value = uvo.getValue();
	    				}else{
	    	    			if(StaticConfig.logswitcher == 3){
	    						response.sendError(Constants.HTTP_STATUS_420, Constants.ERROR_INFO_420);
	    						return null;
	    	    			}
	    				}
	    			}catch(Exception ex){
	    				ex.printStackTrace();
	    			}

				}
			}
		retstr = UtilLibs.createAllItemsForEmpty(value, count, code);
		//---------------------------------------------------------------------
		
		// --- Log the statistics info START
		StatisticsUtil.statistics(RestUriConstant.STATIC_CHART_HISTORY_API, 
				code,
				"Request with code:"+ruvo.getCode() +" and count:"+ ruvo.getCount(), 
				request);
		// --- Log the statistics info END

    	return retstr;
    }

	/**
	 * Query message by code and return JSON data result to mobile via /wssreqhistory
	 * @param ruvo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_QUERYCHARTHISTORY_TYPE, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody String charthistory(@RequestBody ChartHistoryVO ruvo, HttpServletRequest request, HttpServletResponse response) {
		omtlogger.info("------New charthistory request with code:"+ruvo.getCode() +" and type:"+ ruvo.getType());
		
		if(ruvo == null || ruvo.getCode() == null || ruvo.getType().equals("")){
			omtlogger.error("charthistory request met errors, please check the logs.");
			return "";
		}
		String code = ruvo.getCode().toUpperCase();
		String type = ruvo.getType().toUpperCase();
		String retstr = "";
		
		CompanyList company = new CompanyList();
		company.setCode(code.toUpperCase());
		company.setMarket(WebSocketClient.DEFAULT_MARKT);

		//---------------------------------------------------------------------
		String value = "";
			
			MsChartHistory requestvo = new MsChartHistory();
			requestvo.setCode(code);
			
			MsChartHistory uvo = MsDao.findOneMsChartHistory(requestvo);
			if(uvo != null) {
				value = uvo.getValue();
			}else{
				if(MsUtility.synchronizeCodeList(company)){
	    			try{
	    				int second = 0;
	    				while(second < StaticConfig.NO_DATA_API_TIMEOUT){
		    				Thread.sleep(1000);
		    				uvo = MsDao.findOneMsChartHistory(requestvo);
		    				if(uvo != null){
		    					break;
		    				}
		    				second ++;
	    				}

	    				if(uvo != null){
	    					value = uvo.getValue();
	    				}else{
	    	    			if(StaticConfig.logswitcher == 3){
	    						response.sendError(Constants.HTTP_STATUS_420, Constants.ERROR_INFO_420);
	    						return null;
	    	    			}
	    				}
	    			}catch(Exception ex){
	    				ex.printStackTrace();
	    			}
				}
			}
			retstr = UtilLibs.createAllItemsForType(value, type, code);
		
		//---------------------------------------------------------------------
		// --- Log the statistics info START
		StatisticsUtil.statistics(RestUriConstant.STATIC_CHART_HISTORY_NEWAPI, 
				code,
				"Request with code:"+ruvo.getCode() +" and type:"+ ruvo.getType(), 
				request);
		// --- Log the statistics info END
    	return retstr;
    }
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * SMA Request
	 * @param ruvo
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_CHARTHIST_SMA, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody String sma(@RequestBody ChartHistoryVO ruvo, HttpServletRequest request) {
		omtlogger.info("------New sma request with code:"+ruvo.getCode() +" and type:"+ ruvo.getType());
		
		if(ruvo == null || ruvo.getCode() == null || ruvo.getType().equals("")){
			omtlogger.error("charthistory request met errors, please check the logs.");
			return "";
		}
		String code = ruvo.getCode().toUpperCase();
		String type = ruvo.getType().toUpperCase();
		String retstr = "";
		
		CompanyList company = new CompanyList();
		company.setCode(code.toUpperCase());
		company.setMarket(WebSocketClient.DEFAULT_MARKT);

		String value = "";
			
			MsChartHistory requestvo = new MsChartHistory();
			requestvo.setCode(code);
			
			MsChartHistory uvo = MsDao.findOneMsChartHistory(requestvo);
			if(uvo != null) {
				value = uvo.getValue();
			}else{
				MsUtility.synchronizeCodeList(company);
			}
			retstr = UtilLibs.createSMAForType(value, type);
		
    	return retstr;
    }
	/**
	 * SMA Request list
	 * @param ruvo
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_CHARTHIST_SMALIST, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<String> sma(@RequestBody List<ChartHistoryVO> list, HttpServletRequest request) {
		omtlogger.info("------New sma request with list:"+list);
		
		List<String> ret = new ArrayList<String>();
		if(list == null || list.size() == 0){
			omtlogger.error("charthistory request met errors, please check the logs.");
			return ret;
		}
		for(ChartHistoryVO ruvo: list){
			
			String code = ruvo.getCode().toUpperCase();
			String type = ruvo.getType().toUpperCase();
			String retstr = "";
			
			CompanyList company = new CompanyList();
			company.setCode(code.toUpperCase());
			company.setMarket(WebSocketClient.DEFAULT_MARKT);
	
			String value = "";
				
				MsChartHistory requestvo = new MsChartHistory();
				requestvo.setCode(code);
				
				MsChartHistory uvo = MsDao.findOneMsChartHistory(requestvo);
				if(uvo != null) {
					value = uvo.getValue();
				}else{
					MsUtility.synchronizeCodeList(company);
				}
				retstr = UtilLibs.createSMAForType(value, type);
				ret.add(retstr);
		}
    	return ret;
    }

	@RequestMapping(value=RestUriConstant.WSS_REST_CHARTHIST_SMA_DIFF, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody String smadiff(@RequestBody ChartHistoryVO ruvo, HttpServletRequest request) {
		omtlogger.info("------New smadiff request with code:"+ruvo.getCode() +" and type:"+ ruvo.getType());
		
		if(ruvo == null || ruvo.getCode() == null || ruvo.getType().equals("")){
			omtlogger.error("charthistory request met errors, please check the logs.");
			return "";
		}
		String code = ruvo.getCode().toUpperCase();
		String type = ruvo.getType().toUpperCase();
		String retstr = "";
		
		CompanyList company = new CompanyList();
		company.setCode(code.toUpperCase());
		company.setMarket(WebSocketClient.DEFAULT_MARKT);
		
		SmaResult today = new SmaResult();
		String value = "";
			MsSharePrice message = MsDao.findOneSharePrice(code.toUpperCase());
			if(message != null){
				today.setClose(message.getData().getClose());
				today.setOpen(message.getData().getOpen());
				today.setLow(message.getData().getLow());
				today.setHigh(message.getData().getHigh());
				today.setVolume(message.getData().getVolume().doubleValue());
			}
			
			MsChartHistory requestvo = new MsChartHistory();
			requestvo.setCode(code);
			
			MsChartHistory uvo = MsDao.findOneMsChartHistory(requestvo);
			if(uvo != null) {
				value = uvo.getValue();
			}else{
				MsUtility.synchronizeCodeList(company);
			}
			retstr = UtilLibs.createSMADiffForType(value, type, today);

    	return retstr;
    }

	
	@RequestMapping(value=RestUriConstant.WSS_REST_CHARTHIST_SMALIST_DIFF, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<String> smadiffList(@RequestBody List<ChartHistoryVO> list, HttpServletRequest request) {
		omtlogger.info("------New smadiffList request with list:"+list);
		List<String> ret = new ArrayList<String>();
		if(list == null || list.size() == 0){
			omtlogger.error("charthistory request met errors, please check the logs.");
			return ret;
		}
		
		String retstr = "";
		for(ChartHistoryVO ruvo: list){
			
			String code = ruvo.getCode().toUpperCase();
			String type = ruvo.getType().toUpperCase();
			
			CompanyList company = new CompanyList();
			company.setCode(code.toUpperCase());
			company.setMarket(WebSocketClient.DEFAULT_MARKT);
			
			SmaResult today = new SmaResult();
			String value = "";
				MsSharePrice message = MsDao.findOneSharePrice(code.toUpperCase());
				if(message != null){
					today.setClose(message.getData().getClose());
					today.setOpen(message.getData().getOpen());
					today.setLow(message.getData().getLow());
					today.setHigh(message.getData().getHigh());
					today.setVolume(message.getData().getVolume().doubleValue());
				}
				
				MsChartHistory requestvo = new MsChartHistory();
				requestvo.setCode(code);
				
				MsChartHistory uvo = MsDao.findOneMsChartHistory(requestvo);
				if(uvo != null) {
					value = uvo.getValue();
				}else{
					MsUtility.synchronizeCodeList(company);
				}
				retstr = UtilLibs.createSMADiffForType(value, type, today);
				ret.add(retstr);
		}

    	return ret;
    }
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Query from mobile side with an array of company list with according exchange
	 * Response with an array of chart history of the according companies
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_CHARTHIST_CHLIST, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<LightCHResponse> chartlist(@RequestBody LightCHRequest request, HttpServletResponse response) {
		List<LightCHResponse> ret = new ArrayList<LightCHResponse>();
		if(request == null || request.getCodelist() == null || request.getType() == null){
			omtlogger.error("chartlist request met errors, please check the logs.");
			return ret;
		}
		omtlogger.info("------chartlist request with list size:"+request.getCodelist().size() +"--type:"+request.getType());
		String type = request.getType().toUpperCase();
		LightCHResponse resuvo = new LightCHResponse();
		
		for(LightCHCode ruvo: request.getCodelist()){
			resuvo = new LightCHResponse();
			String code = ruvo.getCode().toUpperCase();
			resuvo.setCode(code);
			
			CompanyList company = new CompanyList();
			company.setCode(code.toUpperCase());
			if(ruvo.getExchange()==null || ruvo.getExchange().trim().equals("")){
				company.setMarket(WebSocketClient.DEFAULT_MARKT);
			}else{
				company.setMarket(ruvo.getExchange());
			}
			resuvo.setExchange(company.getMarket());
			
			String value = "";
				MsChartHistory requestvo = new MsChartHistory();
				requestvo.setCode(code);
				
				MsChartHistory uvo = MsDao.findOneMsChartHistory(requestvo);
				if(uvo != null) {
					value = uvo.getValue();
				}else{
					if(MsUtility.synchronizeCodeList(company)){
		    			try{
		    				int second = 0;
		    				while(second < StaticConfig.NO_DATA_API_TIMEOUT){
			    				Thread.sleep(1000);
			    				uvo = MsDao.findOneMsChartHistory(requestvo);
			    				if(uvo != null){
			    					break;
			    				}
			    				second ++;
		    				}

		    				if(uvo != null){
		    					value = uvo.getValue();
		    				}else{
		    	    			if(StaticConfig.logswitcher == 3){
		    						response.sendError(Constants.HTTP_STATUS_420, Constants.ERROR_INFO_420);
		    						return null;
		    	    			}
		    				}
		    			}catch(Exception ex){
		    				ex.printStackTrace();
		    			}
					}
				}
				
				resuvo.setValue(UtilLibs.createAllItemsForArray(value, type));
				ret.add(resuvo);
			}

    	return ret;
    }
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value=RestUriConstant.WSS_REST_ALL_LASTCLOSE, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<LightLastClose> allLastCloselist(HttpServletResponse response) {
		omtlogger.info("----Receive allclose request...");
		List<LightLastClose> ret = StaticMongoTemplate.getStaticMongoTemplate().findAll(LightLastClose.class);
		if(ret == null){
			ret = new ArrayList<LightLastClose>();
			omtlogger.warn("----Query result from Database is null!");
		}else{
			omtlogger.info("----Response with result size:" + ret.size());
		}
    	return ret;
    }
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
}
