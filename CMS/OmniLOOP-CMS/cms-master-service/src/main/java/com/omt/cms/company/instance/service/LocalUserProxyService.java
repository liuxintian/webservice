package com.omt.cms.company.instance.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public interface LocalUserProxyService  {

	public  ServiceResponse add(ServiceRequest request);
	
	public  ServiceResponse readAllActive(ServiceRequest request);

	public  ServiceResponse readAllByType(ServiceRequest request);

	public  ServiceResponse readAllActiveByAsxCode(ServiceRequest request);

	public  ServiceResponse readActive(ServiceRequest request);
 	
	public  ServiceResponse update(ServiceRequest request);

	public  ServiceResponse updateSubscriptionStatus(ServiceRequest request);
	
}
