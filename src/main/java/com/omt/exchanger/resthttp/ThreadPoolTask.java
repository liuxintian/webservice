package com.omt.exchanger.resthttp;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.omt.exchanger.entity.ProcessVO;
import com.omt.exchanger.entity.ProgressVO;
import com.omt.exchanger.entity.User;
import com.omt.webservice.DataCache;

/**
 * Each startprocess would start one thread processtask
 * @author tonyliu
 *
 */
public class ThreadPoolTask implements Runnable, Serializable {
	
	private Logger omtlogger = Logger.getLogger(ThreadPoolTask.class);
	
	private static final long serialVersionUID = 1L;
	
	private ProcessVO uvo;
	
    public ThreadPoolTask(ProcessVO uvo) {
    	this.setUvo(uvo);
    } 
    
    @Override
	public void run() {
		// TODO Auto-generated method stub
    	omtlogger.info("ThreadPoolTask running...");
    	// 1. Get users for this company 
    	User[] ret  = RestGetAction.RestTempGetUsers(uvo.getCpId());
    	if(ret != null && ret.length > 0){
    		
    	}
    	
    	// 2. for each users send the messages?
    	ProgressVO tmpuvo = new ProgressVO();
		tmpuvo.setTotal(150);
    	for(int i=0; i< 151; i++){
    		try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		tmpuvo.setDone(i);
    		
        	// 3. update progress for web page use
    		if(DataCache.ProgressCache != null){
    			DataCache.ProgressCache.put(uvo.getBatchid(), tmpuvo);
    		}
    	}
    	
    }

	public ProcessVO getUvo() {
		return uvo;
	}

	public void setUvo(ProcessVO uvo) {
		this.uvo = uvo;
	}


}
