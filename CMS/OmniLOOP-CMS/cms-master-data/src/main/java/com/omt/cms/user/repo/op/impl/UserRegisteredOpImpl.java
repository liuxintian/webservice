/**
 * 
 */
package com.omt.cms.user.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistered;
import com.omt.cms.user.repo.UserRegisteredRepository;
import com.omt.cms.user.repo.op.UserRegisteredOperations;

/**
 * @author muragesh
 *
 */
@Component
public class UserRegisteredOpImpl  implements UserRegisteredOperations {
	@Autowired UserRegisteredRepository urRepo;


	public UserRegistered findById(Long entityId) {
		return urRepo.findOne(entityId);
	}

	@Override	
	public UserRegistered add(UserRegistered entity) {
		return urRepo.save(entity);
	}

	@Override
	public UserRegistered update(UserRegistered entity) {
		return urRepo.save(entity);
	}

	@Override
	public UserRegistered delete(UserRegistered entity) {
		return urRepo.save(entity);
	}

	 
	public List<UserRegistered> findAll() {
		return (List<UserRegistered>) urRepo.findAll();
	}



	@Override
	public List<UserRegistered> findByUser(User user) {
 		return urRepo.findByUser(user);
	}
}
