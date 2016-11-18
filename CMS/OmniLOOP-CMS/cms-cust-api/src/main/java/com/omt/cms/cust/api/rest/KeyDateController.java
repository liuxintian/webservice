package com.omt.cms.cust.api.rest;

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

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.KeyDateBO;
import com.omt.cms.cust.service.KeyDateService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/company-instances/{cpId}/key-dates")
public class KeyDateController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyDateController.class);    
	@Autowired KeyDateService ces;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<KeyDateBO> readAll(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for KeyDate list received: cp Key {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = ces.readAll(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{kdId}")
	public KeyDateBO read(@PathVariable Long cpId, @PathVariable Long kdId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for KeyDate received: cp Key {}", cpId);
		KeyDateBO exec = new KeyDateBO();
		exec.setId(kdId);
		exec.setCompanyId(cpId);
		sreq.addRequestData(exec);
		ServiceResponse response = ces.read(sreq);
		return getResultBO(response, KeyDateBO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<KeyDateBO> create(@PathVariable Long cpId, @RequestBody @Valid KeyDateBO ceb, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new KeyDate creation received. Request Details : {}", ceb);
		ceb.setCompanyId(cpId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.add(sreq);
		KeyDateBO updtKeyDate = getResultBO(response, KeyDateBO.class);
		return new ResponseEntity<KeyDateBO>(updtKeyDate, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{kdId}")
	public KeyDateBO update(@PathVariable Long cpId,  @PathVariable Long kdId, @RequestBody KeyDateBO ceb, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for KeyDate update received: cp Key {}", cpId);
		ceb.setCompanyId(cpId);
		ceb.setId(kdId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.update(sreq);
		return getResultBO(response, KeyDateBO.class);
	}

}