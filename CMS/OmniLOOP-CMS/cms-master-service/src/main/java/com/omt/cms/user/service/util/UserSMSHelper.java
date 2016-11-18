package com.omt.cms.user.service.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.omt.cms.master.service.util.TwilioSMSHelper;

@Component
public class UserSMSHelper extends TwilioSMSHelper {

	@Value("${user.sms.phone.verify.body}") private String phoneVerBody;
	@Value("${user.sms.phone.fp.body}") private String fpBody;
	
	public void sendPhoneVerifySMS(String phoneNbr, String token){
		String smsMsg = phoneVerBody + ":" + token;
		sendSMS(phoneNbr, smsMsg);
	}

	public void sendFPVerifySMS(String phoneNbr, String token){
		String smsMsg = fpBody + ":" + token;
		sendSMS(phoneNbr, smsMsg);
	}
}
