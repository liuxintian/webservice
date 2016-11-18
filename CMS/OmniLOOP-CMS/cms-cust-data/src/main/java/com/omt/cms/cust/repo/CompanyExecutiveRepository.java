package com.omt.cms.cust.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.CompanyExecutive;
import com.omt.cms.cust.entity.CompanyInstance;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface CompanyExecutiveRepository extends PagingAndSortingRepository<CompanyExecutive, Long> {

	public List<CompanyExecutive> findByCompany(CompanyInstance company);

	public List<CompanyExecutive> findByCompanyAndValidUntilAfter(CompanyInstance company, Timestamp validUntil, Pageable page);

	public List<CompanyExecutive> findByCompanyAndStatus(CompanyInstance company, int status, Pageable page);

	public List<CompanyExecutive> findByCompanyAndStatusAndValidUntilAfter(CompanyInstance company, int status, Timestamp validUntil, Pageable page);

}
