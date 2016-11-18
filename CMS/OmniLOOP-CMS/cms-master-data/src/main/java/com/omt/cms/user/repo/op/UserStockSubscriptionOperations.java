package com.omt.cms.user.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.repo.op.base.CrudOperations;
import com.omt.cms.user.entity.UserStockSubscription;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserStockSubscriptionOperations extends CrudOperations<UserStockSubscription> {

	public List<UserStockSubscription> findByUser(Long userId);

	public boolean userCompanyTickerExists(Long userId, String companyTicker);
	
	public UserStockSubscription getByCompanyTicker(Long userId, String companyTicker);
	
	public boolean deleteByCompanyTicker(Long userId, String companyTicker);
  
}
	