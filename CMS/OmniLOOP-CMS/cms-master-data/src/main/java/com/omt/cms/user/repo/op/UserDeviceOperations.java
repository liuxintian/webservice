package com.omt.cms.user.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserDevice;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserDeviceOperations  {

	public List<UserDevice> findByUser(User user);

	public UserDevice findById(Long entityId);

	public UserDevice add(UserDevice entity);

	public UserDevice update(UserDevice entity);

	public UserDevice delete(UserDevice entity);


}
