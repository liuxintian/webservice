package com.omt.cms.master.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

@Service
public interface CompanyService extends CrudService {

	public  ServiceResponse readAll(ServiceRequest request);
	
	public  ServiceResponse push(ServiceRequest request);
	
	public  ServiceResponse readByASXCode(ServiceRequest request);
	
	public  ServiceResponse tickerNameExists(ServiceRequest request);

	public  ServiceResponse readByCompanyType(ServiceRequest request);

	public  ServiceResponse listAsxRegistry(ServiceRequest request);
	
	public  ServiceResponse readAllWithFilters(ServiceRequest request);
	
	public  ServiceResponse readAllAdmin(ServiceRequest request);

}
