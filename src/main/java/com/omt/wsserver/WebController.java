package com.omt.wsserver;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omt.webservice.RestUriConstant;

@Controller
@RequestMapping(value=RestUriConstant.WSS_SERVER_PAGE)
public class WebController {
	private Logger omtlogger = Logger.getLogger(WebController.class);
	
	@RequestMapping(value=RestUriConstant.WSS_SERVER_WEB_TOMCAT7 , method = RequestMethod.GET)
	public String all(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	
    	return "tomcat7";
	}
	
	@RequestMapping(value=RestUriConstant.WSS_SERVER_WEB_SPRING , method = RequestMethod.GET)
	public String spring(HttpServletRequest request) {
    	omtlogger.info("Enter into news list...");
    	
    	return "spring";
	}
}
