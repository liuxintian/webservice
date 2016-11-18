package com.omt.cms.company.instance.service.impl;

import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.AnnouncementProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.ApiResource;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.AnnouncementBO;

@Service
public class AnnouncementProxyServiceImpl extends BaseInstanceProxyService implements AnnouncementProxyService {

	@Override
	public ServiceResponse readAllActive(ServiceRequest request) {
		String resURI = ApiResource.ANNOUNCEMENTS.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		Long companyId = null;
		if(reqBO!=null){
			companyId = reqBO.cpId;
			resURI = addFilterParams(resURI, reqBO);
		}
		return readAllActive(companyId, resURI, SystemCodes.INSTACE_MOBILE_API_PATH_READALL, AnnouncementBO.class);
	}

	@Override
	public ServiceResponse readActive(ServiceRequest request) {
		AnnouncementBO requestEnt=request.getRequestData(AnnouncementBO.class);
		Long companyId = requestEnt.getCompanyId();
		Long entId=requestEnt.getId();
		return readActive(companyId,entId,  ApiResource.ANNOUNCEMENTS.getValue(), SystemCodes.INSTANCE_MOBILE_API_PATH_READ, AnnouncementBO.class);
	}
}