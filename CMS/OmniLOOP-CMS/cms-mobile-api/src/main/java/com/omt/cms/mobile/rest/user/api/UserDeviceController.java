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
import com.omt.cms.user.service.UserDeviceService;
import com.omt.cms.user.service.bo.UserDeviceBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/users/{userId}/devices")
public class UserDeviceController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDeviceController.class);    
	@Autowired UserDeviceService userRegSrv;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDeviceBO> readAll(@PathVariable Long userId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserDevice read received: UserDevice Key {}", userId);
		UserDeviceBO UserDevice=new UserDeviceBO();
		UserDevice.setUserId(userId);
		sreq.addRequestData(UserDevice);
		ServiceResponse response = userRegSrv.readAll(sreq);
		return getResultBO(response, List.class);
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public UserDeviceBO read(@PathVariable Long userId, @PathVariable Long id,	@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserDevice read received: UserDevice Key {}", id);
		UserDeviceBO userReq = new UserDeviceBO();
		userReq.setUserId(userId);
		userReq.setId(id);
		sreq.addRequestData(userReq);
		ServiceResponse response = userRegSrv.read(sreq);
		return getResultBO(response, UserDeviceBO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDeviceBO> create(@PathVariable Long userId, @RequestBody @Valid UserDeviceBO userReg, 
			BindingResult result,
			UriComponentsBuilder builder, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new user device  creation received. Request Details : {}", userReg);
		userReg.setUserId(userId);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.add(sreq);
		UserDeviceBO updtUr = getResultBO(response, UserDeviceBO.class);
		return new ResponseEntity<UserDeviceBO>(updtUr, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public UserDeviceBO update(@PathVariable Long userId, @PathVariable Long id, @RequestBody UserDeviceBO userReg, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserDevice update received: UserDevice Key {}", id);
		userReg.setUserId(userId);
		userReg.setId(id);
		sreq.addRequestData(userReg);
		ServiceResponse response = userRegSrv.update(sreq);
		return getResultBO(response, UserDeviceBO.class);
	}

}