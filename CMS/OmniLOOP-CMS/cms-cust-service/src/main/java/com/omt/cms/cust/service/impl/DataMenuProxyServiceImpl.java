package com.omt.cms.cust.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriTemplate;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.cust.service.DataMenuProxyService;
import com.omt.cms.master.service.bo.CompanyBO;
import com.omt.cms.master.service.bo.DataMenuBO;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class DataMenuProxyServiceImpl implements DataMenuProxyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataMenuProxyServiceImpl.class);    
	public static final String DATAMENU_API_PATH ="/datamenus";
	public static final String COMPANY_API_PATH ="/companies/{id}";

	@Value("${cms.master.oapi.url}") private String moapiURL;
	@Autowired private RestOperations restOps;

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		List<DataMenuBO> respBO=null;
		try {
			String url  = buildEndPointURL(DATAMENU_API_PATH);
			LOGGER.info("Instance URL Is :{}", url);
			ParameterizedTypeReference<List<DataMenuBO>> typeRef = new ParameterizedTypeReference<List<DataMenuBO>>(){ };
			ResponseEntity<List<DataMenuBO>> responseEntity = restOps.exchange(url, HttpMethod.GET, null, typeRef);
			respBO = responseEntity.getBody();
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			if(respBO!=null){
				CompanyBO cp=readCompanyDetails(request);
				if(cp!=null){
					/*expandMenuUrl(cp.getShareRegistryId(),respBO);*/
					expandMenuUrl(cp.getCompanyTicker(),respBO);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				} else {
					respBO=null;
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

	private CompanyBO readCompanyDetails(ServiceRequest request) {
		CompanyBO respBO=null;
		try {
			CompanyBO reqBO=request.getRequestData(CompanyBO.class);
			if(reqBO != null && reqBO.getId()!=null){
				String urlp = buildEndPointURL(COMPANY_API_PATH);
				UriTemplate uri=new UriTemplate(urlp);
				String url=uri.expand(reqBO.getId()).toString();
				respBO = invokeService(reqBO, url, CompanyBO.class, HttpMethod.GET);

				LOGGER.info("Instance URL Is :{}", url);
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred while checking login-name :", e);

		}
		return respBO;
	}

	private void expandMenuUrl(String asxCode,List<DataMenuBO> menus){

		for(DataMenuBO dm:menus){

			UriTemplate uri=new UriTemplate(dm.getUrl());
			String url=uri.expand(asxCode).toString();
			dm.setUrl(url);

		}
	}


}
