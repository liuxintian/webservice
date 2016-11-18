/**
 * 
 */
package com.omt.cms.user.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserStockWatch;

/**
 * @author Shiva Kalgudi
 *
 */
@Repository
public interface UserStockWatchRepository extends PagingAndSortingRepository<UserStockWatch, Long> {

	List<UserStockWatch> findByUser(User user);

	Long countByUserAndCompanyTicker(User user, String companyTicker);
	
	List<UserStockWatch> findByUserAndCompanyTicker(User user, String companyTicker);

	Long deleteByUserAndCompanyTicker(User user, String companyTicker);

}
