package com.omt.cms.cust.repo.op;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.CompanyExecutive;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.repo.op.base.CrudOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface CompanyExecutiveOperations extends CrudOperations<CompanyExecutive> {
	
	public List<CompanyExecutive> findByCompany(CompanyInstance company);
	
	public CompanyExecutive findByCompanyAndId(CompanyInstance company, Long id);
	
	public List<CompanyExecutive> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd);

	public List<CompanyExecutive> findByCompanyStatus(CompanyInstance company, int status);

	public List<CompanyExecutive> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd);

}
