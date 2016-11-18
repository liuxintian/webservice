package com.omt.cms.cust.admin.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.cust.service.CompanyProxyService;
import com.omt.cms.master.service.bo.CompanyBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/cust-admin/api/companies")
public class CompanyController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);    

	@Autowired CompanyProxyService cs;

	 	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CompanyBO read(@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for cp read received: cp Key {}", id);
		CompanyBO cp=new CompanyBO();
		cp.setId(id);
		sreq.addRequestData(cp);
		ServiceResponse response = cs.readCompanyDetails(sreq);
		return getResultBO(response, CompanyBO.class);
	}
	 
}