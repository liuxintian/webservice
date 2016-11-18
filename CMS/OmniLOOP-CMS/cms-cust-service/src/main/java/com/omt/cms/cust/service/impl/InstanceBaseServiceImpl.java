package com.omt.cms.cust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;

@Component
public abstract class InstanceBaseServiceImpl {

	@Autowired
	protected CompanyInstanceOperations<CompanyInstance> ciOps;

	public ServiceResponse checkInstanceStatus(Long companyId) {
		boolean result = false;
		String resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
		CompanyInstance company = null;
		if (companyId != null) {
			company = ciOps.findById(companyId);
				if(company!=null){
					resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
					if(company.isActive()){
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
 			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}

}
