package com.omt.cms.user.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

@Service
public interface MobileUserLogService extends CrudService {

	public ServiceResponse readByDateRange(ServiceRequest request);
}
