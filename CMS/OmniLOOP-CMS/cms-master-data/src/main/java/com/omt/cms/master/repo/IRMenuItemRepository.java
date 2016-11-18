/**
 * 
 */
package com.omt.cms.master.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.IRMenuItem;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface IRMenuItemRepository extends PagingAndSortingRepository<IRMenuItem, Long> {
	
	public List<IRMenuItem> findByStatus(int status);
	
	public List<IRMenuItem> findByValidUntilAfter(Timestamp validUntil, Pageable page);
}
