package com.omt.webservice.mail;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * Each startprocess would start one thread processtask
 * @author tonyliu
 *
 */
public class ThreadMailTask implements Runnable, Serializable {
	
	private Logger omtlogger = Logger.getLogger(ThreadMailTask.class);
	
	private static final long serialVersionUID = 1L;
	
	private MailTaskVO uvo;
	
    public ThreadMailTask(MailTaskVO uvo) {
    	this.uvo = uvo;
    } 
    
    @Override
	public void run() {
		// TODO Auto-generated method stub
    	omtlogger.info("start to send email to:"+uvo.getTo());
    	MailSender.sendMandrill(uvo);
    	omtlogger.info("end to send email to:"+uvo.getTo());
    }

}
