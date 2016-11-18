package com.omt.cms.cust.repo.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.CompanyInstanceRepository;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */
@Component
public class CompanyInstanceOpImpl implements CompanyInstanceOperations<CompanyInstance>{

	@Autowired private CompanyInstanceRepository repo;
	
	@Override
	public CompanyInstance findById(Long entityId) {
		return repo.findOne(entityId);
	}
	
	@Override
	public CompanyInstance add(CompanyInstance entity) {
		return repo.save(entity);
	}

	@Override
	public CompanyInstance update(CompanyInstance entity) {
		return repo.save(entity);
	}

	@Override
	public CompanyInstance delete(CompanyInstance entity) {
		return repo.save(entity);
	}
}
