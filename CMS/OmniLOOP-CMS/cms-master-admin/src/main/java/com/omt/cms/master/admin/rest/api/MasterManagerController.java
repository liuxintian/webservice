package com.omt.cms.master.admin.rest.api;

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
import com.omt.cms.master.service.MasterManagerService;

@RestController
@RequestMapping("/master-admin/api/managers")
public class MasterManagerController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(MasterManagerController.class);    

	@Autowired MasterManagerService cs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDetailsBO> create(@RequestBody @Valid UserDetailsBO bo, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new manager creation received. Request Details : {}", bo);
		sreq.addRequestData(bo);
		ServiceResponse response = cs.add(sreq);
		UserDetailsBO updtBO = getResultBO(response, UserDetailsBO.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/master-admin/api/managers/{id}").buildAndExpand(updtBO.getUserId()).toUri());
		return new ResponseEntity<UserDetailsBO>(updtBO, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public UserDetailsBO update(@RequestBody UserDetailsBO bo, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for manager update received: Manager Key {}", id);
		bo.setUserId(id);
		sreq.addRequestData(bo);
		ServiceResponse response = cs.update(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public UserDetailsBO read(@PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for Manager read received: Manager Key {}", id);
		sreq.addRequestData(id);
		ServiceResponse response = cs.read(sreq);
		return getResultBO(response, UserDetailsBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDetailsBO> readAll(@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for all Manager read received");
		ServiceResponse response = cs.listManagers(sreq);
		return getResultBO(response, List.class);
	}
}
