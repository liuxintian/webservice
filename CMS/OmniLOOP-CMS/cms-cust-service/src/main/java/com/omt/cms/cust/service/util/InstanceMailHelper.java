package com.omt.cms.cust.service.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MergeVar;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MergeVarBucket;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.omt.cms.cust.entity.LocalUser;

@Component
public class InstanceMailHelper extends MailHelper{
	
	public static final String FP_URI="login?recovery=1&emailTocken=%s";
	public static final String FP_TEMPLATE_NAME="cms_instance_admin_forgot_pass";
	public static final String FP_LINK_KEY = "reset_password_link";
	public static final String MAIL_BODY = "mail_body_content";
	public static final String MAIL_USERNAME = "userName";
	
	@Value("${cms.instance.admin.url}") private String instanceAdminAppUrl;

	public String getInstanceAdminAppUrl() {
		return instanceAdminAppUrl;
	}

	public void setInstanceAdminAppUrl(String masterAdminAppUrl) {
		this.instanceAdminAppUrl = masterAdminAppUrl;
	}
	
	public void sendFPEmail(String emailId, String token){
		MandrillMessage message = createMessage();
		if(message!=null){
			ArrayList<Recipient> recipients = new ArrayList<Recipient>();
			Recipient recipient = new Recipient();
			recipient.setEmail(emailId);
			recipients.add(recipient);
			message.setTo(recipients);
			String urlVal = this.getInstanceAdminAppUrl() + String.format(FP_URI, token);
			List<MergeVar> vars = new ArrayList<>();
			vars.add(new MergeVar(FP_LINK_KEY, urlVal));
			message.setGlobalMergeVars(vars);
			sendMail(message, FP_TEMPLATE_NAME, null);
		}
	}
	
	public void sendMessageToLocalUsers(String subject, List<LocalUser> lus, String companyName, String message){
		List<MergeVar> vars = new ArrayList<>();
		vars.add(new MergeVar(MAIL_BODY, message));
		String tplName = companyName + "_users";
		createAndSendMessage(subject, lus, tplName, vars);
	}

	private void createAndSendMessage(String subject, List<LocalUser> lus, String tplName, List<MergeVar> vars){
		MandrillMessage message = createMessage();
		message.setSubject(subject);
		if(message!=null && lus!=null && !lus.isEmpty()){
			ArrayList<Recipient> recipients = new ArrayList<Recipient>();
			List<MergeVarBucket> mergeVars = new ArrayList<>();
			for(LocalUser lu : lus){
				if(StringUtils.isNotBlank(lu.getUserEmail())){
					Recipient recipient = new Recipient();
					recipient.setEmail(lu.getUserEmail());
					recipient.setType(Recipient.Type.BCC);
					recipients.add(recipient);
					MergeVarBucket mvb = new MergeVarBucket();
					mvb.setRcpt(lu.getUserEmail());
					MergeVar[] mv = new MergeVar[1];
					mv[0] = new MergeVar(MAIL_USERNAME, lu.getUserName());
					mvb.setVars(mv);
					mergeVars.add(mvb);
				}
			}
			message.setTo(recipients);
			message.setGlobalMergeVars(vars);
			message.setMergeVars(mergeVars);
			sendMail(message, tplName, null);
		}
	}
}
