package com.omt.cms.company.instance.service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public interface InstanceManagerProxyService extends CrudService{

	public  ServiceResponse listManagers(ServiceRequest request);

}
