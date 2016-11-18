/**
 * 
 */
package com.omt.cms.user.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.UserStockSubscription;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserStockSubscriptionRepository extends PagingAndSortingRepository<UserStockSubscription, Long> {

	List<UserStockSubscription> findByUserId(Long userId);

	Long countByUserIdAndCompanyTicker(Long userId, String companyTicker);
	
	List<UserStockSubscription> findByUserIdAndCompanyTicker(Long userId, String companyTicker);

	Long deleteByUserIdAndCompanyTicker(Long userId, String companyTicker);

}
