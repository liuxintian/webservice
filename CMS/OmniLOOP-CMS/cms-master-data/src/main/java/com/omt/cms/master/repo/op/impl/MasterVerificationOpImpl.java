package com.omt.cms.master.repo.op.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.MasterVerification;
import com.omt.cms.master.repo.MasterVerificationRepository;
import com.omt.cms.master.repo.op.MasterVerificationOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class MasterVerificationOpImpl implements MasterVerificationOperations {

	@Autowired private MasterVerificationRepository mvr;
	
	@Override
	public MasterVerification findById(Long id) {
		return mvr.findOne(id);
	}

	@Override
	public MasterVerification findByToken(String token) {
		return mvr.findByToken(token);
	}

	@Override
	public MasterVerification findByContextRefAndContext(Long contextRefKey, int context) {
		return mvr.findByContextRefKeyAndContext(contextRefKey, context);
	}

	@Override
	public MasterVerification add(MasterVerification mv) {
		return mvr.save(mv);
	}

	@Override
	public MasterVerification update(MasterVerification mv) {
		return mvr.save(mv);
	}

	@Override
	public void delete(MasterVerification mv) {
		mvr.delete(mv);
	}

}
