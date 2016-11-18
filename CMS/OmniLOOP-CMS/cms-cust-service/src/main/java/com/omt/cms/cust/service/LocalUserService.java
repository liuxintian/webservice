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
public interface LocalUserService extends CrudService {

	public  ServiceResponse readAll(ServiceRequest request);
	
	public  ServiceResponse sendMail(ServiceRequest request);

	public  ServiceResponse updateShareSubscription(ServiceRequest request);

	public  ServiceResponse addToMasterAndInstance(ServiceRequest request);

	public  ServiceResponse readAllByType(ServiceRequest request);

	public  ServiceResponse readAllWithDeviceInfo(ServiceRequest request);

}
