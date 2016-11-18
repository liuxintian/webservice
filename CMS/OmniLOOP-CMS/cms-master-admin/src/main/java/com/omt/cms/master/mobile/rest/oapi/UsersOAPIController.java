package com.omt.cms.master.mobile.rest.oapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.user.service.UserService;
import com.omt.cms.user.service.bo.UserBO;

@RestController
@RequestMapping("/master/oapi/users")
public class UsersOAPIController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersOAPIController.class);
	@Autowired private UserService userService;

	@RequestMapping(method = RequestMethod.PUT, value = "/forgot-password")
	public UserBO forgotPassword(@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for forgot password received: Manager Key {}", bo.getLoginName());
		sreq.addRequestData(bo);
		ServiceResponse response = userService.forgotPassword(sreq);
		return getResultBO(response, UserBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/verify-fp-token")
	public UserBO verifyFPToken(@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for forgot password token validation received");
		sreq.addRequestData(bo);
		ServiceResponse response = userService.verifyFPToken(sreq);
		return getResultBO(response, UserBO.class);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/reset-password")
	public UserBO resetPassword(@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for reset password received: Manager Key {}", bo.getLoginName());
		sreq.addRequestData(bo);
		ServiceResponse response = userService.resetPassword(sreq);
		return getResultBO(response, UserBO.class);
	}
	//@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq
	@RequestMapping(method = RequestMethod.PUT, value = "/reset-password-url")
	public String resetPasswordUrl(@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for reset password received: Manager Key {}", bo.getLoginName());
		sreq.addRequestData(bo);
		String loginName = null;
		String respToken = null;
		String recoveryUrlBase = "http://cms.omnimarkettide.com/recovery";
		//ServiceResponse response = userService.resetPassword(sreq);
		loginName = userService.forgotPasswordClone(sreq);
		respToken = userService.fetchFPToken(loginName);
		String loginNameEncoded = null;
		try {
			loginNameEncoded = URLEncoder.encode(loginName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			loginNameEncoded = "";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recoveryUrlBase+"?recovery="+loginNameEncoded+"&emailTocken="+respToken;
		//return respToken;
		//return getResultBO(response, UserBO.class);
	}
}
