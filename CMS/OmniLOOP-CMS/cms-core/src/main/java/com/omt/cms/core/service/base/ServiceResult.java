package com.omt.cms.core.service.base;

import com.omt.cms.core.service.bo.base.SerializableObject;


/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class ServiceResult extends SerializableObject{

	private static final long serialVersionUID = 1L;
	
	private boolean result = true;	
	private String resultCode;
	private String resultMessage;
	
	public ServiceResult(boolean result){
		this.result = result;
	}

	public ServiceResult(String resultCode){
		this.resultCode = resultCode;
	}
	
	public ServiceResult(boolean result, String resultCode){
		this.result = result;
		this.resultCode = resultCode;
	}

	public ServiceResult(String resultCode, String resultMessage){
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getResultCode() {
		return resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
