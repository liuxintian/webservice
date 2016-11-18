package com.omt.cms.mobile.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.company.instance.service.AllInstancesDataProxyService;
import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.AnnouncementBO;
import com.omt.cms.core.service.bo.base.KeyDateBO;

@RestController
@RequestMapping("/mobile/api")
public class AllInstancesDataMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AllInstancesDataMobileController.class);    
	@Autowired AllInstancesDataProxyService service;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/announcements/all")
	public List<AnnouncementBO> readAllAnnouncements(
			@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@RequestParam(name=SystemCodes.FILTER_EMPTY_TAG, required=false) Boolean etag,
			@RequestParam(name=SystemCodes.FILTER_TAGS, required=false) String tags,
			@RequestParam(name=SystemCodes.FILTER_CP_TYPE, required=false) String cpType,
			@ModelAttribute("serviceRequest") ServiceRequest sreq){
		LOGGER.debug("Request for Announcement list received");
		AnnouncementBO reqBO = new AnnouncementBO();
		FilterCriteriaBO criteria = createFilterValues(reqBO, status, vtd, tags, etag,cpType);
		sreq.addRequestData(criteria);
		ServiceResponse response = service.readAllInstancesAnnouncements(sreq);
		return getResultBO(response, List.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/key-dates/all")
	public List<KeyDateBO> readAllKeyDates(
			@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@RequestParam(name=SystemCodes.FILTER_EMPTY_TAG, required=false) Boolean etag,
			@RequestParam(name=SystemCodes.FILTER_TAGS, required=false) String tags,
			@RequestParam(name=SystemCodes.FILTER_CP_TYPE, required=false) String cpType,
			@ModelAttribute("serviceRequest") ServiceRequest sreq){
		LOGGER.debug("Request for Keydates list received");
		KeyDateBO reqBO = new KeyDateBO();
		FilterCriteriaBO criteria = createFilterValues(reqBO, status, vtd, tags, etag,cpType);
		sreq.addRequestData(criteria);
		ServiceResponse response = service.readAllInstancesKeyDates(sreq);
		return getResultBO(response, List.class);
	}

}
