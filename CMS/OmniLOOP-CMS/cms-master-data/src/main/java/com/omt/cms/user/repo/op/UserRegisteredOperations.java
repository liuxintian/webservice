package com.omt.cms.user.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistered;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserRegisteredOperations  {

	public List<UserRegistered> findByUser(User user);
	
	public UserRegistered findById(Long entityId);

	public UserRegistered add(UserRegistered entity);

	public UserRegistered update(UserRegistered entity);

	public UserRegistered delete(UserRegistered entity);


}
