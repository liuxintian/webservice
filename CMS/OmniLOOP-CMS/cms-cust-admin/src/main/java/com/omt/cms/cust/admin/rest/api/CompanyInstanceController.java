package com.omt.cms.cust.admin.rest.api;

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
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.service.CompanyInstanceService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/cust-admin/api/company-instances/{cpId}")
public class CompanyInstanceController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyInstanceController.class);    
	@Autowired CompanyInstanceService cis;

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

	@RequestMapping(method = RequestMethod.PUT)
	public CompanyInstance update(@PathVariable Long cpId, @RequestBody CompanyInstance cp, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp update received: cp Key {}", cpId);
		cp.setId(cpId);
		sreq.addRequestData(cp);
		ServiceResponse response = cis.update(sreq);
		return getResultBO(response, CompanyInstance.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/delete")
	public CompanyInstance delete(@PathVariable Long cpId, @RequestBody CompanyInstance cp, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp delete received: cp Key {}", cpId);
		cp.setId(cpId);
		sreq.addRequestData(cp);
		ServiceResponse response = cis.delete(sreq);
		return getResultBO(response, CompanyInstance.class);
	}

	@RequestMapping(method = RequestMethod.GET)
	public CompanyInstance read(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp read received: cp Key {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = cis.read(sreq);
		return getResultBO(response, CompanyInstance.class);
	}
	
}