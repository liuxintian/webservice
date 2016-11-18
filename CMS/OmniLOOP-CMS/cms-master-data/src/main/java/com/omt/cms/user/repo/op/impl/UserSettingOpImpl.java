/**
 * 
 */
package com.omt.cms.user.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserSetting;
import com.omt.cms.user.repo.UserSettingRepository;
import com.omt.cms.user.repo.op.UserSettingOperations;

/**
 * @author muragesh
 *
 */
@Component
public class UserSettingOpImpl  implements UserSettingOperations {
	@Autowired UserSettingRepository utRepo;


	public UserSetting findById(Long entityId) {
		return utRepo.findOne(entityId);
	}

	@Override	
	public UserSetting add(UserSetting entity) {
		return utRepo.save(entity);
	}

	@Override
	public UserSetting update(UserSetting entity) {
		return utRepo.save(entity);
	}

	@Override
	public UserSetting delete(UserSetting entity) {
		return utRepo.save(entity);
	}


	public List<UserSetting> findAll() {
		return (List<UserSetting>) utRepo.findAll();
	}



	@Override
	public List<UserSetting> findByUser(User user) {
		return utRepo.findByUser(user);
	}
}
