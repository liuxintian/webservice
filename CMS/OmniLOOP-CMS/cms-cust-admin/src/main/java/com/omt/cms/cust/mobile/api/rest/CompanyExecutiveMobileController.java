package com.omt.cms.cust.mobile.api.rest;

import java.sql.Timestamp;
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
@RequestMapping("/mobile/api/company-instances/{cpId}/executives")
public class CompanyExecutiveMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyExecutiveMobileController.class);    
	@Autowired CompanyExecutiveService ces;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyExecutiveBO> readAll(@PathVariable Long cpId, 
										@RequestParam(name=SystemCodes.FILTER_VALID_UNTIL, required=false) Long vtd,
										@RequestParam(name=SystemCodes.FILTER_STATUS, required=false) Integer status,
										@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for executive list received: cp Key {}", cpId);
		CompanyExecutiveBO reqBO = new CompanyExecutiveBO();
		reqBO.setCompanyId(cpId);
		if(vtd!=null){
			reqBO.setValidUntil(new Timestamp(vtd));	
		}
		if(status!=null){
			reqBO.setStatus(status);	
		}
		sreq.addRequestData(reqBO);
		ServiceResponse response = ces.readAllWithFilters(sreq);
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

}