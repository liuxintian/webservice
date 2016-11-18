/**
 * 
 */
package com.omt.cms.cust.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.cust.entity.InstanceVerification;

/**
 * @author Shiva Kalgudi
 *
 */

@Repository
public interface InstanceVerificationRepository extends PagingAndSortingRepository<InstanceVerification, Long> {

	public InstanceVerification findByToken(String token);
	
	public InstanceVerification findByContextRefKeyAndContext(Long contextRefKey, int context);
}
