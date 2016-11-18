package com.omt.cms.company.instance.service.impl;

import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.KeyDateProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.ApiResource;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.KeyDateBO;

@Service
public class KeyDateProxyServiceImpl extends BaseInstanceProxyService implements KeyDateProxyService {

	@Override
	public ServiceResponse readAllActive(ServiceRequest request) {
		String resURI = ApiResource.KEY_DATES.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		Long companyId = null;
		if(reqBO!=null){
			companyId = reqBO.cpId;
			resURI = addFilterParams(resURI, reqBO);
		}
		return readAllActive(companyId, resURI, SystemCodes.INSTACE_MOBILE_API_PATH_READALL,KeyDateBO.class);
	}

	@Override
	public ServiceResponse readActive(ServiceRequest request) {
		KeyDateBO requestKeyDate=request.getRequestData(KeyDateBO.class);
		Long companyId = requestKeyDate.getCompanyId();
		Long entId=requestKeyDate.getId();
		return readActive(companyId,entId, ApiResource.KEY_DATES.getValue(), SystemCodes.INSTANCE_MOBILE_API_PATH_READ,KeyDateBO.class);
	}

}
