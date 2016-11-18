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
import com.omt.cms.user.service.UserSettingService;
import com.omt.cms.user.service.bo.UserSettingBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/users/{userId}/settings")
public class UserSettingController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserSettingController.class);    
	@Autowired UserSettingService userSetSrv;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserSettingBO> create(@PathVariable Long userId, @RequestBody @Valid UserSettingBO userReg, 
			BindingResult result,
			UriComponentsBuilder builder, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new user setting  creation received. Request Details : {}", userReg);
		userReg.setUserId(userId);
		sreq.addRequestData(userReg);
		ServiceResponse response = userSetSrv.add(sreq);
		UserSettingBO updtUr = getResultBO(response, UserSettingBO.class);
		return new ResponseEntity<UserSettingBO>(updtUr, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public UserSettingBO update(@PathVariable Long userId, @PathVariable Long id, @RequestBody UserSettingBO userReg, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserSetting update received: UserSetting Key {}", id);
		userReg.setUserId(userId);
		userReg.setId(id);
		sreq.addRequestData(userReg);
		ServiceResponse response = userSetSrv.update(sreq);
		return getResultBO(response, UserSettingBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public UserSettingBO read(@PathVariable Long userId, @PathVariable Long id,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserSetting update received: UserSetting Key {}", id);
		UserSettingBO userReg = new UserSettingBO();
		userReg.setUserId(userId);
		userReg.setId(id);
		sreq.addRequestData(userReg);
		ServiceResponse response = userSetSrv.read(sreq);
		return getResultBO(response, UserSettingBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserSettingBO> readAll(@PathVariable Long userId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for UserSetting read received: UserSetting Key {}", userId);
		UserSettingBO UserSetting=new UserSettingBO();
		UserSetting.setUserId(userId);
		sreq.addRequestData(UserSetting);
		ServiceResponse response = userSetSrv.readAll(sreq);
		return getResultBO(response, List.class);
	}
}