package com.omt.cms.user.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.UserVerification;
import com.omt.cms.user.repo.UserVerificationRepository;
import com.omt.cms.user.repo.op.UserVerificationOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class UserVerificationOpImpl implements UserVerificationOperations {

	@Autowired private UserVerificationRepository uvr;
	
	@Override
	public UserVerification findById(Long id) {
		return uvr.findOne(id);
	}

	@Override
	public UserVerification findByToken(String token) {
		return uvr.findByToken(token);
	}

	@Override
	public UserVerification findByUserToken(Long userId, String token) {
		List<UserVerification> records = uvr.findByCreatedForAndToken(userId, token);
		if(!records.isEmpty()){
			return records.get(0);
		}
		return null;
	}
	
	@Override
	public UserVerification findByContextRefAndContext(Long contextRefKey, int context) {
		return uvr.findByContextRefKeyAndContext(contextRefKey, context);
	}

	@Override
	public UserVerification add(UserVerification mv) {
		return uvr.save(mv);
	}

	@Override
	public UserVerification update(UserVerification mv) {
		return uvr.save(mv);
	}

	@Override
	public void delete(UserVerification mv) {
		uvr.delete(mv);
	}

}
