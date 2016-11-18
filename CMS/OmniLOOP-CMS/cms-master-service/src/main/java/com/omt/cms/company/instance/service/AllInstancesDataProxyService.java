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
public interface AllInstancesDataProxyService {
	
	public  ServiceResponse readAllInstancesAnnouncements(ServiceRequest request);

	public  ServiceResponse readAllInstancesKeyDates(ServiceRequest request);

}
