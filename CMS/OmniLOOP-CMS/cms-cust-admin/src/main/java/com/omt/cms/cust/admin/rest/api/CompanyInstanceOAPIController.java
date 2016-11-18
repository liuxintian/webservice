package com.omt.cms.cust.admin.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.service.CompanyInstanceService;
import com.omt.cms.cust.service.DataMenuProxyService;
import com.omt.cms.cust.service.InstanceManagerService;
import com.omt.cms.master.service.bo.CompanyBO;
import com.omt.cms.master.service.bo.DataMenuBO;

@RestController
@RequestMapping("/oapi/company-instances/{cpId}")
public class CompanyInstanceOAPIController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyInstanceController.class);    

	@Autowired CompanyInstanceService cis;
	@Autowired InstanceManagerService ims;
	@Autowired DataMenuProxyService dms;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CompanyInstance> create(@PathVariable Long cpId, @RequestBody @Valid CompanyInstance cp, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new CompanyInstance creation received. Request Details : {}", cp);
		cp.setId(cpId);
		sreq.addRequestData(cp);
		ServiceResponse response = cis.add(sreq);
		CompanyInstance updtCompanyInstance = getResultBO(response, CompanyInstance.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/cust-admin/api/company-instances/{cpId}").buildAndExpand(updtCompanyInstance.getId()).toUri());
		return new ResponseEntity<CompanyInstance>(updtCompanyInstance, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/managers")
	public ResponseEntity<UserDetailsBO> create(@PathVariable Long cpId, @RequestBody @Valid UserDetailsBO bo, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new manager creation received. Request Details : {}", bo);
		bo.setCompanyId(cpId);
		sreq.addRequestData(bo);
		ServiceResponse response = ims.add(sreq);
		UserDetailsBO updtBO = getResultBO(response, UserDetailsBO.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/oapi/company-instances/{cpId}/managers/{id}").buildAndExpand(cpId, updtBO.getUserId()).toUri());
		return new ResponseEntity<UserDetailsBO>(updtBO, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/managers/disable")
	public  UserDetailsBO  disable(@PathVariable Long cpId, @RequestBody @Valid UserDetailsBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for disable manager creation received. Request Details : {}", bo);
		bo.setCompanyId(cpId);
		sreq.addRequestData(bo);
		ServiceResponse response = ims.disable(sreq);
		UserDetailsBO updtBO = getResultBO(response, UserDetailsBO.class);
  		return updtBO;
	}


	@RequestMapping(method = RequestMethod.PUT, value="/managers/enable")
	public  UserDetailsBO  enable(@PathVariable Long cpId, @RequestBody @Valid UserDetailsBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for disable manager creation received. Request Details : {}", bo);
		bo.setCompanyId(cpId);
		sreq.addRequestData(bo);
		ServiceResponse response = ims.enable(sreq);
		UserDetailsBO updtBO = getResultBO(response, UserDetailsBO.class);
  		return updtBO;
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/managers")
	public List<UserDetailsBO> readAll(@PathVariable Long cpId,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for list of managers received. Request Details : {}", cpId);
 		sreq.addRequestData(cpId);
		ServiceResponse response = ims.listManagers(sreq);
  		return response.getResponseData(List.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/datamenus")
	public List<DataMenuBO> readDataMenus( @PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for dm read ");
	  
    	CompanyBO cp=new CompanyBO();
    	cp.setId(cpId);
    	sreq.addRequestData(cp);
		ServiceResponse response = dms.readAll(sreq);
		return response.getResponseData(List.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/deactivate")
	public CompanyInstance deactivate(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		sreq.addRequestData(cpId);
		ServiceResponse response = cis.deactivate(sreq);
		return getResultBO(response, CompanyInstance.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/activate")
	public CompanyInstance activate(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		sreq.addRequestData(cpId);
		ServiceResponse response = cis.activate(sreq);
		return getResultBO(response, CompanyInstance.class);
	}

}
