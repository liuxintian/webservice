/**
 * 
 */
package com.omt.cms.master.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.master.entity.MasterVerification;

/**
 * @author Shiva Kalgudi
 *
 */

@Repository
public interface MasterVerificationRepository extends PagingAndSortingRepository<MasterVerification, Long> {

	public MasterVerification findByToken(String token);
	
	public MasterVerification findByContextRefKeyAndContext(Long contextRefKey, int context);
}
