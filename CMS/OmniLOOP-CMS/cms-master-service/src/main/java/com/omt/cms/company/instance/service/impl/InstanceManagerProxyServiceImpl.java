package com.omt.cms.company.instance.service.impl;

import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.InstanceManagerProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.AnnouncementBO;
import com.omt.cms.core.service.bo.base.UserDetailsBO;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class InstanceManagerProxyServiceImpl extends BaseInstanceProxyService implements InstanceManagerProxyService {
	public static final String API_PREFIX = "/mobile/api/company-instances/{cpId}/{resource}";
	public static final String API_RES_URI = API_PREFIX + "/{resourceId}";
	public static final String RESOURCE = "managers";

	@Override
	public ServiceResponse listManagers(ServiceRequest request) {
		Long companyId = request.getRequestData(Long.class);
		return readAllActive(companyId, RESOURCE, API_PREFIX, AnnouncementBO.class);
	}

	@Override
	public ServiceResponse read(ServiceRequest request) {
		UserDetailsBO requestEnt = request.getRequestData(UserDetailsBO.class);
		Long companyId = requestEnt.getCompanyId();
		Long entId = requestEnt.getUserId();
		return readActive(companyId, entId, RESOURCE, API_RES_URI, UserDetailsBO.class);
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		UserDetailsBO requestEnt = request.getRequestData(UserDetailsBO.class);
		Long companyId = requestEnt.getCompanyId();
		return add(companyId, requestEnt, API_PREFIX, RESOURCE, UserDetailsBO.class);
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		UserDetailsBO requestEnt = request.getRequestData(UserDetailsBO.class);
		Long companyId = requestEnt.getCompanyId();
		Long entityId = requestEnt.getUserId();
		return update(companyId, requestEnt, entityId, API_RES_URI, RESOURCE, UserDetailsBO.class);
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		return null;
	}

}
