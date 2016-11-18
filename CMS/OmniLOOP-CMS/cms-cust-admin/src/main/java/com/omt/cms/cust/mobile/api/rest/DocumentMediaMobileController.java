package com.omt.cms.cust.mobile.api.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/mobile/api/company-instances/{cpId}/document-links")
public class DocumentMediaMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentMediaMobileController.class);    
	@Autowired DocumentLinksMediaService service;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<DocumentLinksMediaBO> readAll(@PathVariable Long cpId, 
			@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@RequestParam(name=SystemCodes.FILTER_EMPTY_TAG, required=false) Boolean etag,
			@RequestParam(name=SystemCodes.FILTER_TAGS, required=false) String tags,
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for DocumentLinksMedia list received: cp Key {}", cpId);
		DocumentLinksMediaBO reqBO = new DocumentLinksMediaBO();
		reqBO.setCompanyId(cpId);
		FilterCriteriaBO criteria = createFilterValues(reqBO, cpId, status, vtd, tags, etag);
		sreq.addRequestData(criteria);
		ServiceResponse response = service.readAllWithFilters(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{docId}")
	public DocumentLinksMediaBO read(@PathVariable Long cpId, @PathVariable Long docId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for DocumentLinksMedia received: cp Key {}", cpId);
		DocumentLinksMediaBO doc = new DocumentLinksMediaBO();
		doc.setId(docId);
		doc.setCompanyId(cpId);
		sreq.addRequestData(doc);
		ServiceResponse response = service.read(sreq);
		return getResultBO(response, DocumentLinksMediaBO.class);
	}

}