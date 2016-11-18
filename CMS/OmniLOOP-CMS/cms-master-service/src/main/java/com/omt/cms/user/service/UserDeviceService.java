package com.omt.cms.user.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

@Service
public interface UserDeviceService extends CrudService {

	public ServiceResponse readAll(ServiceRequest request);
}
