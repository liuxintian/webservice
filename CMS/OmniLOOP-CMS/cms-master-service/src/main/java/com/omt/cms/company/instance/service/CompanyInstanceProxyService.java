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
public interface CompanyInstanceProxyService  {

	public  ServiceResponse add(ServiceRequest request);
		
	public  ServiceResponse activate(ServiceRequest request);
	
	public  ServiceResponse deactivate(ServiceRequest request);

}
