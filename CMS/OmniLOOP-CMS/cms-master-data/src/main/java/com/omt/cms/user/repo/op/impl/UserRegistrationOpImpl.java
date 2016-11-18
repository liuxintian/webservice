/**
 * 
 */
package com.omt.cms.user.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistration;
import com.omt.cms.user.repo.UserRegistrationRepository;
import com.omt.cms.user.repo.op.UserRegistrationOperations;

/**
 * @author muragesh
 *
 */
@Component
public class UserRegistrationOpImpl  implements UserRegistrationOperations {
	@Autowired UserRegistrationRepository urRepo;

	public UserRegistration findById(Long entityId) {
		return urRepo.findOne(entityId);
	}

	@Override	
	public UserRegistration add(UserRegistration entity) {
		return urRepo.save(entity);
	}

	@Override
	public UserRegistration update(UserRegistration entity) {
		return urRepo.save(entity);
	}

	@Override
	public UserRegistration delete(UserRegistration entity) {
		return urRepo.save(entity);
	}

	 
	public List<UserRegistration> findAll() {
		return (List<UserRegistration>) urRepo.findAll();
	}

	@Override
	public List<UserRegistration> findByUser(User user) {
 		return urRepo.findByUser(user);
	}
}
