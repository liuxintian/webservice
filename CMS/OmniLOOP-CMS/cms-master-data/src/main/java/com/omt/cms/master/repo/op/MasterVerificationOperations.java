package com.omt.cms.master.repo.op;

import org.springframework.stereotype.Component;

import com.omt.cms.master.entity.MasterVerification;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface MasterVerificationOperations {

	public MasterVerification findById(Long id);

	public MasterVerification findByToken(String token);

	public MasterVerification findByContextRefAndContext(Long contextRefKey, int context);

	public MasterVerification add(MasterVerification mv);

	public MasterVerification update(MasterVerification mv);

	public void delete(MasterVerification mv);

}
