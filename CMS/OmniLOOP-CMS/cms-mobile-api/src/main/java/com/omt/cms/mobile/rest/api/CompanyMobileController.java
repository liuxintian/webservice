package com.omt.cms.mobile.rest.api;

import java.util.List;

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
import com.omt.cms.core.service.bo.base.AddressBO;
import com.omt.cms.master.entity.AsxRegistry;
import com.omt.cms.master.service.CompanyService;
import com.omt.cms.master.service.IRMenuService;
import com.omt.cms.master.service.bo.CompanyBO;
import com.omt.cms.master.service.bo.IRMenuBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/companies")
public class CompanyMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMobileController.class);    
	@Autowired IRMenuService irs;
	@Autowired CompanyService cs;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyBO> readAllActive(@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for company list is received");
		ServiceResponse response = cs.readAll(sreq);
		return getResultBO(response, List.class);
	}

    @RequestMapping(method = RequestMethod.GET, value="/newEndPoint/{asxCode}")    
    public AddressBO justTest(@PathVariable String asxCode){
    	AddressBO abo = new AddressBO();
    	abo.setAddressCity("Mel");
    	abo.setAddressCountry("AU");
    	abo.setAddressState("VIC");
    	abo.setAddressZip("3000");
    	abo.setAddressLine1("address:" + asxCode);
    	return abo;
    }
    
	@RequestMapping(method = RequestMethod.GET, value = "/asx-code/{asxCode}")
	public CompanyBO readByAsxCode(@PathVariable String asxCode, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for company read received: company ASX Code {}", asxCode);
		sreq.addRequestData(asxCode);
		ServiceResponse response = cs.readByASXCode(sreq);
		return getResultBO(response, CompanyBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{cpId}")
	public CompanyBO read(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for company read received: company Key {}", cpId);
		CompanyBO company=new CompanyBO();
		company.setId(cpId);
		sreq.addRequestData(company);
		ServiceResponse response = cs.read(sreq);
		return getResultBO(response, CompanyBO.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{cpId}/ir-menus")
	public IRMenuBO readIRMenus(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for irm read received: irm Key {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = irs.read(sreq);
		return getResultBO(response, IRMenuBO.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/type/{typeCode}")
	public List<CompanyBO> readByCompanyType(@PathVariable String typeCode, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for company list received: company type Code {}", typeCode);
		sreq.addRequestData(typeCode);
		ServiceResponse response = cs.readByCompanyType(sreq);
		return getResultBO(response, List.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/asx-codes/all")
	public List<AsxRegistry> readByAsxCode(@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		ServiceResponse response = cs.listAsxRegistry(sreq);
		return getResultBO(response, List.class);
	}

}