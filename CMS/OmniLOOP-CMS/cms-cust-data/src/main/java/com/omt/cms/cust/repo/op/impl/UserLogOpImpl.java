package com.omt.cms.cust.repo.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.UserLog;
import com.omt.cms.cust.repo.UserLogRepository;
import com.omt.cms.cust.repo.op.UserLogOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;


/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class UserLogOpImpl extends BaseCrudOpImpl<UserLog> implements UserLogOperations{

	@Autowired UserLogRepository luRepo;

	public UserLogOpImpl() {
		this.entityType = UserLog.class;
	}

	@Override
	public PagingAndSortingRepository<UserLog, Long> getRepository() {
		return luRepo;
	}


}
