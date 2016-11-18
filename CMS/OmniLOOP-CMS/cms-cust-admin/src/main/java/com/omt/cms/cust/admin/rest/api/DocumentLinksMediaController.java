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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.DocumentLinksMediaBO;
import com.omt.cms.cust.service.DocumentLinksMediaService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/cust-admin/api/company-instances/{cpId}/document-links")
public class DocumentLinksMediaController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentLinksMediaController.class);    
	@Autowired DocumentLinksMediaService ces;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<DocumentLinksMediaBO> readAll(@PathVariable Long cpId,
			@RequestParam(name=SystemCodes.FILTER_TERM, required=false) String term,
			@RequestParam(name=SystemCodes.FILTER_PAGE, required=false) Integer page,
			@RequestParam(name=SystemCodes.FILTER_SIZE, required=false) Integer size,
			@RequestParam(name=SystemCodes.FILTER_SORT_BY, required=false) String sortBy,
			@RequestParam(name=SystemCodes.FILTER_SORT_ORDER, required=false) Integer sortOrder,
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for DocumentLinksMedia list received: cp Key {}", cpId);
		FilterCriteriaBO criteria = new FilterCriteriaBO(term, page, size, sortBy, sortOrder);
		criteria.cpId = cpId;
		sreq.addRequestData(criteria);
		ServiceResponse response = ces.readAll(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{ceId}")
	public DocumentLinksMediaBO read(@PathVariable Long cpId, @PathVariable Long ceId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for DocumentLinksMedia received: cp Key {}", cpId);
		DocumentLinksMediaBO exec = new DocumentLinksMediaBO();
		exec.setId(ceId);
		exec.setCompanyId(cpId);
		sreq.addRequestData(exec);
		ServiceResponse response = ces.read(sreq);
		return getResultBO(response, DocumentLinksMediaBO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DocumentLinksMediaBO> create(@PathVariable Long cpId, @RequestBody @Valid DocumentLinksMediaBO ceb, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new DocumentLinksMedia creation received. Request Details : {}", ceb);
		ceb.setCompanyId(cpId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.add(sreq);
		DocumentLinksMediaBO updtDocumentLinksMedia = getResultBO(response, DocumentLinksMediaBO.class);
		return new ResponseEntity<DocumentLinksMediaBO>(updtDocumentLinksMedia, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{ceId}")
	public DocumentLinksMediaBO update(@PathVariable Long cpId,  @PathVariable Long ceId, @RequestBody DocumentLinksMediaBO ceb, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for DocumentLinksMedia update received: cp Key {}", cpId);
		ceb.setCompanyId(cpId);
		ceb.setId(ceId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.update(sreq);
		return getResultBO(response, DocumentLinksMediaBO.class);
	}

}