package com.omt.cms.cust.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.GsonHelper;
import com.omt.cms.core.common.SystemCodes.UserStatus;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.core.service.bo.base.UsersBO;
import com.omt.cms.core.service.bo.base.UsersLoginBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.LocalUser;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.repo.op.LocalUserOperations;
import com.omt.cms.cust.service.UsersProxyService;
import com.omt.cms.cust.service.bo.mapper.LocalUserMapper;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class UsersProxyServiceImpl implements UsersProxyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersProxyServiceImpl.class);    
	public static final String USER_API_PATH ="/users";
	public static final String API_EP_USERS_NOT_INT_USE ="/login-names-inuse";
	public static final String API_EP_MULTI_USERS ="/mutli-users";
	
	@Autowired CompanyInstanceOperations<CompanyInstance> ciOps;
	@Autowired LocalUserOperations luOps;
	@Autowired LocalUserMapper mapper;
	@Value("${cms.master.oapi.url}") private String moapiURL;
	@Autowired private RestOperations restOps;
	
	@Override
	public ServiceResponse userLoginNamesInUse(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UsersLoginBO reqBO = request.getRequestData(UsersLoginBO.class);
		UsersLoginBO respBO = null;
		try {
			if(reqBO != null && !reqBO.getUsers().isEmpty()){
				String url = buildEndPointURL(API_EP_USERS_NOT_INT_USE);
				LOGGER.info("Instance URL Is :{}", url);
				respBO = invokeService(reqBO, url, UsersLoginBO.class, HttpMethod.PUT);
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
		HttpHeaders headers = createHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(reqBO), headers);
		ResponseEntity<T> respEntity = restOps.exchange(url, method, requestEntity, type);
		return respEntity.getBody();
	}

	@Override
	public ServiceResponse addUsers(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		UsersBO reqBO = request.getRequestData(UsersBO.class);
		UsersBO respBO = null;
		try {
			if(reqBO!=null && !reqBO.getUsers().isEmpty() && reqBO.getCpId()!=null){
				Long companyId = reqBO.getCpId();
				CompanyInstance company = ciOps.findById(companyId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						String url = buildEndPointURL(API_EP_MULTI_USERS);
						LOGGER.info("Instance URL Is :{}", url);
						respBO = invokeService(reqBO, url, UsersBO.class, HttpMethod.POST);
						resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
						if(respBO!=null){
							for(LocalUserBO lu:respBO.getUsers()){
								if(lu.getId() != null){
									lu.setDefaultValidUntil();
									LocalUser entity = mapper.createNewEntityFromBO(lu, null);
									entity.setCreatedOn(DateHelper.getCurTimestamp());
									entity.setPwdHash(null);
									entity.setCompany(company);
									entity.setUserStatus(UserStatus.INVITED.getValue());
									entity.setUserInvitedDT(DateHelper.getCurTimestamp());
									luOps.add(entity);
									
									//set additional data
									lu.setUserInvited(true);
									lu.setUserInvitedDT(entity.getUserInvitedDT());
									lu.setCompanyId(companyId);
								}
							}
							result = true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
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

	private String buildEndPointURL(String apiOp) {
		StringBuilder urlBuilder = new StringBuilder(moapiURL);
		urlBuilder.append(USER_API_PATH);
		urlBuilder.append(apiOp);
		String url = urlBuilder.toString();
		return url;
	}
	
	@Override
	public ServiceResponse read(ServiceRequest request) {
		return null;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		return null;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		return null;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return null;
	}

	public String getMoapiURL() {
		return moapiURL;
	}

	public void setMoapiURL(String moapiURL) {
		this.moapiURL = moapiURL;
	}
	
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
