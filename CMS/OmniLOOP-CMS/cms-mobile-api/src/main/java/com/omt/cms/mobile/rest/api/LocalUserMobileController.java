package com.omt.cms.mobile.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.company.instance.service.LocalUserProxyService;
import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LocalUserBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/company-instances")
public class LocalUserMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalUserMobileController.class);    
	@Autowired private LocalUserProxyService lus;
 
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/{cpId}/local-users")
	public List<LocalUserBO> readAll(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser list received: cp Key {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = lus.readAllActive(sreq);
		return getResultBO(response, List.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/asx-code/{asxCode}/local-users/type/{type}")
	public List<LocalUserBO> readAll(@PathVariable String asxCode, 
			@PathVariable String type, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser list received: cp Key {}", asxCode);
		boolean ss = false;
		if(StringUtils.isNotBlank(type)){
			if(StringUtils.equals(type, "subs")){
				ss = true;
			}
		}
		LocalUserBO reqBO = new LocalUserBO();
		reqBO.setCompanyName(asxCode);
		reqBO.setShareSubscriber(ss);
		sreq.addRequestData(reqBO);
		ServiceResponse response = lus.readAllByType(sreq);
		return getResultBO(response, List.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/asx-code/{asxCode}/local-users")
	public List<LocalUserBO> readAllByAsxCode(@PathVariable String asxCode, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser list received: cp Key {}", asxCode);
		sreq.addRequestData(asxCode);
		ServiceResponse response = lus.readAllActiveByAsxCode(sreq);
		return getResultBO(response, List.class);
	}

	@RequestMapping(method = RequestMethod.GET, value="/{cpId}/local-users/{id}")
	public LocalUserBO read(@PathVariable Long cpId, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser received: cp Key {}", cpId);
		LocalUserBO lubo = new LocalUserBO();
		lubo.setId(id);
		lubo.setCompanyId(cpId);
		sreq.addRequestData(lubo);
		ServiceResponse response = lus.readActive(sreq);
		return getResultBO(response, LocalUserBO.class);
	}

	@RequestMapping(method = RequestMethod.POST, value="/{cpId}/local-users")
	public ResponseEntity<LocalUserBO> create(@PathVariable Long cpId, @RequestBody @Valid LocalUserBO reqBO, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new LocalUser creation received. Request Details : {}", reqBO);
		reqBO.setCompanyId(cpId);
		sreq.addRequestData(reqBO);
		ServiceResponse response = lus.add(sreq);
		LocalUserBO updtLocalUser = getResultBO(response, LocalUserBO.class);
		return new ResponseEntity<LocalUserBO>(updtLocalUser, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public LocalUserBO update(@PathVariable Long cpId,  @PathVariable Long id, @RequestBody LocalUserBO reqBO, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser update received: cp Key {}", cpId);
		reqBO.setCompanyId(cpId);
		reqBO.setId(id);
		sreq.addRequestData(reqBO);
		ServiceResponse response = lus.update(sreq);
		return getResultBO(response, LocalUserBO.class);
	} 
}