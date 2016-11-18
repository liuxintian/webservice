/**
 * 
 */
package com.omt.cms.master.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.AsxRegistry;

/**
 * @author muragesh
 *
 */
@Repository
public interface AsxRegistryRepository extends PagingAndSortingRepository<AsxRegistry, Long> {

	public List<AsxRegistry> findByCompanyTicker(String companyTicker);
	
	public Long countByCompanyTicker(String companyTicker);
	
}
