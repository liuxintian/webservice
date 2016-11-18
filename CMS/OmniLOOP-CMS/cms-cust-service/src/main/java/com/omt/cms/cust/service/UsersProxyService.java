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
public interface UsersProxyService extends CrudService{

	public  ServiceResponse userLoginNamesInUse(ServiceRequest request);
	
	public ServiceResponse addUsers(ServiceRequest request);
}
