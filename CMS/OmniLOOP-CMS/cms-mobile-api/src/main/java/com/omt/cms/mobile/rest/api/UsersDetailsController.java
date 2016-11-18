package com.omt.cms.mobile.rest.api;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.bo.base.UserDetailsBO;

@RestController
@RequestMapping("/me")
public class UsersDetailsController extends BaseController{

	@RequestMapping(method = RequestMethod.GET)
	public UserDetailsBO getUserDetails(@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		return sreq.getUser();
	}

}
