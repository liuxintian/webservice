package com.omt.cms.mobile.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.service.IRMenuService;
import com.omt.cms.master.service.bo.IRMenuBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/companies/{cpId}/ir-menus")
public class IRMenuMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IRMenuMobileController.class);    

 	@Autowired IRMenuService irs;


	@RequestMapping(method = RequestMethod.PUT)
	public IRMenuBO update(@PathVariable Long cpId, @RequestBody IRMenuBO irm, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for irm update received: irm Key {}", cpId);
		irm.setCompanyId(cpId);
		sreq.addRequestData(irm);
		ServiceResponse response = irs.update(sreq);
		return getResultBO(response, IRMenuBO.class);
	}


	 
}