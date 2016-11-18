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
import com.omt.cms.user.service.UserRegisteredService;
import com.omt.cms.user.service.bo.UserRegisteredBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/users/{userId}/registeries")
public class UserRegisteredController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisteredController.class);    
	@Autowired UserRegisteredService userRegSrv;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserRegisteredBO> create(@PathVariable Long userId, @RequestBody @Valid UserRegisteredBO userReg, 
			BindingResult result,
			UriComponentsBuilder builder, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new user registered creation received. Request Details : {}", userReg);
		userReg.setUserId(userId);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.add(sreq);
		UserRegisteredBO updtUr = getResultBO(response, UserRegisteredBO.class);
		return new ResponseEntity<UserRegisteredBO>(updtUr, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public UserRegisteredBO update(@PathVariable Long userId, @PathVariable Long id, @RequestBody UserRegisteredBO userReg, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserRegistered update received: UserRegistered Key {}", id);
		userReg.setUserId(userId);
		userReg.setId(id);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.update(sreq);
		return getResultBO(response, UserRegisteredBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public UserRegisteredBO read(@PathVariable Long userId, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserRegistered read received: UserRegistered Key {}", id);
		UserRegisteredBO userReg = new UserRegisteredBO();
		userReg.setUserId(userId);
		userReg.setId(id);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.read(sreq);
		return getResultBO(response, UserRegisteredBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserRegisteredBO> readAll(@PathVariable Long userId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserRegistered read received: UserRegistered Key {}", userId);
		UserRegisteredBO UserRegistered=new UserRegisteredBO();
		UserRegistered.setUserId(userId);
		sreq.addRequestData(UserRegistered);
		ServiceResponse response = userRegSrv.readAll(sreq);
		return getResultBO(response, List.class);
	}
}