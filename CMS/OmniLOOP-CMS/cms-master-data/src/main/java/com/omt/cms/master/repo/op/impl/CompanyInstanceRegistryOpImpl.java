/**
 * 
 */
package com.omt.cms.master.repo.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.Company;
import com.omt.cms.master.entity.CompanyInstanceRegistry;
import com.omt.cms.master.repo.CompanyInstanceRegistryRepository;
import com.omt.cms.master.repo.op.CompanyInstanceRegistryOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class CompanyInstanceRegistryOpImpl extends BaseCrudOpImpl<CompanyInstanceRegistry> implements CompanyInstanceRegistryOperations{

	@Autowired CompanyInstanceRegistryRepository cirRepo;

	public CompanyInstanceRegistryOpImpl() {
		this.entityType = CompanyInstanceRegistry.class;
	}

	@Override
	public PagingAndSortingRepository<CompanyInstanceRegistry, Long> getRepository() {
		return cirRepo;
	}

	@Override
	public CompanyInstanceRegistry findByCompany(Company cp){
		return cirRepo.findByCompany(cp);
	}
	
	@Override
	public boolean instanceNameExists(String instanceName) {
		return cirRepo.countByInstanceName(instanceName) > 0;
	}

}
