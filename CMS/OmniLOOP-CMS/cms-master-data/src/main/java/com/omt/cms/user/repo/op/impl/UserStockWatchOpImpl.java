/**
 * 
 */
package com.omt.cms.user.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.repo.op.base.BaseCrudOpImpl;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserStockWatch;
import com.omt.cms.user.repo.UserStockWatchRepository;
import com.omt.cms.user.repo.op.UserStockWatchOperations;

/**
 * @author muragesh
 *
 */
@Component
public class UserStockWatchOpImpl extends BaseCrudOpImpl<UserStockWatch>  implements UserStockWatchOperations {
	@Autowired UserStockWatchRepository uwRepo;

	public UserStockWatchOpImpl() {
		this.entityType = UserStockWatch.class;
	}

	@Override
	public PagingAndSortingRepository<UserStockWatch, Long> getRepository() {
		return uwRepo;
	}

	@Override
	public List<UserStockWatch> findByUser(User user) {
		return uwRepo.findByUser(user);
	}

	@Override
	public boolean userCompanyTickerExists(User user, String companyTicker) {
		return uwRepo.countByUserAndCompanyTicker(user, companyTicker) > 0;
	}
	
	@Override
	public UserStockWatch getByCompanyTicker(User user, String companyTicker) {
		List<UserStockWatch> lst = uwRepo.findByUserAndCompanyTicker(user, companyTicker);
		if(lst.size() > 0){
			return lst.get(0);
		}
		return null;
	}
	
	@Override
	public boolean deleteByCompanyTicker(User user, String companyTicker) {
		return uwRepo.deleteByUserAndCompanyTicker(user, companyTicker) > 0;
	}
}
