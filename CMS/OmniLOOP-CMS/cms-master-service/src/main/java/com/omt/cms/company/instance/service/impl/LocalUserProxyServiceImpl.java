package com.omt.cms.company.instance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.LocalUserProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.core.service.bo.base.UsersBO;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.op.CompanyInstanceRegistryOperations;
import com.omt.cms.master.repo.op.CompanyOperations;
import com.omt.cms.user.service.UserService;
import com.omt.cms.user.service.bo.UserBO;
import com.omt.cms.user.service.util.LocalUserHelper;

@Service
public class LocalUserProxyServiceImpl extends BaseInstanceProxyService implements LocalUserProxyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalUserProxyServiceImpl.class);    
	@Autowired CompanyInstanceRegistryOperations cirOps;
	@Autowired UserService userService;
	@Autowired LocalUserHelper luHelper;
	@Autowired CompanyOperations compOps;
	
	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result=false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ServiceResponse response=null;
		LocalUserBO reqUser = request.getRequestData(LocalUserBO.class);
		if(reqUser!=null && reqUser.getCompanyId()!=null){
			UserBO userBO = luHelper.createFrom(reqUser);
			if(userBO!=null){
				Long companyId = reqUser.getCompanyId();
				UsersBO reqUsers = new UsersBO();
				List<LocalUserBO> userLst = new ArrayList<>(1);
				userLst.add(reqUser);
				reqUsers.setCpId(companyId);
				reqUsers.setUsers(userLst);
				ServiceRequest addUsrReq = new ServiceRequest();
				addUsrReq.addRequestData(reqUsers);
				ServiceResponse addUserResp = userService.addUsers(addUsrReq);
				if (addUserResp != null && addUserResp.getServiceResult().isResult()) {
					UsersBO resUsers = addUserResp.getResponseData(UsersBO.class);
					if (resUsers != null && !resUsers.getUsers().isEmpty()) {
						LocalUserBO resBO = resUsers.getUsers().get(0);
						if (resBO != null && resBO.getId() != null) {
							reqUser.setId(resBO.getId());
							Long cpId = reqUser.getCompanyId();
							Company cp = cpOps.findById(cpId);
							resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
							if (cp != null && cp.isActive()) {
								response = add(cpId, reqUser, SystemCodes.INSTACE_MOBILE_API_PATH_READALL, "local-users", LocalUserBO.class);
							}
						}
					}

				} else {
					response = addUserResp;
					LOGGER.error("Adding of new user failed");
				}
			}
		}

		if(response==null){
			response = new ServiceResponse(result, resultCode);
		}
		
		return response;
	}
	
	@Override
	public ServiceResponse readAllActive(ServiceRequest request) {
		Long companyId = request.getRequestData(Long.class);
		return readAllActive(companyId,"local-users",SystemCodes.INSTACE_MOBILE_API_PATH_READALL, LocalUserBO.class);
	}

	@Override
	public ServiceResponse readAllByType(ServiceRequest request) {
		String resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
		boolean result = false;
		LocalUserBO reqBO = request.getRequestData(LocalUserBO.class);
		if(StringUtils.isNotBlank(reqBO.getCompanyName())){
			Company cmp = compOps.findByCompanyTicker(reqBO.getCompanyName().toLowerCase());
			if(cmp!=null){
				Long companyId = cmp.getId();
				boolean ss = false;
				if(reqBO.getShareSubscriber()!=null){
					ss = reqBO.getShareSubscriber();
				}
				String endPoint = "local-users/type/" + ss; 
				return readAllActive(companyId, endPoint, SystemCodes.INSTACE_MOBILE_API_PATH_READALL, LocalUserBO.class);
			}
		}
		return new ServiceResponse(result, resultCode);
	}

	@Override
	public ServiceResponse readAllActiveByAsxCode(ServiceRequest request) {
		String resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
		boolean result = false;
		String asxCode = request.getRequestData(String.class);
		if(StringUtils.isNotBlank(asxCode)){
			Company cmp = compOps.findByCompanyTicker(asxCode.toLowerCase());
			if(cmp!=null){
				return readAllActive(cmp.getId(),"local-users",SystemCodes.INSTACE_MOBILE_API_PATH_READALL, LocalUserBO.class);
			}
		}
		return new ServiceResponse(result, resultCode);
	}

	@Override
	public ServiceResponse readActive(ServiceRequest request) {
		LocalUserBO reqBO=request.getRequestData(LocalUserBO.class);
		Long companyId = reqBO.getCompanyId();
		Long entId=reqBO.getId();
		return readActive(companyId,entId,"local-users",SystemCodes.INSTANCE_MOBILE_API_PATH_READ, LocalUserBO.class);
	}
	
	@Override
	public ServiceResponse update(ServiceRequest request) {
  		LocalUserBO reqBO=request.getRequestData(LocalUserBO.class);
		Long companyId = reqBO.getCompanyId();
		return update(companyId,reqBO,reqBO.getId(),SystemCodes.INSTANCE_MOBILE_API_PATH_READ,"local-users", LocalUserBO.class);
  
	}
	
	@Override
	public ServiceResponse updateSubscriptionStatus(ServiceRequest request) {
  		LocalUserBO reqBO=request.getRequestData(LocalUserBO.class);
		Long companyId = reqBO.getCompanyId();
		String endPoint = SystemCodes.INSTANCE_MOBILE_API_PATH_READ + "/subscribe-status";
		return update(companyId, reqBO, reqBO.getId(), endPoint, "local-users", LocalUserBO.class);
   }

}
