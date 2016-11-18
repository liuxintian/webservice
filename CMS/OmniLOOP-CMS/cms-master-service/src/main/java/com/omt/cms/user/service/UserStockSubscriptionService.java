package com.omt.cms.user.service;

import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.CrudService;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;

@Service
public interface UserStockSubscriptionService extends CrudService {

	public  ServiceResponse deleteByAsxCode(ServiceRequest request);

	public  ServiceResponse deleteTickers(ServiceRequest request);

}
