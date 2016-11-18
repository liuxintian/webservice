/**
 * 
 */
package com.omt.cms.master.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.IRMenu;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface IRMenuRepository extends PagingAndSortingRepository<IRMenu, Long> {
	
	public List<IRMenu> findByCompanyId(Long companyId);
	
	public IRMenu findByCompanyIdAndMenuId(Long companyId, Long menuId);

	public List<IRMenu> findByCompanyIdAndValidUntilAfter(Long companyId, Timestamp validUntil, Pageable page);
	
}