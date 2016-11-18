package com.omt.cms.mobile.rest.oapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.base.ServiceResult;
import com.omt.cms.core.service.bo.base.UsersLoginBO.LoginUser;
import com.omt.cms.user.entity.MobileUserLog;
import com.omt.cms.user.service.MobileUserLogService;
import com.omt.cms.user.service.UserService;
import com.omt.cms.user.service.bo.UserBO;

@RestController
@RequestMapping("/mobile/oapi/users")
public class UsersController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
	@Autowired private UserService userService;
	@Autowired private DefaultTokenServices tokenService;
	@Autowired private MobileUserLogService logService;

    @RequestMapping(method = RequestMethod.POST, value="/signup")    
    public ResponseEntity<UserBO> singupUser(@RequestBody UserBO reqReg, UriComponentsBuilder builder){
    	LOGGER.debug("New registration request received for user" + reqReg.getLoginName());
    	ServiceRequest sreq = new ServiceRequest();
    	sreq.addRequestData(reqReg);
    	ServiceResponse response = userService.register(sreq);
    	HttpHeaders headers = null;
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	if(response.getServiceResult().isResult()){
    		UserBO respUser = response.getResponseData(UserBO.class);
        	LOGGER.debug("Newly created user Id : {}", respUser.getId());
            headers = new HttpHeaders();
            headers.setLocation(builder.path("/mobile/oapi/users/{id}").buildAndExpand(respUser.getId()).toUri());
            status = HttpStatus.CREATED;
    	}	
        return new ResponseEntity<UserBO>(getResultBO(response, UserBO.class), headers, status);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/verify-email")    
    public UserBO verifyEMail(@RequestBody UserBO reqReg){
    	ServiceRequest request = new ServiceRequest();
    	request.addRequestData(reqReg);
    	ServiceResponse resp = userService.verifyEMail(request);
    	return getResultBO(resp, UserBO.class);
    }	

    @RequestMapping(method = RequestMethod.PUT, value="/verify-phone")    
    public UserBO verifyPhone(@RequestBody UserBO reqReg){
    	ServiceRequest request = new ServiceRequest();
    	request.addRequestData(reqReg);
    	ServiceResponse resp = userService.verifyPhone(request);
    	return getResultBO(resp, UserBO.class);
    }	

	@RequestMapping(method = RequestMethod.PUT, value = "/reset-password")
	public UserBO resetPassword(@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for reset password received: Manager Key {}", bo.getLoginName());
		sreq.addRequestData(bo);
		ServiceResponse response = userService.resetPassword(sreq);
		return getResultBO(response, UserBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/forgot-password")
	public UserBO forgotPassword(@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for forgot password received: Manager Key {}", bo.getLoginName());
		sreq.addRequestData(bo);
		ServiceResponse response = userService.forgotPassword(sreq);
		return getResultBO(response, UserBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/verify-fp-token")
	public UserBO verifyFPToken(@RequestBody UserBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for forgot password token validation received");
		sreq.addRequestData(bo);
		ServiceResponse response = userService.verifyFPToken(sreq);
		return getResultBO(response, UserBO.class);
	}
	
/*	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserBO> readAll(@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		ServiceResponse response = userService.listUsers(sreq);
		return getResultBO(response, List.class);
	}
*/
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public UserBO read(@PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		sreq.addRequestData(id);
		ServiceResponse response = userService.read(sreq);
		return getResultBO(response, UserBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public UserBO update(@PathVariable Long id, @RequestBody UserBO reqBO, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		reqBO.setId(id);
		sreq.addRequestData(reqBO);
		ServiceResponse response = userService.update(sreq);
		return getResultBO(response, UserBO.class);
	}
	
    @RequestMapping(method = RequestMethod.PUT, value="/{loginName}/resend-email-vtoken")    
    public UserBO resendEmailVerifyToken(@PathVariable String loginName){
    	ServiceRequest request = new ServiceRequest();
    	request.addRequestData(loginName);
    	ServiceResponse resp = userService.resendEmailVerifyToken(request);
    	return getResultBO(resp, UserBO.class);
    }	

    @RequestMapping(method = RequestMethod.PUT, value="/{loginName}/resend-phone-vtoken")    
    public UserBO resendPhoneVerifyToken(@PathVariable String loginName){
    	ServiceRequest request = new ServiceRequest();
    	request.addRequestData(loginName);
    	ServiceResponse resp = userService.resendPhoneVerifyToken(request);
    	return getResultBO(resp, UserBO.class);
    }	

    @RequestMapping(method = RequestMethod.PUT, value="/change-email")    
    public UserBO changeUserEmail(@RequestBody UserBO reqUser){
    	ServiceRequest request = new ServiceRequest();
    	request.addRequestData(reqUser);
    	ServiceResponse resp = userService.changeUserEmail(request);
    	return getResultBO(resp, UserBO.class);
    }	

    @RequestMapping(method = RequestMethod.PUT, value="/change-phone")    
    public UserBO changeUserPhone(@RequestBody UserBO reqUser){
    	ServiceRequest request = new ServiceRequest();
    	request.addRequestData(reqUser);
    	ServiceResponse resp = userService.changeUserPhone(request);
    	return getResultBO(resp, UserBO.class);
    }	

    @RequestMapping(value = "/oauth/token/revoke", method = RequestMethod.PUT)
	public @ResponseBody void revokeAccessToken(@RequestParam("tokenValue") String tokenValue){
		tokenService.revokeToken(tokenValue);
	}

    @RequestMapping(method = RequestMethod.GET, value="/login-name-inuse/{loginName}")    
    public LoginUser loginNameInUse(@PathVariable String loginName){
    	ServiceRequest request = new ServiceRequest();
    	request.addRequestData(loginName);
    	ServiceResponse resp = userService.loginNameInUse(request);
    	return getResultBO(resp, LoginUser.class);
    }	

    @RequestMapping(method = RequestMethod.POST, value="/log")    
    public ServiceResult addUserLog(@RequestBody MobileUserLog reqLog){
    	LOGGER.debug("User log message is received");
    	ServiceRequest sreq = new ServiceRequest();
    	sreq.addRequestData(reqLog);
    	ServiceResponse response = logService.add(sreq);
        return response.getServiceResult();
    }

}
