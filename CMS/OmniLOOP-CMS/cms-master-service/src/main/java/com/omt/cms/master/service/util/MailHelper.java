package com.omt.cms.master.service.util;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

public abstract class MailHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(MailHelper.class);    

	@Autowired MailMessageFactory mailMsgFactory;
	@Autowired
	@Qualifier("mandrillApi")
	MandrillApi mandrillApi;

	@Autowired
	@Qualifier("liveMandrillApi")
	MandrillApi liveMandrillApi;

	@Value("${mandrill.asynch}") private boolean asynch;

	public boolean isAsynch() {
		return asynch;
	}

	public void setAsynch(boolean asynch) {
		this.asynch = asynch;
	}

	public MandrillApi getApi(){
		return mandrillApi;
	}

	public MandrillMessage createMessage(){
		return mailMsgFactory.createMessage();
	}
	
	public void sendMail(MandrillMessage message){
		try {
			mandrillApi.messages().send(message, this.asynch);
		} catch (MandrillApiError e) {
			LOGGER.error("Error occurred while sending email: {}. Error details : {}", message, e);
		} catch (IOException e) {
			LOGGER.error("Error occurred while sending email: {}. Error details : {}", message, e);
		}
	}

	public void sendMail(MandrillMessage message, String tplName, HashMap<String, String> tplContent){
		try {
			MandrillMessageStatus[] status = mandrillApi.messages().sendTemplate(tplName, tplContent, message, this.asynch);
			if(status!=null && status.length > 0){
				LOGGER.info("Mail Send Response:{}",  ToStringBuilder.reflectionToString(status[0]));
			}
		} catch (MandrillApiError e) {
			LOGGER.error("Error occurred while sending email: {}. Error details : {}", message, e);
		} catch (IOException e) {
			LOGGER.error("Error occurred while sending email: {}. Error details : {}", message, e);
		}
	}

	public void sendMailLive(MandrillMessage message, String tplName, HashMap<String, String> tplContent){
		try {
			MandrillMessageStatus[] status = liveMandrillApi.messages().sendTemplate(tplName, tplContent, message, this.asynch);
			if(status!=null && status.length > 0){
				LOGGER.info("Mail Send Response:{}",  ToStringBuilder.reflectionToString(status[0]));
			}
		} catch (MandrillApiError e) {
			LOGGER.error("Error occurred while sending email: {}. Error details : {}", message, e);
		} catch (IOException e) {
			LOGGER.error("Error occurred while sending email: {}. Error details : {}", message, e);
		}
	}
}
