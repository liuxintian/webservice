package com.omt.cms.master.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.master.service.test.base.ServiceTestBase;
import com.omt.cms.master.service.util.ManagerMailHelper;

public class MailHelperTest extends ServiceTestBase {
	
	@Autowired ManagerMailHelper mmHelper;
	
	@Test
	public void testFPMail(){
		mmHelper.sendFPEmail("shiva.kalgudi@gmail.com", "welcome");
	}

}
