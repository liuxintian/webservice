package com.omt.cms.mobile.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.omt.cms.master.service.IRMenuItemService;
import com.omt.cms.master.service.bo.IRMenuItemBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/ir-menu-items")
public class IRMenuItemController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IRMenuItemController.class);    
	@Autowired IRMenuItemService irs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IRMenuItemBO> create(@RequestBody @Valid IRMenuItemBO irm, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new IR Menu items. Request Details : {}", irm);
		sreq.addRequestData(irm);
		ServiceResponse response = irs.add(sreq);
		IRMenuItemBO updtIrm = getResultBO(response, IRMenuItemBO.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/mobile/api/ir-menu-items/{id}").buildAndExpand(updtIrm.getId()).toUri());
		return new ResponseEntity<IRMenuItemBO>(updtIrm, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public IRMenuItemBO update(@RequestBody IRMenuItemBO irm, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for irm update received: irm Key {}", id);
		irm.setId(id);
		sreq.addRequestData(irm);
		ServiceResponse response = irs.update(sreq);
		return getResultBO(response, IRMenuItemBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public IRMenuItemBO read(@PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for irm read received: irm Key {}", id);
		sreq.addRequestData(id);
		ServiceResponse response = irs.read(sreq);
		return getResultBO(response, IRMenuItemBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<IRMenuItemBO> readAll(@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for IR Menu Items received");
		ServiceResponse response = irs.readAll(sreq);
		return response.getResponseData(List.class);
	}

}