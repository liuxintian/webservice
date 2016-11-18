/**
 * 
 */
package com.omt.cms.user.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.UserVerification;

/**
 * @author Shiva Kalgudi
 *
 */

@Repository
public interface UserVerificationRepository extends PagingAndSortingRepository<UserVerification, Long> {

	public UserVerification findByToken(String token);
	
	public UserVerification findByContextRefKeyAndContext(Long contextRefKey, int context);
	
	public List<UserVerification> findByCreatedForAndToken(Long createdFor, String token);
}
