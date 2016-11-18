package com.omt.cms.master.service.test;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LoginUserBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.master.service.MasterManagerService;
import com.omt.cms.master.service.test.base.ServiceTestBase;

public class MasterManagerServiceTest extends ServiceTestBase {

	@Autowired MasterManagerService mmService;
	
	//@Test
	public void addNewManager(){
		//String name, String loginName, String password
		UserDetailsBO user = new UserDetailsBO("OMT SUPER ADMIN", "sa@omt.com", "welcome");
		ServiceRequest req = new ServiceRequest();
		req.addRequestData(user);
		ServiceResponse res = mmService.add(req);
		assertNotNull(res);
	}

	@Test
	public void login(){
		LoginUserBO login = new LoginUserBO("sa@omt.com", "welcome");
		ServiceRequest req = new ServiceRequest();
		req.addRequestData(login);
		ServiceResponse res = mmService.login(req);
		assertNotNull(res);
		UserDetailsBO result = res.getResponseData(UserDetailsBO.class);
		System.out.println(ToStringBuilder.reflectionToString(result));
		System.out.println(res.getServiceResult().getResultCode());
	}
}
