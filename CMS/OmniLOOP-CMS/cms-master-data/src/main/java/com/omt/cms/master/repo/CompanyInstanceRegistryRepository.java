/**
 * 
 */
package com.omt.cms.master.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.Company;
import com.omt.cms.master.entity.CompanyInstanceRegistry;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface CompanyInstanceRegistryRepository extends PagingAndSortingRepository<CompanyInstanceRegistry, Long> {
	
	public CompanyInstanceRegistry findByCompany(Company cp);
	
	public long countByInstanceName(String instanceName);
}
