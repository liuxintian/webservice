package com.omt.cms.user.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.repo.op.base.CrudOperations;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserStockWatch;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserStockWatchOperations extends CrudOperations<UserStockWatch> {

	public List<UserStockWatch> findByUser(User user);

	public boolean userCompanyTickerExists(User user, String companyTicker);
	
	public UserStockWatch getByCompanyTicker(User user, String companyTicker);
	
	public boolean deleteByCompanyTicker(User user, String companyTicker);
  
}
	