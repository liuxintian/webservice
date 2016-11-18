package com.omt.cms.cust.admin.rest.api;

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
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.cust.service.InstanceManagerService;

@RestController
@RequestMapping("/instance/oapi")
public class InstanceOAPIController  extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(InstanceOAPIController.class);    
	@Autowired InstanceManagerService cs;

	@RequestMapping(method = RequestMethod.PUT, value = "/managers/reset-password")
	public UserDetailsBO resetPassword(@RequestBody UserDetailsBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for manager reset password received: Manager Key {}", bo.getLoginName());
		sreq.addRequestData(bo);
		ServiceResponse response = cs.resetPassword(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/managers/forgot-password")
	public String forgotPassword(@RequestBody UserDetailsBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for manager forgot password received: Manager Key {}", bo.getLoginName());
		sreq.addRequestData(bo);
		ServiceResponse response = cs.forgotPassword(sreq);
		return getResultBO(response, String.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/managers/verify-fp-token")
	public UserDetailsBO verifyFPToken(@RequestBody LoginUserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for manager forgot password token validation received");
		sreq.addRequestData(bo);
		ServiceResponse response = cs.verifyFPToken(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}
}
