package com.omt.cms.master.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.entity.CompanyInstanceRegistry;
import com.omt.cms.master.repo.op.CompanyInstanceRegistryOperations;

@Component
public class CompanyInstanceRestClient extends InstanceRestClient {

	@Autowired private CompanyInstanceRegistryOperations cirOps;
	
	private static final String INSTANCE_URI = "/oapi/company-instances/%s";
	
	public ServiceResponse pushCompanyToInstance(Company company){
		if(company!=null && company.isActive()){
			CompanyInstanceRegistry registry = cirOps.findByCompany(company);
			if(registry!=null){
				String urlPrefix = registry.getInstanceURL();
				String endPoint = urlPrefix + String.format(INSTANCE_URI, company.getId());
				Company instance = new Company();
				instance.setId(company.getId());
				instance.setCompanyName(company.getCompanyName());
				invoke(instance, endPoint, HttpMethod.POST);
			}
		}
		
		return null;
	}
}
