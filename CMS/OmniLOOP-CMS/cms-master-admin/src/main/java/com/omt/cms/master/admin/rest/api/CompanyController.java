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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.base.ServiceResult;
import com.omt.cms.master.service.CompanyService;
import com.omt.cms.master.service.bo.CompanyBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/master-admin/api/companies")
public class CompanyController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);    

	@Autowired CompanyService cs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CompanyBO> create(@RequestBody @Valid CompanyBO cp, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new company creation received. Request Details : {}", cp);
		sreq.addRequestData(cp);
		ServiceResponse response = cs.add(sreq);
		CompanyBO updtCompany = getResultBO(response, CompanyBO.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/master-admin/api/companies/{id}").buildAndExpand(updtCompany.getId()).toUri());
		return new ResponseEntity<CompanyBO>(updtCompany, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public CompanyBO update(@RequestBody CompanyBO cp, 
			@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp update received: cp Key {}", id);
		cp.setId(id);
		sreq.addRequestData(cp);
		ServiceResponse response = cs.update(sreq);
		return getResultBO(response, CompanyBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/delete")
	public CompanyBO delete(@RequestBody CompanyBO cp, 
			@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp delete received: cp Key {}", id);
		cp.setId(id);
		sreq.addRequestData(cp);
		ServiceResponse response = cs.delete(sreq);
		return getResultBO(response, CompanyBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CompanyBO read(@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp read received: cp Key {}", id);
		CompanyBO cp=new CompanyBO();
		cp.setId(id);
		sreq.addRequestData(cp);
		ServiceResponse response = cs.read(sreq);
		return getResultBO(response, CompanyBO.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyBO> readAll(
			@RequestParam(name=SystemCodes.FILTER_TERM, required=false) String term,
			@RequestParam(name=SystemCodes.FILTER_PAGE, required=false) Integer page,
			@RequestParam(name=SystemCodes.FILTER_SIZE, required=false) Integer size,			
			@RequestParam(name=SystemCodes.FILTER_SORT_BY, required=false) String sortBy,
			@RequestParam(name=SystemCodes.FILTER_SORT_ORDER, required=false) Integer sortOrder,			
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp read ");
		FilterCriteriaBO criteria = new FilterCriteriaBO(term, page, size, sortBy, sortOrder);
		sreq.addRequestData(criteria);		
		ServiceResponse response = cs.readAllAdmin(sreq);
		return response.getResponseData(List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/asx-code-exists/{asxCode}")
	public ServiceResult asxCodeExists(@PathVariable String asxCode, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for company read received: company ASX Code {}", asxCode);
		sreq.addRequestData(asxCode);
		ServiceResponse response = cs.tickerNameExists(sreq);
		return response.getServiceResult();
	}

}