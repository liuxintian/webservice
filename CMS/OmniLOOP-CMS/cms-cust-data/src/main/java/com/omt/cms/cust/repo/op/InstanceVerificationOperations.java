package com.omt.cms.cust.repo.op;

import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.InstanceVerification;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface InstanceVerificationOperations {

	public InstanceVerification findById(Long id);

	public InstanceVerification findByToken(String token);

	public InstanceVerification findByContextRefAndContext(Long contextRefKey, int context);

	public InstanceVerification add(InstanceVerification iv);

	public InstanceVerification update(InstanceVerification iv);

	public void delete(InstanceVerification iv);

}
