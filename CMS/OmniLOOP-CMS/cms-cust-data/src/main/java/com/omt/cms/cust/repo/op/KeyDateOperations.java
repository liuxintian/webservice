package com.omt.cms.cust.repo.op;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.KeyDate;
import com.omt.cms.repo.op.base.CrudOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface KeyDateOperations extends CrudOperations<KeyDate> {

	public static final String ENTITY_TABLE_NAME = "keydates";
	
	public List<KeyDate> findByCompany(CompanyInstance company);
	
	public List<KeyDate> findByAllWithLimit();

	public KeyDate findByCompanyAndId(CompanyInstance company, Long id);

	public List<KeyDate> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd);

	public List<KeyDate> findByCompanyStatus(CompanyInstance company, int status);

	public List<KeyDate> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd);

	public List<KeyDate> findByStatus(int status);

	public List<KeyDate> findByValidUntil(Timestamp vtd);

	public List<KeyDate> findByStatusValidUntil(int status, Timestamp vtd);
	
	public List<KeyDate> findByFilters(FilterCriteriaBO filters);

	public List<KeyDate> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);

	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);

}
