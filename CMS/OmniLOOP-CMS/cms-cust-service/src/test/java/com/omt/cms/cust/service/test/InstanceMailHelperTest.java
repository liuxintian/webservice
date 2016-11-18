package com.omt.cms.cust.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.cust.entity.LocalUser;
import com.omt.cms.cust.service.test.base.ServiceTestBase;
import com.omt.cms.cust.service.util.InstanceMailHelper;

public class InstanceMailHelperTest extends ServiceTestBase {

	@Autowired private InstanceMailHelper mailHelper;
	
	@Test
	public void test(){
		LocalUser user = new LocalUser();
		user.setUserEmail("kalgudi@gmail.com");
		user.setUserName("Shiva Kalgudi");
		List<LocalUser> users = new ArrayList<>(1);
		users.add(user);
		mailHelper.sendMessageToLocalUsers("Welocome", users, "SuryyaNameinstance", "<H1>Welcome</H1>");
	}
}
