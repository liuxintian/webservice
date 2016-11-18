package com.omt.cms.cust.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.InstanceManager;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface InstanceManagerOperations extends CrudOperations<InstanceManager>{

	public InstanceManager findUser(String loginName);
	
	public boolean loginNameExists(String loginName);
	
	public List<InstanceManager> listManagers();
	
	public List<InstanceManager> findByCompany(CompanyInstance company);
	
	public InstanceManager findByCompanyAndId(CompanyInstance company, Long id);

}
