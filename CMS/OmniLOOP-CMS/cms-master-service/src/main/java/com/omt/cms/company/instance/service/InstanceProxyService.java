package com.omt.cms.company.instance.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

@Service
public interface InstanceProxyService {

	public  ServiceResponse readAllActive(ServiceRequest request);

	public  ServiceResponse readActive(ServiceRequest request);

}
