package com.omt.cms.mobile.rest.api;

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

import com.omt.cms.company.instance.service.DocumentLinksMediaProxyService;
import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.DocumentLinksMediaBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/companies/{cpId}/document-links")
public class DocumentLinksMediaMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentLinksMediaMobileController.class);    
	@Autowired DocumentLinksMediaProxyService service;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<DocumentLinksMediaBO> readAll(@PathVariable Long cpId, 
			@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@RequestParam(name=SystemCodes.FILTER_EMPTY_TAG, required=false) Boolean etag,
			@RequestParam(name=SystemCodes.FILTER_TAGS, required=false) String tags,
			@ModelAttribute("serviceRequest") ServiceRequest sreq){
		LOGGER.debug("Request for Announcement list received: cp Key {}", cpId);
		DocumentLinksMediaBO reqBO = new DocumentLinksMediaBO();
		reqBO.setCompanyId(cpId);
		FilterCriteriaBO criteria = createFilterValues(reqBO, cpId, status, vtd, tags, etag);
		sreq.addRequestData(criteria);
		ServiceResponse response = service.readAllActive(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{dlmId}")
	public DocumentLinksMediaBO read(@PathVariable Long cpId, @PathVariable Long dlmId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for DocumentLinksMedia received: cp Key {}", cpId);
		DocumentLinksMediaBO kdBO = new DocumentLinksMediaBO();
		kdBO.setId(dlmId);
		kdBO.setCompanyId(cpId);
		sreq.addRequestData(kdBO);
		ServiceResponse response = service.readActive(sreq);
		return getResultBO(response, DocumentLinksMediaBO.class);
	}
}