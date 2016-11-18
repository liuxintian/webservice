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
import com.omt.cms.user.service.UserRegistrationService;
import com.omt.cms.user.service.bo.UserRegistrationBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/users/{userId}/registrations")
public class UserRegistrationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);    
	@Autowired UserRegistrationService userRegSrv;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserRegistrationBO> create(@PathVariable Long userId, @RequestBody @Valid UserRegistrationBO userReg, 
			BindingResult result,
			UriComponentsBuilder builder, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new user registered creation received. Request Details : {}", userReg);
		userReg.setUserId(userId);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.add(sreq);
		UserRegistrationBO updtUr = getResultBO(response, UserRegistrationBO.class);
		return new ResponseEntity<UserRegistrationBO>(updtUr, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public UserRegistrationBO update(@PathVariable Long userId, @PathVariable Long id, @RequestBody UserRegistrationBO userReg, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserRegistration update received: UserRegistration Key {}", id);
		userReg.setUserId(userId);
		userReg.setId(id);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.update(sreq);
		return getResultBO(response, UserRegistrationBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public UserRegistrationBO read(@PathVariable Long userId, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserRegistration read received: UserRegistration Key {}", id);
		UserRegistrationBO userReg = new UserRegistrationBO();
		userReg.setUserId(userId);
		userReg.setId(id);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.read(sreq);
		return getResultBO(response, UserRegistrationBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserRegistrationBO> readAll(@PathVariable Long userId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserRegistration read received: UserRegistration Key {}", userId);
		UserRegistrationBO UserRegistration=new UserRegistrationBO();
		UserRegistration.setUserId(userId);
		sreq.addRequestData(UserRegistration);
		ServiceResponse response = userRegSrv.readAll(sreq);
		return getResultBO(response, List.class);
	}
}