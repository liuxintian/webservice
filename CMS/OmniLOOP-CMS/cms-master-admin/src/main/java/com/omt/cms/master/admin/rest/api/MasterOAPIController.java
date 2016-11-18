package com.omt.cms.master.admin.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.master.service.CompanyService;
import com.omt.cms.master.service.DataMenuService;
import com.omt.cms.master.service.MasterManagerService;
import com.omt.cms.master.service.bo.CompanyBO;
import com.omt.cms.master.service.bo.DataMenuBO;

@RestController
@RequestMapping("/master/oapi")
public class MasterOAPIController  extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MasterOAPIController.class);    
	@Autowired MasterManagerService cs;
	@Autowired CompanyService cps;
	@Autowired DataMenuService dms;

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
	
	@RequestMapping(method = RequestMethod.GET, value = "/companies/{id}")
	public CompanyBO read(@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp read received: cp Key {}", id);
		CompanyBO cp=new CompanyBO();
		cp.setId(id);
		sreq.addRequestData(cp);
		ServiceResponse response = cps.read(sreq);
		return getResultBO(response, CompanyBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/datamenus")
	public List<DataMenuBO> readAll(  ServiceRequest sreq) {
		LOGGER.debug("Request for dm read ");
		ServiceResponse response = dms.readAll(sreq);
		return response.getResponseData(List.class);
	}
}
