package com.omt.cms.company.instance.service.impl;

import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.DocumentLinksMediaProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.ApiResource;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.DocumentLinksMediaBO;

@Service
public class DocumentLinksMediaProxyServiceImpl extends BaseInstanceProxyService implements DocumentLinksMediaProxyService {

	@Override
	public ServiceResponse readAllActive(ServiceRequest request) {
		String resURI = ApiResource.DOCUMENT_LINKS.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		Long companyId = null;
		if(reqBO!=null){
			companyId = reqBO.cpId;
			resURI = addFilterParams(resURI, reqBO);
		}
		return readAllActive(companyId, resURI, SystemCodes.INSTACE_MOBILE_API_PATH_READALL, DocumentLinksMediaBO.class);
	}

	@Override
	public ServiceResponse readActive(ServiceRequest request) {
		DocumentLinksMediaBO requestEnt=request.getRequestData(DocumentLinksMediaBO.class);
		Long companyId = requestEnt.getCompanyId();
		Long entId = requestEnt.getId();
		return readActive(companyId,entId, ApiResource.DOCUMENT_LINKS.getValue(), SystemCodes.INSTANCE_MOBILE_API_PATH_READ, DocumentLinksMediaBO.class);
	}

}
