package com.omt.cms.user.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistration;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserRegistrationOperations  {

	public List<UserRegistration> findByUser(User user);
	
	public UserRegistration findById(Long entityId);

	public UserRegistration add(UserRegistration entity);

	public UserRegistration update(UserRegistration entity);

	public UserRegistration delete(UserRegistration entity);


}
