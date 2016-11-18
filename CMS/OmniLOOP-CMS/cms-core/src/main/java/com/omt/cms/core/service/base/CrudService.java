package com.omt.cms.core.service.base;

import org.springframework.stereotype.Service;



/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public interface CrudService {

	public  ServiceResponse read(ServiceRequest request);

	public  ServiceResponse add(ServiceRequest request);

	public  ServiceResponse update(ServiceRequest request);

	public  ServiceResponse delete(ServiceRequest request);

}
