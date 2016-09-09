package com.omt.config.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omt.config.StaticConfig;
import com.omt.config.entity.SysConfigVO;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.DataGridModel;
import com.omt.webservice.listener.InitSys;
import com.omt.webservice.morningstar.MsSymbolSynThread;

/**
 * OMT Web Service platform parameters configuration class
 * @author tonyliu
 *
 */

@Controller
@RequestMapping(value=RestUriConstant.WEB_SYS_CFG)
public class SysConfigController {
	private Logger omtlogger = Logger.getLogger(SysConfigController.class);
	
	@RequestMapping(value=RestUriConstant.WEB_SYS_CFG_INDEX , method = RequestMethod.GET)
	public String getuserweb(HttpServletRequest request) {
    	omtlogger.info("Start to syscfg...");
    	return "syscfg";
	}
	
	@RequestMapping(value=RestUriConstant.WEB_SYS_CFG_HELP , method = RequestMethod.GET)
	public String getuserhelp(HttpServletRequest request) {
    	omtlogger.info("Start to get user help...");
    	return "help";
	}

	@RequestMapping(value=RestUriConstant.WEB_SYS_CFG_HELP_PDF_INTERFACE , method = RequestMethod.GET)
	public ResponseEntity<byte[]> getInterface(HttpServletRequest request) {
    	omtlogger.info("Start to getInterface...");
    	String fullpath = null;
	    String filename = "interfaces.pdf";
	    
		if(getClass().getClassLoader().getResource(filename) != null){
			fullpath = getClass().getClassLoader().getResource(filename).getPath();
		}
    	
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    headers.add("content-disposition", "inline;filename=" + filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(UtilLibs.loadFile(fullpath), headers, HttpStatus.OK);
	    return response;
	}
	@RequestMapping(value=RestUriConstant.WEB_SYS_CFG_HELP_PDF_REFERENCE , method = RequestMethod.GET)
	public ResponseEntity<byte[]> getReference(HttpServletRequest request) {
    	omtlogger.info("Start to getReference...");
    	String fullpath = null;
	    String filename = "reference.pdf";
	    
		if(getClass().getClassLoader().getResource(filename) != null){
			fullpath = getClass().getClassLoader().getResource(filename).getPath();
		}

		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    headers.add("content-disposition", "inline;filename=" + filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(UtilLibs.loadFile(fullpath), headers, HttpStatus.OK);
	    return response;
	}
	
	@RequestMapping(value=RestUriConstant.WEB_SYS_CFG_LIST, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<SysConfigVO> list = findByPage(dgm.getPage()-1, dgm.getRows());
		
		result.put("total", totalCount());
		result.put("rows", list);
    	omtlogger.trace("Result of queryList size:"+list.size());
	    return result;
	}

	@RequestMapping(value=RestUriConstant.WEB_SYS_CFG_EDIT, method=RequestMethod.POST)
	@ResponseBody
	public String edit(SysConfigVO uvo) throws Exception{
		String result = "Success";
		SysConfigVO oldDS = null;
		// Datasource switcher
		if(uvo.getCfgName().equals("datasource")){
			oldDS = findOne("datasource");
			updateOne(uvo);
			StaticConfig.updateSysConfig();
			if(oldDS!= null && (!(uvo.getCfgValue().equals(oldDS.getCfgValue())))){
				InitSys.switchDS();
			}
		}
		if(uvo.getCfgName().equals("herokuRestUrl")){
			oldDS = findOne("herokuRestUrl");
			updateOne(uvo);
			StaticConfig.updateSysConfig();
			if(oldDS!= null && (!(uvo.getCfgValue().equals(oldDS.getCfgValue())))){
				InitSys.switchTrigger();
			}
		}
		if(uvo.getCfgName().equals("herokuCallInterval")){
			oldDS = findOne("herokuCallInterval");
			updateOne(uvo);
			StaticConfig.updateSysConfig();
			if(oldDS!= null && (!(uvo.getCfgValue().equals(oldDS.getCfgValue())))){
				InitSys.switchTrigger();
			}
		}
		if(uvo.getCfgName().equals("TIGGER_FIELD_DEFAUTL")){
			oldDS = findOne("TIGGER_FIELD_DEFAUTL");
			updateOne(uvo);
			StaticConfig.updateSysConfig();
			if(oldDS!= null && (!(uvo.getCfgValue().equals(oldDS.getCfgValue())))){
				InitSys.switchTrigger();
			}
		}
		if(uvo.getCfgName().equals("MS_SYMBOL_SYNC_FLAG")){
			oldDS = findOne("MS_SYMBOL_SYNC_FLAG");
			updateOne(uvo);
			StaticConfig.updateSysConfig();
			if (oldDS!= null && (!(uvo.getCfgValue().equals(oldDS.getCfgValue())))){
				MsSymbolSynThread.updateSymbols();
			}
		}
		
		if(uvo.getCfgName().equals("TriggerAllRestUrl")){
			oldDS = findOne("TriggerAllRestUrl");
			updateOne(uvo);
			StaticConfig.updateSysConfig();
			if(oldDS!= null && (!(uvo.getCfgValue().equals(oldDS.getCfgValue())))){
				InitSys.switchAllTrigger();
			}
		}
		if(uvo.getCfgName().equals("TriggerAllCallInterval")){
			oldDS = findOne("TriggerAllCallInterval");
			updateOne(uvo);
			StaticConfig.updateSysConfig();
			if(oldDS!= null && (!(uvo.getCfgValue().equals(oldDS.getCfgValue())))){
				InitSys.switchAllTrigger();
			}
		}
		
		updateOne(uvo);
		StaticConfig.updateSysConfig();

		return result;
	}
	
	public SysConfigVO findOne(String cfgName){
		Query query = new Query();
		query.addCriteria(Criteria.where("cfgName").is(cfgName));
		return StaticMongoTemplate.getStaticMongoTemplate().findOne(query, SysConfigVO.class);
	}	
	public void updateOne(SysConfigVO entity){
		Query query = new Query();
		query.addCriteria(Criteria.where("cfgName").is(entity.getCfgName()));
		
    	Update update = new Update();
    	update.set("cfgValue", entity.getCfgValue());
		
		StaticMongoTemplate.getStaticMongoTemplate().updateFirst(query, update, SysConfigVO.class);
	}
	
	public List<SysConfigVO> findByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		 Query query = new Query();
		 query.skip(pageSize * pageNumber);
		 query.limit(pageSize);
		 
		return StaticMongoTemplate.getStaticMongoTemplate().find(query, SysConfigVO.class);		
	}
	public int totalCount(){
		int count = 0;
		count = (int) StaticMongoTemplate.getStaticMongoTemplate().count(new Query(), SysConfigVO.class);
		return count;
	}	
}
