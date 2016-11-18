/**	
 * 
 */
package com.omt.cms.master.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.AsxRegistry;
import com.omt.cms.master.repo.AsxRegistryRepository;
import com.omt.cms.master.repo.op.AsxRegistryOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class AsxRegistryOpImpl extends BaseCrudOpImpl<AsxRegistry> implements AsxRegistryOperations {

	@Autowired private AsxRegistryRepository cpRepo;

	public AsxRegistryOpImpl() {
		this.entityType = AsxRegistry.class;
	}

	@Override
	public PagingAndSortingRepository<AsxRegistry, Long> getRepository() {
		return cpRepo;
	}

	@Override
	public AsxRegistry findByCompanyTicker(String companyTicker) {
		List<AsxRegistry> companies = cpRepo.findByCompanyTicker(companyTicker);
		if(companies!=null && !companies.isEmpty()){
			return companies.get(0);
		}
		return null;
	}

	@Override
	public boolean tickerNameExists(String companyTicker) {
		return cpRepo.countByCompanyTicker(companyTicker) > 0;
	}
	
}
