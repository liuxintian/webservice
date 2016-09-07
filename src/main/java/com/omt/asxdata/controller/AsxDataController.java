package com.omt.asxdata.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.webservice.RestUriConstant;
import com.omt.asxdata.dao.AsxDaoRepImpl;
import com.omt.asxdata.entity.AsxDataVO;

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
@RequestMapping(value=RestUriConstant.WEB_ASX_BASE)
public class AsxDataController {

	private Logger omtlogger = Logger.getLogger(AsxDataController.class);
	
	@Autowired
	@Qualifier("daoAsxDataDao")
	public AsxDaoRepImpl daoAsxDataDao;
	
	/**
	 * Return data to Restful client with Top 5 Gains and Declines Data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WEB_ASX_GET_ALL, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<AsxDataVO> asxGetAll() throws Exception {
		omtlogger.info("start asxGetAll ...");
		List<AsxDataVO> retlist = new ArrayList<AsxDataVO>();
		retlist = daoAsxDataDao.findAll();
		
		if(retlist != null)omtlogger.info("GAINS and DECLINES retlist size:"+retlist.size());
    	return retlist;
    }

	/**
	 * Return data to Restful client with Top 5 Gains Data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WEB_ASX_GET_GAINS, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<AsxDataVO> asxGetGains() throws Exception {
		omtlogger.info("start asxGetGains ...");
		List<AsxDataVO> retlist = new ArrayList<AsxDataVO>();
		retlist = daoAsxDataDao.findByType(AsxWebClient.GAINS);
		if(retlist != null)omtlogger.info("GAINS retlist size:"+retlist.size());
    	return retlist;
    }
	
	/**
	 * Return data to Restful client with Top 5 Declines Data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=RestUriConstant.WEB_ASX_GET_DECLINES, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)     
	public @ResponseBody List<AsxDataVO> asxGetDeclines() throws Exception {
		omtlogger.info("start asxGetDeclines ...");
		List<AsxDataVO> retlist = new ArrayList<AsxDataVO>();
		retlist = daoAsxDataDao.findByType(AsxWebClient.DECLINES);
		if(retlist != null)omtlogger.info("DECLINES retlist size:"+retlist.size());
    	return retlist;
    }
}
