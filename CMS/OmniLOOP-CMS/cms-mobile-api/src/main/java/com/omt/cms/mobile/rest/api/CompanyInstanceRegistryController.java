package com.omt.cms.mobile.rest.api;

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
import com.omt.cms.master.service.CompanyInstanceRegistryService;
import com.omt.cms.master.service.bo.CompanyInstanceRegistryBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/companies/{cpId}/instances")
public class CompanyInstanceRegistryController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyInstanceRegistryController.class);    
	@Autowired CompanyInstanceRegistryService cirs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CompanyInstanceRegistryBO> create(@PathVariable Long cpId, @RequestBody @Valid CompanyInstanceRegistryBO cir, 
			BindingResult result,
			UriComponentsBuilder builder, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new company creation received. Request Details : {}", cir);
		cir.setCpId(cpId);
		sreq.addRequestData(cir);
		ServiceResponse response = cirs.add(sreq);
		CompanyInstanceRegistryBO updtCompany = getResultBO(response, CompanyInstanceRegistryBO.class);
		return new ResponseEntity<CompanyInstanceRegistryBO>(updtCompany, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public CompanyInstanceRegistryBO update(@PathVariable Long cpId, @PathVariable Long id, @RequestBody CompanyInstanceRegistryBO cir, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cir update received: cir Key {}", id);
		cir.setCpId(cpId);
		cir.setId(id);
		sreq.addRequestData(cir);
		ServiceResponse response = cirs.update(sreq);
		return getResultBO(response, CompanyInstanceRegistryBO.class);
	}

	@RequestMapping(method = RequestMethod.GET)
	public CompanyInstanceRegistryBO read(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cir read received: cir Key {}", cpId);
		CompanyInstanceRegistryBO cir=new CompanyInstanceRegistryBO();
		cir.setCpId(cpId);
		sreq.addRequestData(cir);
		ServiceResponse response = cirs.read(sreq);
		return getResultBO(response, CompanyInstanceRegistryBO.class);
	}
}