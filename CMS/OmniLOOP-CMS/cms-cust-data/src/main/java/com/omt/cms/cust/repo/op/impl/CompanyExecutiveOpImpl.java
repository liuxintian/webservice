package com.omt.cms.cust.repo.op.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.CompanyExecutive;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.CompanyExecutiveRepository;
import com.omt.cms.cust.repo.op.CompanyExecutiveOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * 
 * @author Shiva Kalgudi
 *
 */
@Component
public class CompanyExecutiveOpImpl extends BaseCrudOpImpl<CompanyExecutive> implements CompanyExecutiveOperations{
	
	@Autowired CompanyExecutiveRepository ceRepo;

	public CompanyExecutiveOpImpl(){
		this.entityType = CompanyExecutive.class;
	}

	@Override
	public PagingAndSortingRepository<CompanyExecutive, Long> getRepository() {
		return ceRepo;
	}
	
	@Override
	public List<CompanyExecutive> findByCompany(CompanyInstance company) {
		return ceRepo.findByCompany(company);
	}
	
	@Override
	public CompanyExecutive findByCompanyAndId(CompanyInstance company, Long id) {
		CompanyExecutive exec = ceRepo.findOne(id);
		long cpId = company.getId().longValue();
		if(exec!=null){
			long exCPId = exec.getCompany().getId().longValue();
			if(cpId==exCPId){
				return exec;
			}
		}
		return null;
	}

	@Override
	public List<CompanyExecutive> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd) {
		PageRequest pr = createPageRequest();
		return ceRepo.findByCompanyAndValidUntilAfter(company, vtd, pr);
	}

	@Override
	public List<CompanyExecutive> findByCompanyStatus(CompanyInstance company, int status) {
		PageRequest pr = createPageRequest();
		return ceRepo.findByCompanyAndStatus(company, status, pr);
	}

	@Override
	public List<CompanyExecutive> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd) {
		PageRequest pr = createPageRequest();
		return ceRepo.findByCompanyAndStatusAndValidUntilAfter(company, status, vtd, pr);
	}
	
}
