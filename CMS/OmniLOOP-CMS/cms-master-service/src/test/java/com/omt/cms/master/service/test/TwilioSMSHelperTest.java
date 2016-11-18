package com.omt.cms.master.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.master.service.test.base.ServiceTestBase;
import com.omt.cms.user.service.util.UserSMSHelper;

public class TwilioSMSHelperTest  extends ServiceTestBase {

	@Autowired private UserSMSHelper helper;
	
	@Test
	public void test(){
		helper.sendSMS("+919343144455", "Test SMS from Twilio-Shiva");
	}
}
