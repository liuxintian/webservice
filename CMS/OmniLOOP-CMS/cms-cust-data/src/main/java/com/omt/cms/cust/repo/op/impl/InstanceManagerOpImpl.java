package com.omt.cms.cust.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.InstanceManager;
import com.omt.cms.cust.repo.InstanceManagerRepository;
import com.omt.cms.cust.repo.op.InstanceManagerOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class InstanceManagerOpImpl extends BaseCrudOpImpl<InstanceManager> implements InstanceManagerOperations{
	
	@Autowired InstanceManagerRepository mmRepo; 
	
	public InstanceManagerOpImpl(){
		this.entityType = InstanceManager.class;
	}
	
	@Override
	public PagingAndSortingRepository<InstanceManager, Long> getRepository() {
		return mmRepo;
	}

	@Override
	public InstanceManager findUser(String loginName) {
		return mmRepo.findByLoginName(loginName);
	}
	
	@Override
	public boolean loginNameExists(String loginName) {
		Long count = mmRepo.countByLoginName(loginName);
		return count > 0;
	}

	//TODO support pagination
	@Override
	public List<InstanceManager> listManagers() {
		return (List<InstanceManager>) mmRepo.findAll();
	}

	@Override
	public List<InstanceManager> findByCompany(CompanyInstance company) {
		return mmRepo.findByCompany(company);
	}

	@Override
	public InstanceManager findByCompanyAndId(CompanyInstance company, Long id) {
		InstanceManager im = mmRepo.findOne(id);
		long cpId = company.getId().longValue();
		long exCPId = im.getCompany().getId().longValue();
		if(cpId==exCPId){
			return im;
		}
		return null;
	}
}
