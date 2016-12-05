package com.omt.cms.mobile.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.omt.cms.company.instance.service.InstanceManagerProxyService;
import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.UserDetailsBO;

@RestController
@RequestMapping("/mobile/api/company-instances/{cpId}/managers")
public class InstanceManagerController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(InstanceManagerController.class);    
	@Autowired InstanceManagerProxyService ims;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDetailsBO> create(@PathVariable Long cpId, @RequestBody @Valid UserDetailsBO bo, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new manager creation received. Request Details : {}", bo);
		bo.setCompanyId(cpId);
		sreq.addRequestData(bo);
		ServiceResponse response = ims.add(sreq);
		UserDetailsBO updtBO = getResultBO(response, UserDetailsBO.class);
		return new ResponseEntity<UserDetailsBO>(updtBO, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public UserDetailsBO update(@PathVariable Long cpId, @PathVariable Long id, @RequestBody UserDetailsBO bo,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for manager update received: Manager Key {}", id);
		bo.setCompanyId(cpId);
		bo.setUserId(id);
		sreq.addRequestData(bo);
		ServiceResponse response = ims.update(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public UserDetailsBO read(@PathVariable Long cpId, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for Manager read received: Manager Key {}", id);
		UserDetailsBO bo = new UserDetailsBO();
		bo.setCompanyId(cpId);
		bo.setUserId(id);
		sreq.addRequestData(bo);
		ServiceResponse response = ims.read(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDetailsBO> readAll(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for all Manager read received");
		sreq.addRequestData(cpId);
		ServiceResponse response = ims.listManagers(sreq);
		return getResultBO(response, List.class);
	}
}