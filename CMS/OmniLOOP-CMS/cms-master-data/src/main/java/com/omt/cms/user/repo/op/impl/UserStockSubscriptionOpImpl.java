/**
 * 
 */
package com.omt.cms.user.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.repo.op.base.BaseCrudOpImpl;
import com.omt.cms.user.entity.UserStockSubscription;
import com.omt.cms.user.repo.UserStockSubscriptionRepository;
import com.omt.cms.user.repo.op.UserStockSubscriptionOperations;

/**
 * @author muragesh
 *
 */
@Component
public class UserStockSubscriptionOpImpl extends BaseCrudOpImpl<UserStockSubscription>  implements UserStockSubscriptionOperations {
	@Autowired UserStockSubscriptionRepository uwRepo;

	public UserStockSubscriptionOpImpl() {
		this.entityType = UserStockSubscription.class;
	}

	@Override
	public PagingAndSortingRepository<UserStockSubscription, Long> getRepository() {
		return uwRepo;
	}

	@Override
	public List<UserStockSubscription> findByUser(Long userId) {
		return uwRepo.findByUserId(userId);
	}

	@Override
	public boolean userCompanyTickerExists(Long userId, String companyTicker) {
		return uwRepo.countByUserIdAndCompanyTicker(userId, companyTicker) > 0;
	}
	
	@Override
	public UserStockSubscription getByCompanyTicker(Long userId, String companyTicker) {
		List<UserStockSubscription> lst = uwRepo.findByUserIdAndCompanyTicker(userId, companyTicker);
		if(lst.size() > 0){
			return lst.get(0);
		}
		return null;
	}
	
	@Override
	public boolean deleteByCompanyTicker(Long userId, String companyTicker) {
		return uwRepo.deleteByUserIdAndCompanyTicker(userId, companyTicker) > 0;
	}
}
