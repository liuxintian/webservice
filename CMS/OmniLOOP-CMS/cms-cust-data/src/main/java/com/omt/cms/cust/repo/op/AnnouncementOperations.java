package com.omt.cms.cust.repo.op;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.Announcement;
import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.repo.op.base.CrudOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public interface AnnouncementOperations extends CrudOperations<Announcement> {
	
	public static final String ENTITY_TABLE_NAME = "announcements";

	public List<Announcement> findByCompany(CompanyInstance company);

	public List<Announcement> findByAllWithLimit();

	public Announcement findByCompanyAndId(CompanyInstance company, Long id);

	public List<Announcement> findByCompanyValidUntil(CompanyInstance company, Timestamp vtd);

	public List<Announcement> findByCompanyStatus(CompanyInstance company, int status);

	public List<Announcement> findByCompanyStatusValidUntil(CompanyInstance company, int status, Timestamp vtd);

	public List<Announcement> findByStatus(int status);
	
	public List<Announcement> findByValidUntil(Timestamp vtd);

	public List<Announcement> findByStatusValidUntil(int status, Timestamp vtd);

	public List<Announcement> findByFilters(FilterCriteriaBO filters);
	
	public List<Announcement> findByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);
	
	public Long countByFiltersAdmins(CompanyInstance company, FilterCriteriaBO filters);

}
