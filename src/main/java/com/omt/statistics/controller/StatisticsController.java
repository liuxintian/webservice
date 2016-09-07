package com.omt.statistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omt.statistics.dao.StDaoRepImpl;
import com.omt.statistics.entity.StPageShowVO;
import com.omt.statistics.entity.StatisticsVO;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.morningstar.entity.SmaHistory;
import com.omt.webservice.morningstar.entity.SmaPercent;

/**
 * WebpageController
 * WebpageController using for web page to query and operate user messages
 * @author tonyliu 
 */
@Controller
@RequestMapping(value=RestUriConstant.WEB_ST_PAGE)
public class StatisticsController
{
	private Logger omtlogger = Logger.getLogger(StatisticsController.class);
	
	@Autowired
	@Qualifier("daoStatisService")
	public StDaoRepImpl daoStatisService;
	
	/**----------------------------------------------------------------------------------------------------------------------------------
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WEB_ST_PAGE_INDEX , method = RequestMethod.GET)
	public String getuserweb(HttpServletRequest request) {
    	omtlogger.info("Start to getuserweb...");
    	
    	return "statistics";
	}
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_ST_PAGE_QUERYLIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<StPageShowVO> list = daoStatisService.findByPage(dgm);
		
		result.put("total", daoStatisService.totalCount(dgm));
		result.put("rows", list);
    	omtlogger.trace("Result of queryList size:"+list.size());
	    return result;
	}

	/**
     * @Real
     * This method will delete an usermsg by it's msgid value.
     */
    @RequestMapping(value=RestUriConstant.WEB_ST_PAGE_ITEMDETAILS, method=RequestMethod.GET)
	public ModelAndView findDetails(@RequestParam("msgid") String msgid, HttpServletRequest request) {
    	omtlogger.info("Start to finddetails...with id"+msgid);
    	StatisticsVO uvo = new StatisticsVO();
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName("details");
    		model.addObject("usermessage", uvo);
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    		omtlogger.fatal(ex.getMessage());
    	}
    	return model;
	}	
		
	/**-------------------------------------------------------------------------------------------------------------------------
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WEB_ST_PAGE_SMA_HIST , method = RequestMethod.GET)
	public String smahistory(HttpServletRequest request) {
    	omtlogger.info("Start to smahistory...");
    	
    	return "smahistory";
	}
	
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_ST_PAGE_SMA_HIST_QRY, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> smahistlist(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		@SuppressWarnings("unchecked")
		List<SmaHistory> list = (List<SmaHistory>) findByPage(dgm, SmaHistory.class);
		
		result.put("total", totalCount(dgm, SmaHistory.class));
		result.put("rows", list);
    	omtlogger.trace("Result of smahistlist size:"+list.size());
	    return result;
	}
	/**-------------------------------------------------------------------------------------------------------------------------
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */
	@RequestMapping(value=RestUriConstant.WEB_ST_PAGE_SMA_DIFF , method = RequestMethod.GET)
	public String smadiff(HttpServletRequest request) {
    	omtlogger.info("Start to smadiff...");
    	
    	return "smadiff";
	}
	
	/**
     * @Real
	 * Use this to:
	 * webpage query all the message list and return to view
	 * @return
	 */	
	@RequestMapping(value=RestUriConstant.WEB_ST_PAGE_SMA_DIFF_QRY, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> smadifflist(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		@SuppressWarnings("unchecked")
		List<SmaPercent> list = (List<SmaPercent>) findByPage(dgm, SmaPercent.class);
		
		result.put("total", totalCount(dgm, SmaPercent.class));
		result.put("rows", list);
    	omtlogger.trace("Result of smadifflist size:"+list.size());
	    return result;
	}

	/**-------------------------------------------------------------------------------------------------------------------------
	 * 
	 * @param dgm
	 * @param entityClass
	 * @return
	 */
	private List<?> findByPage(DataGridModel dgm, Class<?> entityClass){
		 Query query = createQuery(dgm);
		 query.with(new Sort(Sort.Direction.DESC, "_id"));
		 query.skip((dgm.getPage()-1) * dgm.getRows());
		 query.limit(dgm.getRows());
		 return StaticMongoTemplate.getStaticMongoTemplate().find(query, entityClass);
	}
	private int totalCount(DataGridModel dgm, Class<?> entityClass){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(createQuery(dgm), entityClass);
		return count;
	}
	private Query createQuery(DataGridModel dgm){
		 Query query = new Query();
		 //[{"field":"date","op":"equal","value":"27/04/2016"},{"field":"code","op":"contains","value":"ONT"},{"field":"type","op":"equal","value":"SharePrice"},{"field":"count","op":"equal","value":"3"}]
		 if(dgm != null && dgm.getFilterRules() != null && dgm.getFilterRules().length() > 3){
			 JSONArray dmgary = new JSONArray(dgm.getFilterRules());
			 for(Object obj: dmgary){
				 JSONObject fieldObj = new JSONObject(obj.toString());
				 switch(fieldObj.getString("field")){
				 case "code":
					 query.addCriteria(Criteria.where(fieldObj.getString("field")).regex(fieldObj.getString("value").toUpperCase()));
					 break;
				 case "type":
					 query.addCriteria(Criteria.where(fieldObj.getString("field")).is(fieldObj.getString("value")));
					 break;
				 }
			 }
		 }
		 return query;
	}	

}