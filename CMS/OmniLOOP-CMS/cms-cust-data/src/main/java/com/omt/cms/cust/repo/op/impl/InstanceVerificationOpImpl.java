package com.omt.cms.cust.repo.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.InstanceVerification;
import com.omt.cms.cust.repo.InstanceVerificationRepository;
import com.omt.cms.cust.repo.op.InstanceVerificationOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class InstanceVerificationOpImpl implements InstanceVerificationOperations {

	@Autowired private InstanceVerificationRepository ivr;
	
	@Override
	public InstanceVerification findById(Long id) {
		return ivr.findOne(id);
	}

	@Override
	public InstanceVerification findByToken(String token) {
		return ivr.findByToken(token);
	}

	@Override
	public InstanceVerification findByContextRefAndContext(Long contextRefKey, int context) {
		return ivr.findByContextRefKeyAndContext(contextRefKey, context);
	}

	@Override
	public InstanceVerification add(InstanceVerification mv) {
		return ivr.save(mv);
	}

	@Override
	public InstanceVerification update(InstanceVerification mv) {
		return ivr.save(mv);
	}

	@Override
	public void delete(InstanceVerification mv) {
		ivr.delete(mv);
	}

}
