package com.omt.webservice.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.omt.config.StaticConfig;

public class MailSender {
	private static Logger omtlogger = Logger.getLogger(MailSender.class);
	private static final Map<String, String> headers = new HashMap<>();
	static {
		headers.put("X-MC-Tags", StaticConfig.mailTags);
	}
	/**
	 * Send mail with specified params
	 * @param from who this is from
	 * @param to who this is for
	 * @param subject subject of the mail
	 * @param text body of the mail
	 */
	public static void send(MailTaskVO uvo){
		final String from = StaticConfig.mailUsername;
		final String pwd = StaticConfig.mailPassword;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "login");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", StaticConfig.mailHost);
		props.put("mail.smtp.port", StaticConfig.mailPort);
		
 		Session mailSession = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pwd);
			}
		});
 		
		Message simpleMessage = new MimeMessage(mailSession);
		for (String key : headers.keySet()) {
	        String value = headers.get(key);
	        try {
				simpleMessage.addHeader(key, value);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(uvo.getTo());
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		try {
			simpleMessage.setFrom(fromAddress);
			simpleMessage.setRecipient(RecipientType.TO, toAddress);
			simpleMessage.setSubject(uvo.getSubject());
			simpleMessage.setText(uvo.getBody());
						
			Transport.send(simpleMessage);			
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}
	
	public static void sendMandrill(MailTaskVO uvo){
		MandrillApi mandrillApi = new MandrillApi(StaticConfig.mailPassword);
		MandrillMessage message = new MandrillMessage();
		message.setSubject(uvo.getSubject());
		message.setText(uvo.getBody());
		message.setAutoText(true);
		message.setFromEmail(StaticConfig.mailAlias);
		message.setFromName(StaticConfig.mailTitle);
		message.setAutoHtml(true);
		
		// add recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		// one recipient
		Recipient recipient = new Recipient();
		recipient.setEmail(uvo.getTo());
		recipients.add(recipient);
		
		message.setTo(recipients);
		message.setPreserveRecipients(true);
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add(StaticConfig.mailTags);
		message.setTags(tags);
		
		// message.setHeaders(headers);
		// ... add more message details if you want to!
		// then ... send
		try {
			MandrillMessageStatus[] messageStatusReports = mandrillApi.messages().send(message, false);
			if(messageStatusReports != null){
				for(MandrillMessageStatus status: messageStatusReports){
					omtlogger.info("send mail to:" + status.getEmail() + "--status:"+status.getStatus());
				}
			}
		} catch (MandrillApiError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String args[]){
		MailTaskVO uvo = new MailTaskVO();
		uvo.setBody("Test");
		uvo.setSubject("Hello");
		uvo.setTo("r.gandhi@omnimarkettide.com");
		sendMandrill(uvo);
		
//		MandrillApi mandrillApi = new MandrillApi("Pmg4Nq6D1Da7IDd0sYKtIA"); 
//		MandrillApi mandrillApi = new MandrillApi("kcej9x_WZPjqhveM-QPN7g"); 
//
//		MandrillUserInfo user;
//		try {
//			user = mandrillApi.users().info();
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			System.out.println( gson.toJson(user) );
//		} catch (MandrillApiError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
