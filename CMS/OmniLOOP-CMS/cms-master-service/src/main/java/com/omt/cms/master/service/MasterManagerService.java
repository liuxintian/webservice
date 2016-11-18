package com.omt.cms.master.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

/***
 * 
 * @author Shiva Kalgudi
 *
 */


@Service
public interface MasterManagerService extends CrudService {

	public  ServiceResponse login(ServiceRequest request);

	public  ServiceResponse listManagers(ServiceRequest request);
	
	public  ServiceResponse resetPassword(ServiceRequest request);
	
	public  ServiceResponse forgotPassword(ServiceRequest request);
	
	public  ServiceResponse verifyFPToken(ServiceRequest request);
	
}
