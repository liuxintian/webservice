package com.omt.webservice.control;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.config.StaticConfig;
import com.omt.webservice.Constants;
import com.omt.webservice.OmtBasicClass;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.UserMessage;

import org.apache.log4j.Logger;

/**
 * RestfulController
 * Rest APIs using for Mobile & third-party to post,delete and query message
 * @version v0.1
 * @author tony.liu 
 */
@RestController
@RequestMapping(value=RestUriConstant.WEB_REST)
public class RestfulController extends OmtBasicClass
{
	private Logger omtlogger = Logger.getLogger(RestfulController.class);
	
    /**
     * Use this to:
     * Create usermessage and store into mongodb
     * @param name
     * @param age
     * @param notes
     * @return inserted object
     */
	@RequestMapping(value=RestUriConstant.WEB_REST_CREATEUSER, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody UserMessage creatusermsg(@RequestBody UserMessage entity, @RequestHeader(value=RestUriConstant.HEADER_REQ_CODE, defaultValue=RestUriConstant.HEADER_REQ_CODE_DEFAULT) String code) {
    	omtlogger.info("Start to creatusermsg with code:"+code+"|name:"+ entity.getName() + "|email:"+entity.getEmail()+"|datetime:"+entity.getDatetime()+"|notes:"+entity.getNotes() +"|vac:"+entity.getVac());
    	if(!UtilLibs.validQuestion(entity)){
    		omtlogger.error("Get an error while creating question: invalid input.");
    		return entity;
    	}
    	if(code == null || code.trim().length() == 0){
    		omtlogger.warn("Create question get null code in Request Header with mode MS_DATA_SWITCHER: "+ StaticConfig.MS_DATA_SWITCHER);
    	}
    	
    	try{
        	setCode(code.toUpperCase());
        	entity.setId(null);
        	entity.setCode(code.toUpperCase());
        	entity.setDatetime(entity.getDatetime());
        	entity.setStatus(Constants.MSG_STATUS_NORMAL);
        	
        	insert(entity);
    	}catch(Exception ex){
    		ex.printStackTrace();
    		omtlogger.fatal(ex.getMessage());
    	}
    	
    	omtlogger.info("Creatusermsg result success with name:"+entity.getName());
        return entity;
    }
	
	//----------------------------------------------------------------------------------------------------------
}
