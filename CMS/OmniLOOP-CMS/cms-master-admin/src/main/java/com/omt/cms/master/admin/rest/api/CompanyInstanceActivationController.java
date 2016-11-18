package com.omt.cms.master.admin.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.company.instance.service.CompanyInstanceManagerProxyService;
import com.omt.cms.company.instance.service.CompanyInstanceProxyService;
import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.service.bo.CompanyInstanceRegistryBO;

@RestController
@RequestMapping("/master-admin/api/companies/{cpId}")
public class CompanyInstanceActivationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyInstanceActivationController.class);    
	@Autowired CompanyInstanceProxyService cis;
	@Autowired CompanyInstanceManagerProxyService cims;

	@RequestMapping(method = RequestMethod.PUT, value="/activate")
	public  CompanyInstanceRegistryBO  activate(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for activating CompanyInstance received. Request Details : {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = cis.add(sreq);
		return getResultBO(response, CompanyInstanceRegistryBO.class);
	}

	@RequestMapping(method = RequestMethod.POST, value="/managers")
	public  UserDetailsBO  addManager(@PathVariable Long cpId, @RequestBody @Valid UserDetailsBO bo,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for creating new instance manager received. Request Details : {}", cpId);
		bo.setCompanyId(cpId);
		sreq.addRequestData(bo);
		ServiceResponse response = cims.add(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/managers/disable")
	public  UserDetailsBO  disableManager(@PathVariable Long cpId, @RequestBody @Valid UserDetailsBO bo,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for creating new instance manager received. Request Details : {}", cpId);
		bo.setCompanyId(cpId);
		sreq.addRequestData(bo);
		ServiceResponse response = cims.disable(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}


	@RequestMapping(method = RequestMethod.PUT, value="/managers/enable")
	public  UserDetailsBO  enableManager(@PathVariable Long cpId, @RequestBody @Valid UserDetailsBO bo,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for creating new instance manager received. Request Details : {}", cpId);
		bo.setCompanyId(cpId);
		sreq.addRequestData(bo);
		ServiceResponse response = cims.enable(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/managers")
	public  List<UserDetailsBO>  readManagers(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for instance managers received. Request Details : {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = cims.readAllActive(sreq);
		return response.getResponseData(List.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/reactivate")
	public  Company  reactivate(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		sreq.addRequestData(cpId);
		ServiceResponse response = cis.activate(sreq);
		return getResultBO(response, Company.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/deactivate")
	public  Company  deactivate(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		sreq.addRequestData(cpId);
		ServiceResponse response = cis.deactivate(sreq);
		return getResultBO(response, Company.class);
	}
	
}
