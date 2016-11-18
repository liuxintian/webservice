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
import com.omt.cms.core.service.bo.base.KeyDateBO;
import com.omt.cms.cust.service.KeyDateService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/company-instances/{cpId}/key-dates")
public class KeyDateMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyDateMobileController.class);    
	@Autowired KeyDateService kdService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<KeyDateBO> readAll(@PathVariable Long cpId, 
			@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@RequestParam(name=SystemCodes.FILTER_EMPTY_TAG, required=false) Boolean etag,
			@RequestParam(name=SystemCodes.FILTER_TAGS, required=false) String tags,
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for KeyDate list received: cp Key {}", cpId);
		KeyDateBO reqBO = new KeyDateBO();
		reqBO.setCompanyId(cpId);
		FilterCriteriaBO criteria = createFilterValues(reqBO, cpId, status, vtd, tags, etag);
		sreq.addRequestData(criteria);
		ServiceResponse response = kdService.readAllWithFilters(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{kdId}")
	public KeyDateBO read(@PathVariable Long cpId, @PathVariable Long kdId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for KeyDate received: cp Key {}", cpId);
		KeyDateBO kdBO = new KeyDateBO();
		kdBO.setId(kdId);
		kdBO.setCompanyId(cpId);
		sreq.addRequestData(kdBO);
		ServiceResponse response = kdService.read(sreq);
		return getResultBO(response, KeyDateBO.class);
	}


}