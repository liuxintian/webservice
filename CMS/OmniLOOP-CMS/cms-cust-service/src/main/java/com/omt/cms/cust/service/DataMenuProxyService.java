package com.omt.cms.cust.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public interface DataMenuProxyService { 

	public  ServiceResponse readAll(ServiceRequest request);
	
}
