package com.omt.cms.user.service.util;

import java.util.ArrayList;
import java.util.List;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MergeVar;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.omt.cms.master.service.util.MailHelper;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class UserMailHelper extends MailHelper{

	public enum Templates {
		LOCAL_USER_REGISTER("local_user_register"), USER_MAIL_VERIFY("user_mail_verify"), USER_FORGOT_PASSWORD("user_forgot_password");
		
		private String value;
		private Templates(String v){ this.value=v; }
		public String getValue(){ return value; }
	}

	public enum TemplatesDynaKeys {
		REG_VER_TOKEN("reg_ver_token"), USER_MAIL_VER_TOKEN("user_mail_ver_token"), USER_FP_TOKEN("user_fp_token"),
		USER_LOGIN_NAME("user_login_name"), USER_LOGIN_PASS("user_login_pass"), USER_RESET_PASS_URL("user_reset_pass_url")
		;
		
		private String value;
		private TemplatesDynaKeys(String v){ this.value=v; }
		public String getValue(){ return value; }
	}

	public void mailLoginDetails(String emailId, String loginName, String loginPass){
		List<MergeVar> vars = new ArrayList<>();
		vars.add(new MergeVar(TemplatesDynaKeys.USER_LOGIN_NAME.getValue(), loginName));
		vars.add(new MergeVar(TemplatesDynaKeys.USER_LOGIN_PASS.getValue(), loginPass));
		createAndSendMessage(emailId, Templates.LOCAL_USER_REGISTER.getValue(), vars);
	}

	public void sendMailVerifyEmail(String emailId, String token){
		createVarAndSendMessage(emailId, token, Templates.USER_MAIL_VERIFY.getValue(), TemplatesDynaKeys.USER_MAIL_VER_TOKEN.getValue());
	}

	public void sendForgotPasswordEmail(String emailId, String token){
		createVarAndSendMessageLive(emailId, token, Templates.USER_FORGOT_PASSWORD.getValue(), TemplatesDynaKeys.USER_FP_TOKEN.getValue(),TemplatesDynaKeys.USER_LOGIN_NAME.getValue(),TemplatesDynaKeys.USER_RESET_PASS_URL.getValue());
	}
	
	public void createVarAndSendMessage(String emailId, String token, String tplName, String mvKey){
			List<MergeVar> vars = new ArrayList<>();
			vars.add(new MergeVar(mvKey, token));
			createAndSendMessage(emailId, tplName, vars);
	}

	public void createAndSendMessage(String emailId, String tplName, List<MergeVar> vars){
		MandrillMessage message = createMessage();
		if(message!=null){
			ArrayList<Recipient> recipients = new ArrayList<Recipient>();
			Recipient recipient = new Recipient();
			recipient.setEmail(emailId);
			recipients.add(recipient);
			message.setTo(recipients);
			message.setGlobalMergeVars(vars);
			sendMail(message, tplName, null);
		}
	}
	
	public void createVarAndSendMessageLive(String emailId, String token, String tplName, String mvKey,String acctNameKey,String resetUrl){
		String recoveryUrlBase = "http://cms.omnimarkettide.com/recovery";
		String loginNameEncoded = null;
		try {
			loginNameEncoded = URLEncoder.encode(emailId, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			loginNameEncoded = "";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MergeVar> vars = new ArrayList<>();
		vars.add(new MergeVar(mvKey, token));
		vars.add(new MergeVar(acctNameKey, emailId));
		vars.add(new MergeVar(resetUrl, recoveryUrlBase+"?recovery="+loginNameEncoded+"&emailTocken="+token));
		MandrillMessage message = createMessage();
		if(message!=null){
			ArrayList<Recipient> recipients = new ArrayList<Recipient>();
			Recipient recipient = new Recipient();
			recipient.setEmail(emailId);
			recipients.add(recipient);
			message.setTo(recipients);
			message.setGlobalMergeVars(vars);
			sendMailLive(message, tplName, null);
		}
	}
}
