package com.omt.cms.master.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.MasterManager;
import com.omt.cms.master.repo.MasterManagerRepository;
import com.omt.cms.master.repo.op.MasterManagerOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class MasterManagerOpImpl extends BaseCrudOpImpl<MasterManager> implements MasterManagerOperations{
	
	@Autowired MasterManagerRepository mmRepo; 
	
	public MasterManagerOpImpl(){
		this.entityType = MasterManager.class;
	}
	
	@Override
	public PagingAndSortingRepository<MasterManager, Long> getRepository() {
		return mmRepo;
	}

	@Override
	public MasterManager findUser(String loginName) {
		return mmRepo.findByLoginName(loginName);
	}
	
	@Override
	public boolean loginNameExists(String loginName) {
		Long count = mmRepo.countByLoginName(loginName);
		return count > 0;
	}

	//TODO support pagination
	@Override
	public List<MasterManager> listManagers() {
		return (List<MasterManager>) mmRepo.findAll();
	}
}
