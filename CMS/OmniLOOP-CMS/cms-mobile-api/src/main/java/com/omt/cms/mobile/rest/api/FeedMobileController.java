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

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.service.FeedService;
import com.omt.cms.master.service.bo.FeedBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/feeds")
public class FeedMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedMobileController.class);    
	@Autowired FeedService fs;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<FeedBO> readAll(@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@RequestParam(name=SystemCodes.FILTER_EMPTY_TAG, required=false) Boolean etag,
			@RequestParam(name=SystemCodes.FILTER_TAGS, required=false) String tags,
			@ModelAttribute("serviceRequest") ServiceRequest sreq){
		LOGGER.debug("Request for feed list received:");
		FeedBO reqBO = new FeedBO();
		FilterCriteriaBO criteria = createFilterValues(reqBO, status, vtd, tags, etag);
		sreq.addRequestData(criteria);
		ServiceResponse response = fs.readAllWithFilters(sreq);
		return getResultBO(response, List.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public FeedBO read(@PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for feed read received: feed Key {}", id);
		sreq.addRequestData(id);
		ServiceResponse response = fs.read(sreq);
		return getResultBO(response, FeedBO.class);
	}
}