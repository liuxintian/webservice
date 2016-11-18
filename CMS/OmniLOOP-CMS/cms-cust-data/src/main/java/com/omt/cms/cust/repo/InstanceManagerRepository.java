/**
 * 
 */
package com.omt.cms.cust.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.InstanceManager;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface InstanceManagerRepository extends PagingAndSortingRepository<InstanceManager, Long> {

	public InstanceManager findByLoginName(String loginName);
	
	public Long countByLoginName(String loginName);
	
	public List<InstanceManager> findByCompany(CompanyInstance company);
	
	public List<InstanceManager> findByCompanyAndValidUntilAfter(CompanyInstance company, Timestamp validUntil, Pageable page);
	
	public List<InstanceManager> findByValidUntilAfter(Timestamp validUntil, Pageable page);

	public List<InstanceManager> findByStatus(int status, Pageable page);

	public List<InstanceManager> findByStatusAndValidUntilAfter(int status, Timestamp validUntil, Pageable page);

	public List<InstanceManager> findByCompanyAndStatusAndValidUntilAfter(CompanyInstance company, int status, Timestamp validUntil, Pageable page);

}
