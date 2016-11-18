package com.omt.cms.user.repo.op;

import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.UserVerification;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserVerificationOperations {

	public UserVerification findById(Long id);

	public UserVerification findByToken(String token);

	public UserVerification findByUserToken(Long userId, String token);

	public UserVerification findByContextRefAndContext(Long contextRefKey, int context);

	public UserVerification add(UserVerification uv);

	public UserVerification update(UserVerification uv);

	public void delete(UserVerification uv);

}
	