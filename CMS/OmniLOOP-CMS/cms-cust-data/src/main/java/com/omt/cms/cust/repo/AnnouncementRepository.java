package com.omt.cms.cust.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.Announcement;
import com.omt.cms.cust.entity.CompanyInstance;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement, Long> {

	public List<Announcement> findByCompany(CompanyInstance company);

	public List<Announcement> findByCompanyAndValidUntilAfter(CompanyInstance company, Timestamp validUntil, Pageable page);

	public List<Announcement> findByCompanyAndStatus(CompanyInstance company, int status, Pageable page);

	public List<Announcement> findByCompanyAndStatusAndValidUntilAfter(CompanyInstance company, int status, Timestamp validUntil, Pageable page);

	public List<Announcement> findByStatus(int status, Pageable page);

	public List<Announcement> findByValidUntilAfter(Timestamp validUntil, Pageable page);

	public List<Announcement> findByStatusAndValidUntilAfter(int status, Timestamp validUntil, Pageable page);

	@Query(value="select * from announcements  where company_id = ?1 and tag_role @@to_tsquery(?2)", nativeQuery = true)
	public List<Announcement> searchByTagRole(Long companyId, String term);
	
	@Query("SELECT e FROM #{#entityName} e WHERE e.company=?1 AND ("
	 		+ "LOWER(e.documentLink) LIKE %?2% "
	 		+ "OR LOWER(e.documentTitle) LIKE %?2% "
	 		+ "OR LOWER(e.tagRole) LIKE %?2% )"
	 		)
	public List<Announcement> searchByCriteria(CompanyInstance company, String title, Pageable page);

	@Query("SELECT count(e) FROM #{#entityName} e WHERE  e.company=?1 AND ("
	 		+ "LOWER(e.documentLink) LIKE %?2% "
	 		+ "OR LOWER(e.documentTitle) LIKE %?2% "
	 		+ "OR LOWER(e.tagRole) LIKE %?2% )"
	 		)
	public Long countByCriteria(CompanyInstance company, String title);

	public Long countByCompany(CompanyInstance company);

	public List<Announcement> findByCompany(CompanyInstance company, Pageable page);

}
