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

import com.omt.cms.entity.DocumentLinksMedia;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface DocumentLinksMediaRepository extends PagingAndSortingRepository<DocumentLinksMedia, Long> {

	public List<DocumentLinksMedia> findByCompanyId(Long companyId);

	public List<DocumentLinksMedia> findByCompanyIdAndValidUntilAfter(Long companyId, Timestamp validUntil, Pageable page);

	public List<DocumentLinksMedia> findByCompanyIdAndStatus(Long companyId, int status, Pageable page);

	public List<DocumentLinksMedia> findByCompanyIdAndStatusAndValidUntilAfter(Long companyId, int status, Timestamp validUntil, Pageable page);

	@Query("SELECT e FROM #{#entityName} e WHERE e.companyId=?1 AND ("
	 		+ "LOWER(e.docLinkURL) LIKE %?2% "
	 		+ "OR LOWER(e.docTitle) LIKE %?2% "
	 		+ "OR LOWER(e.docType) LIKE %?2% "
	 		+ "OR LOWER(e.docExtension) LIKE %?2% )"
	 		)
	public List<DocumentLinksMedia> searchByCriteria(Long companyId, String title, Pageable page);

	@Query("SELECT count(e) FROM #{#entityName} e WHERE  e.companyId=?1 AND ("
	 		+ "LOWER(e.docLinkURL) LIKE %?2% "
	 		+ "OR LOWER(e.docTitle) LIKE %?2% "
	 		+ "OR LOWER(e.docType) LIKE %?2% "
	 		+ "OR LOWER(e.docExtension) LIKE %?2% )"
	 		)
	public Long countByCriteria(Long companyId, String title);

	public Long countByCompanyId(Long companyId);

	public List<DocumentLinksMedia> findByCompanyId(Long companyId, Pageable page);

}
