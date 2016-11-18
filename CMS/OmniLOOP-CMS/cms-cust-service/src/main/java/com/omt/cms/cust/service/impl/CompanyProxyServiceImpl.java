package com.omt.cms.cust.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriTemplate;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.cust.service.CompanyProxyService;
import com.omt.cms.master.service.bo.CompanyBO;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class CompanyProxyServiceImpl implements CompanyProxyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyProxyServiceImpl.class);    
	public static final String COMPANY_API_PATH ="/companies/{id}";
 	
 	@Value("${cms.master.oapi.url}") private String moapiURL;
	@Autowired private RestOperations restOps;
	
	@Override
	public ServiceResponse readCompanyDetails(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyBO respBO=null;
		try {
 			CompanyBO reqBO=request.getRequestData(CompanyBO.class);
			if(reqBO != null && reqBO.getId()!=null){
				String urlp = buildEndPointURL(COMPANY_API_PATH);
				UriTemplate uri=new UriTemplate(urlp);
				String url=uri.expand(reqBO.getId()).toString();
			
				LOGGER.info("Instance URL Is :{}", url);
				respBO = invokeService(reqBO, url, CompanyBO.class, HttpMethod.GET);
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
				if(respBO!=null){
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name :", e);
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(respBO);
		return response;
	}

	private <T> T invokeService(T reqBO, String url, Class<T> type, HttpMethod method) {
  		ResponseEntity<T> respEntity = restOps.exchange(url, method, null, type);
		return respEntity.getBody();
	}

	 
	private String buildEndPointURL(String apiOp) {
		StringBuilder urlBuilder = new StringBuilder(moapiURL);
 		urlBuilder.append(apiOp);
		String url = urlBuilder.toString();
		return url;
	}
	
 	
	 

}
