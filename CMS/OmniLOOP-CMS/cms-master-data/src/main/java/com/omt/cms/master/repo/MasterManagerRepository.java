/**
 * 
 */
package com.omt.cms.master.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.MasterManager;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface MasterManagerRepository extends PagingAndSortingRepository<MasterManager, Long> {

	public MasterManager findByLoginName(String loginName);
	
	public Long countByLoginName(String loginName);
	
	public List<MasterManager> findByValidUntilAfter(Timestamp validUntil, Pageable page);
}
