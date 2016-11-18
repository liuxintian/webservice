package com.omt.cms.master.service.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MergeVar;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;

@Component
public class ManagerMailHelper extends MailHelper{
	
	public static final String FP_URI="login?recovery=1&emailTocken=%s";
	public static final String FP_TEMPLATE_NAME="cms_master_admin_forgot_pass";
	
	public static final String FP_LINK_KEY = "reset_password_link";
	
	@Value("${master.admin.cms.url}") private String masterAdminAppUrl;

	public String getMasterAdminAppUrl() {
		return masterAdminAppUrl;
	}

	public void setMasterAdminAppUrl(String masterAdminAppUrl) {
		this.masterAdminAppUrl = masterAdminAppUrl;
	}
	
	public void sendFPEmail(String emailId, String token){
		MandrillMessage message = createMessage();
		if(message!=null){
			ArrayList<Recipient> recipients = new ArrayList<Recipient>();
			Recipient recipient = new Recipient();
			recipient.setEmail(emailId);
			recipients.add(recipient);
			message.setTo(recipients);
			String urlVal = this.getMasterAdminAppUrl() + String.format(FP_URI, token);
			List<MergeVar> vars = new ArrayList<>();
			MergeVar var = new MergeVar(FP_LINK_KEY, urlVal);
			vars.add(var);
			message.setGlobalMergeVars(vars);
			sendMail(message, FP_TEMPLATE_NAME, null);
			
		}
	}
}
