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
public interface CompanyInstanceManagerProxyService  {

	public  ServiceResponse add(ServiceRequest request);
	public  ServiceResponse readAllActive(ServiceRequest request);
	public  ServiceResponse disable(ServiceRequest request);
	public  ServiceResponse enable(ServiceRequest request);
}
