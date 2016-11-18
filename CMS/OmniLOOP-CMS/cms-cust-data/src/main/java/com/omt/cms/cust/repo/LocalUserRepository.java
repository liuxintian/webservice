/**
 * 
 */
package com.omt.cms.cust.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.entity.LocalUser;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface LocalUserRepository extends PagingAndSortingRepository<LocalUser, Long> {

	public LocalUser findByLoginName(String loginName);
	
	public Long countByLoginName(String loginName);

	public List<LocalUser> findByCompany(CompanyInstance company, Pageable page);

	@Query("SELECT e FROM #{#entityName} e WHERE e.company=?1 AND (e.userInvitedDT IS NOT NULL OR e.shareSubscriber=?2)")
	public List<LocalUser> findCompanySubscribers(CompanyInstance company, Boolean shareSubscriber, Pageable page);

	@Query("SELECT e FROM #{#entityName} e WHERE e.company=?1 AND ("
	 		+ "LOWER(e.userName) LIKE %?2% "
	 		+ "OR LOWER(e.userEmail) LIKE %?2% "
	 		+ "OR LOWER(e.loginName) LIKE %?2% "
	 		+ "OR LOWER(e.userContact) LIKE %?2% "
	 		+ "OR LOWER(e.tagRole) LIKE %?2% )"
	 		)
	public List<LocalUser> searchByCriteria(CompanyInstance company, String title, Pageable page);


	@Query("SELECT count(e) FROM #{#entityName} e WHERE e.company=?1 AND ("
	 		+ "LOWER(e.userName) LIKE %?2% "
	 		+ "OR LOWER(e.userEmail) LIKE %?2% "
	 		+ "OR LOWER(e.loginName) LIKE %?2% "
	 		+ "OR LOWER(e.userContact) LIKE %?2% "
	 		+ "OR LOWER(e.tagRole) LIKE %?2% )"
	 		)
	public Long countByCriteria(CompanyInstance company, String title);

	public Long countByCompany(CompanyInstance company);

	public List<LocalUser> findByCompanyAndShareSubscriber(CompanyInstance company, boolean shareSubscriber, Pageable page);

	@Query("SELECT e FROM #{#entityName} e WHERE e.company=?1 AND e.userInvitedDT IS NOT NULL")
	public List<LocalUser> findByCompanyAndUserInvited(CompanyInstance company, Pageable page);
}
