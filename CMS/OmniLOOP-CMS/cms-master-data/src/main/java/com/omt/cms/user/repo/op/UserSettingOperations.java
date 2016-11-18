package com.omt.cms.user.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserSetting;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserSettingOperations  {



	public List<UserSetting> findByUser(User user);
	
	public UserSetting findById(Long entityId);

	public UserSetting add(UserSetting entity);

	public UserSetting update(UserSetting entity);

	public UserSetting delete(UserSetting entity);


}
