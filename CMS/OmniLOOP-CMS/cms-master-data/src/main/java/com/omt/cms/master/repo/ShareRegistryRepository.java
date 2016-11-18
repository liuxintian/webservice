/**
 * 
 */
package com.omt.cms.master.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.ShareRegistry;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface ShareRegistryRepository extends PagingAndSortingRepository<ShareRegistry, Long> {

	public List<ShareRegistry> findByStatus(int status);

	public List<ShareRegistry> findByValidUntilAfter(Timestamp validUntil, Pageable page);
	
	@Query("SELECT e FROM #{#entityName} e WHERE "
	 		+ "LOWER(e.shareRegistry) LIKE %?1% "
	 		+ "OR LOWER(e.srURL) LIKE %?1% "
	 		)
	public List<ShareRegistry> searchByCriteria(String title, Pageable page);

	@Query("SELECT count(e) FROM #{#entityName} e WHERE "
	 		+ "LOWER(e.shareRegistry) LIKE %?1% "
	 		+ "OR LOWER(e.srURL) LIKE %?1% "
	 		)
	public Long countByCriteria(String title);
	
}
