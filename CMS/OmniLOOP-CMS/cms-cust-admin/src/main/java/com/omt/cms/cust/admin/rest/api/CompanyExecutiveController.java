package com.omt.cms.cust.admin.rest.api;

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
import com.omt.cms.core.service.bo.base.CompanyExecutiveBO;
import com.omt.cms.cust.service.CompanyExecutiveService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/cust-admin/api/company-instances/{cpId}/executives")
public class CompanyExecutiveController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyExecutiveController.class);    
	@Autowired CompanyExecutiveService ces;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyExecutiveBO> readAll(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for executive list received: cp Key {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = ces.readAll(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{ceId}")
	public CompanyExecutiveBO read(@PathVariable Long cpId, @PathVariable Long ceId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for executive received: cp Key {}", cpId);
		CompanyExecutiveBO exec = new CompanyExecutiveBO();
		exec.setId(ceId);
		exec.setCompanyId(cpId);
		sreq.addRequestData(exec);
		ServiceResponse response = ces.read(sreq);
		return getResultBO(response, CompanyExecutiveBO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CompanyExecutiveBO> create(@PathVariable Long cpId, @RequestBody @Valid CompanyExecutiveBO ceb, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new CompanyExecutive creation received. Request Details : {}", ceb);
		ceb.setCompanyId(cpId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.add(sreq);
		CompanyExecutiveBO updtCompanyExecutive = getResultBO(response, CompanyExecutiveBO.class);
		return new ResponseEntity<CompanyExecutiveBO>(updtCompanyExecutive, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{ceId}")
	public CompanyExecutiveBO update(@PathVariable Long cpId,  @PathVariable Long ceId, @RequestBody CompanyExecutiveBO ceb, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for executive update received: cp Key {}", cpId);
		ceb.setCompanyId(cpId);
		ceb.setId(ceId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.update(sreq);
		return getResultBO(response, CompanyExecutiveBO.class);
	}

}