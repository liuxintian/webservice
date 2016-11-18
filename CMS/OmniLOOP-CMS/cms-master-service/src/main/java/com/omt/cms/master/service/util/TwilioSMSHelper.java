package com.omt.cms.master.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class TwilioSMSHelper {
	@Autowired private TwilioRestClient twilioClient;
	MessageFactory messageFactory = null;
	@Value("${twilio.sms.from}") private String smsFrom;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSMSHelper.class);    

	@PostConstruct
	public void afterPropertiesSet() throws Exception{
		if(twilioClient!=null){
			messageFactory = twilioClient.getAccount().getMessageFactory();
		}
	}
	
	public String sendSMS(String smsTo, String smsContent){
		try{
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair(RequestParams.FROM.getValue(), smsFrom));
		    params.add(new BasicNameValuePair(RequestParams.TO.getValue(), smsTo));
		    params.add(new BasicNameValuePair(RequestParams.BODY.getValue(), smsContent));
		    Message message = messageFactory.create(params);
		    LOGGER.info("Message Id:{}", message.getSid());
		    return message.getSid();
		}catch(Exception e){
			LOGGER.warn("Error occurred while sending SMS:{}", e);
		}
		return StringUtils.EMPTY;
	}
	
	protected enum RequestParams {
		BODY("Body"), TO("To"), FROM("From");
		private String value;
		private RequestParams(String v){ this.value=v; }
		public String getValue(){ return value; }
	}

}
