package com.omt.cms.core.service.base;



/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class ServiceResponse extends ServiceAttributes{

	private static final long serialVersionUID = 1L;
	ServiceResult serResult = null;
	
	public ServiceResponse(boolean result, String resultCode){
		super();
		serResult = new ServiceResult(result,resultCode);
	}

	public void addResponseData(Object responseData){
		addAttribute(ServiceCommonAttributes.RESPONSE_DATA.getValue(), responseData);
	}
	
	public <T> T getResponseData(Class<T> type){
		return getObjectAttribute(ServiceCommonAttributes.RESPONSE_DATA.getValue(), type);
	}

	public Object getResponseData(){
		return getObjectAttribute(ServiceCommonAttributes.RESPONSE_DATA.getValue());
	}

	public ServiceResult getServiceResult(){
		return this.serResult;
	}
}
