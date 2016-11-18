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

import com.omt.cms.company.instance.service.CompanyExecutiveProxyService;
import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.CompanyExecutiveBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/companies/{cpId}/executives")
public class CompanyExecutiveMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyExecutiveMobileController.class);    
	@Autowired CompanyExecutiveProxyService ceService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyExecutiveBO> readAll(@PathVariable Long cpId, @RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
			@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) String status,
			@ModelAttribute("serviceRequest") ServiceRequest sreq){
		LOGGER.debug("Request for CompanyExecutive list received: cp Key {}", cpId);
		CompanyExecutiveBO reqBO = new CompanyExecutiveBO();
		reqBO.setCompanyId(cpId);
		addFilterValues(reqBO, status, vtd);
		sreq.addRequestData(reqBO);
		ServiceResponse response = ceService.readAllActive(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{ceId}")
	public CompanyExecutiveBO read(@PathVariable Long cpId, @PathVariable Long ceId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for CompanyExecutive received: cp Key {}", cpId);
		CompanyExecutiveBO kdBO = new CompanyExecutiveBO();
		kdBO.setId(ceId);
		kdBO.setCompanyId(cpId);
		sreq.addRequestData(kdBO);
		ServiceResponse response = ceService.readActive(sreq);
		return getResultBO(response, CompanyExecutiveBO.class);
	}
}