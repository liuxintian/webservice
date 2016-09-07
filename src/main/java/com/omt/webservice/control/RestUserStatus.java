package com.omt.webservice.control;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omt.webservice.Constants;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.UserStatus;

import org.apache.log4j.Logger;

/**
 * RestfulController
 * Rest APIs using for Mobile & third-party to post,delete and query message
 * @version v0.1
 * @author tony.liu 
 */
@RestController
@RequestMapping(value=RestUriConstant.WEB_REST_STATUS)
public class RestUserStatus
{
	private Logger omtlogger = Logger.getLogger(RestUserStatus.class);
	//
	@RequestMapping(value=RestUriConstant.WEB_REST_SETUSERSTATUS, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE) 
	private @ResponseBody UserStatus setUserStatus( @RequestBody UserStatus uvo, @RequestHeader(value=RestUriConstant.HEADER_REQ_CODE, defaultValue=RestUriConstant.HEADER_REQ_CODE_DEFAULT) String code){
		if(uvo == null || uvo.getEmailid() == null || uvo.getEmailid().trim().length() == 0){
			omtlogger.info("Start to setUserStatus...failed, please check input parameters.");
			return uvo;
		}
		omtlogger.info("Start to setUserStatus...with emailid:" + uvo.getEmailid() + "-- status:"+uvo.getStatus() +"-- code:"+code);
		try{
			if(code != null){
				uvo.setCode(code.toUpperCase());
			}else{
				uvo.setCode("");
			}
			uvo.setTime(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_VIC));
			insertUserStatus(uvo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return uvo;
	}
	public static void insertUserStatus(UserStatus uvo){
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("emailid").is(uvo.getEmailid()));
		query.addCriteria(Criteria.where("code").is(uvo.getCode()));
		
    	Update update = new Update();
		update.set("status", uvo.getStatus());
		update.set("time", uvo.getTime());
		
		if(uvo != null && uvo.getEmailid() != null) StaticMongoTemplate.getStaticMongoTemplate().upsert(query, update, UserStatus.class);
	}

}
