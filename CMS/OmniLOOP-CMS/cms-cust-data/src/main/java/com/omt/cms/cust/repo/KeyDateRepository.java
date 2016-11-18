/**
 * 
 */
package com.omt.cms.cust.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.KeyDate;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface KeyDateRepository extends PagingAndSortingRepository<KeyDate, Long> {

	public List<KeyDate> findByCompany(CompanyInstance company);

	public List<KeyDate> findByStatus(int status, Pageable page);

	public List<KeyDate> findByValidUntilAfter(Timestamp validUntil, Pageable page);

	public List<KeyDate> findByStatusAndValidUntilAfter(int status, Timestamp validUntil, Pageable page);

	public List<KeyDate> findByCompanyAndValidUntilAfter(CompanyInstance company, Timestamp validUntil, Pageable page);

	public List<KeyDate> findByCompanyAndStatus(CompanyInstance company, int status, Pageable page);

	public List<KeyDate> findByCompanyAndStatusAndValidUntilAfter(CompanyInstance company, int status, Timestamp validUntil, Pageable page);

	@Query(value="select * from keydates  where company_id = ?1 and tag_role @@to_tsquery(?2)", nativeQuery = true)
	public List<KeyDate> searchByTagRole(Long companyId, String term);

	@Query("SELECT e FROM #{#entityName} e WHERE e.company=?1 AND ("
	 		+ "LOWER(e.eventDetails) LIKE %?2% "
	 		+ "OR LOWER(e.eventLocation) LIKE %?2% "
	 		+ "OR LOWER(e.eventSubTitle) LIKE %?2% "
	 		+ "OR LOWER(e.eventTitle) LIKE %?2% "
	 		+ "OR LOWER(e.tagRole) LIKE %?2% )"
	 		)
	public List<KeyDate> searchByCriteria(CompanyInstance company, String title, Pageable page);


	@Query("SELECT count(e) FROM #{#entityName} e WHERE e.company=?1 AND ("
	 		+ "LOWER(e.eventDetails) LIKE %?2% "
	 		+ "OR LOWER(e.eventLocation) LIKE %?2% "
	 		+ "OR LOWER(e.eventSubTitle) LIKE %?2% "
	 		+ "OR LOWER(e.eventTitle) LIKE %?2% "
	 		+ "OR LOWER(e.tagRole) LIKE %?2% )"
	 		)
	public Long countByCriteria(CompanyInstance company, String title);

	public Long countByCompany(CompanyInstance company);

	public List<KeyDate> findByCompany(CompanyInstance company, Pageable page);

}
