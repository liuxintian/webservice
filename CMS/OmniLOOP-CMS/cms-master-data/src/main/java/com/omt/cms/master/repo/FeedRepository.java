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

import com.omt.cms.master.entity.Feed;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface FeedRepository extends PagingAndSortingRepository<Feed, Long> {
	
	public List<Feed> findByStatus(int status);
	
	public List<Feed> findByValidUntilAfter(Timestamp validUntil, Pageable page);

	public List<Feed> findByStatus(int status, Pageable page);

	public List<Feed> findByStatusAndValidUntilAfter(int status, Timestamp validUntil, Pageable page);

	@Query("SELECT e FROM #{#entityName} e WHERE "
	 		+ "LOWER(e.feedTitle) LIKE %?1% "
	 		+ "OR LOWER(e.feedURL) LIKE %?1% "
	 		+ "OR LOWER(e.feedTitle) LIKE %?1% "
	 		+ "OR LOWER(e.feedCompany) LIKE %?1% "
	 		+ "OR LOWER(e.feedContact) LIKE %?1% "
	 		+ "OR LOWER(e.tagRole) LIKE %?1% "
	 		)
	public List<Feed> searchByCriteria(String title, Pageable page);

	@Query("SELECT count(e) FROM #{#entityName} e WHERE "
	 		+ "LOWER(e.feedTitle) LIKE %?1% "
	 		+ "OR LOWER(e.feedURL) LIKE %?1% "
	 		+ "OR LOWER(e.feedTitle) LIKE %?1% "
	 		+ "OR LOWER(e.feedCompany) LIKE %?1% "
	 		+ "OR LOWER(e.feedContact) LIKE %?1% "
	 		+ "OR LOWER(e.tagRole) LIKE %?1% "
	 		)
	public Long countByCriteria(String title);

}
