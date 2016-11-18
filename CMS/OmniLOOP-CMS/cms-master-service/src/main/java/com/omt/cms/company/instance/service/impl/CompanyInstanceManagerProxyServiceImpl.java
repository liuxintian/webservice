package com.omt.cms.company.instance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.CompanyInstanceManagerProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.op.CompanyInstanceRegistryOperations;

@Service
public class CompanyInstanceManagerProxyServiceImpl extends BaseInstanceProxyService implements CompanyInstanceManagerProxyService {

	@Autowired CompanyInstanceRegistryOperations cirOps;

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result=false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ServiceResponse response=null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if(reqUser!=null){
			Company cp = cpOps.findById(reqUser.getCompanyId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(cp!=null && cp.isActive()){
				response = add(cp, reqUser, "company-instances/" + cp.getId() + "/managers", UserDetailsBO.class);
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
		return readAllActive(companyId,"managers",SystemCodes.INSTANCE_API_PATH,UserDetailsBO.class);

	}
	@Override
	public ServiceResponse disable(ServiceRequest request) {
		boolean result=false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ServiceResponse response=null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if(reqUser!=null){
			Company cp = cpOps.findById(reqUser.getCompanyId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(cp!=null && cp.isActive()){
				response = disable(cp, reqUser, "company-instances/" + cp.getId() + "/managers/disable", UserDetailsBO.class);
			}
		}
		if(response==null){
			response = new ServiceResponse(result, resultCode);
		}
		return response;
	}

	@Override
	public ServiceResponse enable(ServiceRequest request) {
		boolean result=false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		ServiceResponse response=null;
		UserDetailsBO reqUser = request.getRequestData(UserDetailsBO.class);
		if(reqUser!=null){
			Company cp = cpOps.findById(reqUser.getCompanyId());
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(cp!=null && cp.isActive()){
				response = enable(cp, reqUser, "company-instances/" + cp.getId() + "/managers/enable", UserDetailsBO.class);
			}
		}
		if(response==null){
			response = new ServiceResponse(result, resultCode);
		}
		return response;
	}


}
