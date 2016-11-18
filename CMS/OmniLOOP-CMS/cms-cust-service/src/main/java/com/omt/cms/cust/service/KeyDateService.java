package com.omt.cms.cust.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public interface KeyDateService extends CrudService {

	public  ServiceResponse readAll(ServiceRequest request);
	
	public  ServiceResponse readAllWithFilters(ServiceRequest request);

	public  ServiceResponse readAllInstancesData(ServiceRequest request);
	
	
}
