package com.omt.cms.master.admin.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.UsersLoginBO.LoginUser;
import com.omt.cms.user.service.UserService;
import com.omt.cms.user.service.bo.UserBO;

@RestController
@RequestMapping("/master-admin/api/users")
public class MasterUserController extends BaseController {

	@Autowired UserService userSrv;

	@RequestMapping(method = RequestMethod.GET, value = "/{loginName}/login-name-inuse")
	public LoginUser loginNameInUse(@PathVariable String loginName) {
		ServiceRequest request = new ServiceRequest();
		request.addRequestData(loginName);
		ServiceResponse resp = userSrv.loginNameInUse(request);
		return getResultBO(resp, LoginUser.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{loginName}/update")
	public UserBO updateLoginName(@PathVariable String loginName) {
		ServiceRequest request = new ServiceRequest();
		request.addRequestData(loginName);
		ServiceResponse resp = userSrv.updateLoginName(request);
		return getResultBO(resp, UserBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{loginName}/fp-token")
	public String getFPToken(@PathVariable String loginName) {
		ServiceRequest request = new ServiceRequest();
		request.addRequestData(loginName);
		ServiceResponse resp = userSrv.getFPToken(request);
		return getResultBO(resp, String.class);
	}

}
