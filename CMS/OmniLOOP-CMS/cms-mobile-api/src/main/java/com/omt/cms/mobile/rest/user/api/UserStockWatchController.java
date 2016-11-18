package com.omt.cms.mobile.rest.user.api;

import java.util.List;

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
import com.omt.cms.core.service.base.ServiceResult;
import com.omt.cms.user.service.UserStockWatchService;
import com.omt.cms.user.service.bo.UserStockWatchBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/users/{userId}/watchlists")
public class UserStockWatchController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserStockWatchController.class);    
	@Autowired UserStockWatchService uwrs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserStockWatchBO> create(@PathVariable Long userId, @RequestBody @Valid UserStockWatchBO uw, 
			BindingResult result,
			UriComponentsBuilder builder, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new user watch list creation received. Request Details : {}", uw);
		uw.setUserId(userId);
		sreq.addRequestData(uw);
		ServiceResponse response = uwrs.add(sreq);
		UserStockWatchBO updtUw = getResultBO(response, UserStockWatchBO.class);
		return new ResponseEntity<UserStockWatchBO>(updtUw, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{uwId}/delete")
	public UserStockWatchBO delete(@PathVariable Long userId, @PathVariable Long uwId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for uw delete received: uw Key {}", uwId);
		UserStockWatchBO uw = new UserStockWatchBO();
		uw.setUserId(userId);
		uw.setId(uwId);
		sreq.addRequestData(uw);
		ServiceResponse response = uwrs.delete(sreq);
		return getResultBO(response, UserStockWatchBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/ticker/{companyTicker}/delete")
	public ServiceResult deleteByAsxCode(@PathVariable Long userId, @PathVariable String companyTicker,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for uw delete received: asx-code Key {}", companyTicker);
		UserStockWatchBO uw = new UserStockWatchBO();
		uw.setUserId(userId);
		uw.setCompanyTicker(companyTicker);
		sreq.addRequestData(uw);
		ServiceResponse response = uwrs.deleteByAsxCode(sreq);
		return response.getServiceResult();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserStockWatchBO> read(@PathVariable Long userId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for uw read received: uw Key {}", userId);
		UserStockWatchBO uw=new UserStockWatchBO();
		uw.setUserId(userId);
		sreq.addRequestData(uw);
		ServiceResponse response = uwrs.read(sreq);
		return getResultBO(response, List.class);
	}
}