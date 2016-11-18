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

import com.omt.cms.company.instance.service.AnnouncementProxyService;
import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.AnnouncementBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/companies/{cpId}/announcements")
public class AnnouncementMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementMobileController.class);    
	@Autowired AnnouncementProxyService annService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<AnnouncementBO> readAll(@PathVariable Long cpId, 
			@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@RequestParam(name=SystemCodes.FILTER_EMPTY_TAG, required=false) Boolean etag,
			@RequestParam(name=SystemCodes.FILTER_TAGS, required=false) String tags,
			@ModelAttribute("serviceRequest") ServiceRequest sreq){
		LOGGER.debug("Request for Announcement list received: cp Key {}", cpId);
		AnnouncementBO reqBO = new AnnouncementBO();
		reqBO.setCompanyId(cpId);
		FilterCriteriaBO criteria = createFilterValues(reqBO, cpId, status, vtd, tags, etag);
		sreq.addRequestData(criteria);
		ServiceResponse response = annService.readAllActive(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{annId}")
	public AnnouncementBO read(@PathVariable Long cpId, @PathVariable Long annId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for Announcement received: cp Key {}", cpId);
		AnnouncementBO kdBO = new AnnouncementBO();
		kdBO.setId(annId);
		kdBO.setCompanyId(cpId);
		sreq.addRequestData(kdBO);
		ServiceResponse response = annService.readActive(sreq);
		return getResultBO(response, AnnouncementBO.class);
	}
}