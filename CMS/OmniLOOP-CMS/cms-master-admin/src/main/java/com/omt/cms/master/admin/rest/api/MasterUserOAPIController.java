package com.omt.cms.master.admin.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.UsersBO;
import com.omt.cms.core.service.bo.base.UsersLoginBO;
import com.omt.cms.user.service.UserService;

@RestController
@RequestMapping("/master/oapi")
public class MasterUserOAPIController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MasterOAPIController.class);    
	@Autowired UserService userSrv;

	@RequestMapping(method = RequestMethod.PUT, value = "/users/login-names-inuse")
	public UsersLoginBO loginNamesInUse(@RequestBody UsersLoginBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for checking login names received");
		sreq.addRequestData(bo);
		ServiceResponse response = userSrv.loginNamesInUse(sreq);
		return getResultBO(response, UsersLoginBO.class);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users/mutli-users")
	public UsersBO addUsers(@RequestBody UsersBO users, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for adding multiple users received");
		sreq.addRequestData(users);
		ServiceResponse response = userSrv.addUsers(sreq);
		return getResultBO(response, UsersBO.class);
	}

}
