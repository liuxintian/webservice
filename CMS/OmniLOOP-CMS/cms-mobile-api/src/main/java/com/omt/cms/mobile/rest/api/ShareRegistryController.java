package com.omt.cms.mobile.rest.api;

import javax.validation.Valid;

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

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.service.ShareRegistryService;
import com.omt.cms.master.service.bo.ShareRegistryBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/companies/share-registries")
public class ShareRegistryController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShareRegistryController.class);    
	@Autowired ShareRegistryService srs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ShareRegistryBO> create(@RequestBody @Valid ShareRegistryBO sr, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new share registries. Request Details : {}", sr);
		sreq.addRequestData(sr);
		ServiceResponse response = srs.add(sreq);
		ShareRegistryBO updtIrm = getResultBO(response, ShareRegistryBO.class);
		return new ResponseEntity<ShareRegistryBO>(updtIrm, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ShareRegistryBO update(@RequestBody ShareRegistryBO sr, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for share registries update received: sr Key {}", id);
		sr.setId(id);
		sreq.addRequestData(sr);
		ServiceResponse response = srs.update(sreq);
		return getResultBO(response, ShareRegistryBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ShareRegistryBO read(@PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for share registries read received: sr Key {}", id);
		sreq.addRequestData(id);
		ServiceResponse response = srs.read(sreq);
		return getResultBO(response, ShareRegistryBO.class);
	}

}