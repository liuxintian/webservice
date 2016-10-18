package com.omt.websocket;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.webservice.Constants;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.MsUtility;
import com.omt.webservice.morningstar.dao.MsDao;
import com.omt.webservice.morningstar.entity.MsChartHistory;
import com.omt.webservice.morningstar.entity.MsSharePrice;
import com.omt.websocket.dao.HistoryDaoRepImpl;
import com.omt.websocket.dao.SocketDaoRepImpl;
import com.omt.websocket.entity.ChartHistoryReq;
import com.omt.websocket.entity.ChartHistoryTypeReq;
import com.omt.websocket.entity.CompanyList;
import com.omt.websocket.entity.SharePriceReq;
import com.omt.webservice.morningstar.entity.ShareDataM;

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
@RequestMapping(value=RestUriConstant.WSS_REST_DIF)
public class MarketRestController {

	private Logger omtlogger = Logger.getLogger(MarketRestController.class);
	
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
	public @ResponseBody List<Object> wsRequest(@RequestBody List<SharePriceReq> inreqList, HttpServletRequest request) throws Exception {
		
		if(inreqList == null || inreqList.size() == 0){
			return null;
		}
		
		List<Object> retlist = new ArrayList<Object>();
		for(SharePriceReq uvo: inreqList){
			omtlogger.info("------request code:"+uvo.getCode() +"--market:"+uvo.getMarket() + "--timezone:"+uvo.getTimezone());
			if(uvo.getCode() == null || uvo.getMarket() == null || uvo.getTimezone() == null){
				continue;
			}
			
			CompanyList company = new CompanyList();
			company.setCode(uvo.getCode().toUpperCase());
			company.setMarket(uvo.getMarket().toUpperCase());
			//---------------------------------------------------------------------
			{
				MsSharePrice message = MsDao.findOneSharePrice(uvo);
				
	    		if(message != null) {
	        		message.getData().setCode(uvo.getCode().toUpperCase());
	        		message.getData().setTimestamp(UtilLibs.ConvertTimeToTMZ(message.getData().getTimestamp(), Constants.SYS_TM_FMT, uvo.getTimezone()));;
	        		retlist.add(message.getData());
	        	}else{
					MsUtility.synchronizeCodeList(company);
	        		
	        		message = new MsSharePrice();
	        		message.setData(new ShareDataM());
	        		message.getData().setCode(uvo.getCode().toUpperCase());
	        		retlist.add(message.getData());
	        	}
			}
    		//---------------------------------------------------------------------
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
	public @ResponseBody String wssreqhistory(@RequestBody ChartHistoryReq ruvo, HttpServletRequest request) {
		omtlogger.info("------wssreqhistory code:"+ruvo.getCode() + "--market:"+ruvo.getMarket()+" --count:"+ ruvo.getCount());
		
		if(ruvo == null || ruvo.getCode() == null || ruvo.getCount() == 0 || ruvo.getMarket() == null){
			omtlogger.error("wssreqhistory met errors, please check the logs.");
			return "";
		}
		String code = ruvo.getCode().toUpperCase();
		Integer count = ruvo.getCount();
		String retstr = "";
		CompanyList company = new CompanyList();
		company.setCode(code.toUpperCase());
		company.setMarket(ruvo.getMarket().toUpperCase());

		//---------------------------------------------------------------------
		String value = "";
		{
			
			MsChartHistory requestvo = new MsChartHistory();
			requestvo.setCode(code.toUpperCase());
			requestvo.setCount(count);
			requestvo.setMarket(ruvo.getMarket().toUpperCase());
			
			MsChartHistory uvo = MsDao.findMarketMsChartHistory(requestvo);
			if(uvo != null) {
				value = uvo.getValue();
			}else{
				MsUtility.synchronizeCodeList(company);
			}
		}
		
		retstr = UtilLibs.createAllItemsForEmpty(value, count, code);
		//---------------------------------------------------------------------

    	return retstr;
    }

	/**
	 * Query message by code and return JSON data result to mobile via /wssreqhistory
	 * @param ruvo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WSS_REST_QUERYCHARTHISTORY_TYPE, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody String charthistory(@RequestBody ChartHistoryTypeReq ruvo, HttpServletRequest request) {
		omtlogger.info("------wssreqhistory code:"+ruvo.getCode() + "--market:"+ruvo.getMarket()+" --count:"+ ruvo.getType());
		
		if(ruvo == null || ruvo.getCode() == null || ruvo.getType().equals("") || ruvo.getMarket() == null){
			omtlogger.error("charthistory request met errors, please check the logs.");
			return "";
		}
		String code = ruvo.getCode().toUpperCase();
		String type = ruvo.getType().toUpperCase();
		String market = ruvo.getMarket().toUpperCase();
		String retstr = "";
		
		CompanyList company = new CompanyList();
		company.setCode(code.toUpperCase());
		company.setMarket(ruvo.getMarket().toUpperCase());

		//---------------------------------------------------------------------
		String value = "";
		{
			
			MsChartHistory requestvo = new MsChartHistory();
			requestvo.setCode(code);
			requestvo.setMarket(market);
			
			MsChartHistory uvo = MsDao.findMarketMsChartHistory(requestvo);
			if(uvo != null) {
				value = uvo.getValue();
			}else{
				MsUtility.synchronizeCodeList(company);
			}
			retstr = UtilLibs.createAllItemsForType(value, type, code);
		}
		
		//---------------------------------------------------------------------

    	return retstr;
    }
	
}
