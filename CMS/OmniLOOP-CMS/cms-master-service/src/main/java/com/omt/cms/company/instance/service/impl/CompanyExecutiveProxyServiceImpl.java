package com.omt.cms.company.instance.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.CompanyExecutiveProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.ApiResource;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.CompanyExecutiveBO;

@Service
public class CompanyExecutiveProxyServiceImpl extends BaseInstanceProxyService implements CompanyExecutiveProxyService {

	@Override
	public ServiceResponse readAllActive(ServiceRequest request) {
		String resURI = ApiResource.EXECUTIVES.getValue();
		CompanyExecutiveBO reqBO = request.getRequestData(CompanyExecutiveBO.class);
		Long companyId = null;
		if(reqBO!=null){
			companyId = reqBO.getCompanyId();
			Timestamp validUntil = reqBO.getValidUntil();
			Integer status = reqBO.getStatus();
			resURI = addFilterParams(resURI, validUntil, status);
		}
		return readAllActive(companyId, resURI, SystemCodes.INSTACE_MOBILE_API_PATH_READALL, CompanyExecutiveBO.class);
	}
	
	@Override
	public ServiceResponse readActive(ServiceRequest request) {
		CompanyExecutiveBO requestEnt=request.getRequestData(CompanyExecutiveBO.class);
		Long companyId = requestEnt.getCompanyId();
		Long entId=requestEnt.getId();
		return readActive(companyId,entId, ApiResource.EXECUTIVES.getValue(), SystemCodes.INSTANCE_MOBILE_API_PATH_READ, CompanyExecutiveBO.class);
	}

}
