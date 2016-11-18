package com.omt.cms.master.repo.op;

import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.Company;
import com.omt.cms.master.entity.CompanyInstanceRegistry;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface CompanyInstanceRegistryOperations extends CrudOperations<CompanyInstanceRegistry> {
	
	public CompanyInstanceRegistry findByCompany(Company cp);

	public boolean instanceNameExists(String instanceName);

}
