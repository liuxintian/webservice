/**
 * 
 */
package com.omt.cms.user.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserDevice;
import com.omt.cms.user.repo.UserDeviceRepository;
import com.omt.cms.user.repo.op.UserDeviceOperations;

/**
 * @author muragesh
 *
 */
@Component
public class UserDeviceOpImpl  implements UserDeviceOperations {
	@Autowired UserDeviceRepository udRepo;


	public UserDevice findById(Long entityId) {
		return udRepo.findOne(entityId);
	}

	@Override	
	public UserDevice add(UserDevice entity) {
		return udRepo.save(entity);
	}

	@Override
	public UserDevice update(UserDevice entity) {
		return udRepo.save(entity);
	}

	@Override
	public UserDevice delete(UserDevice entity) {
		return udRepo.save(entity);
	}

	 
	public List<UserDevice> findAll() {
		return (List<UserDevice>) udRepo.findAll();
	}



	@Override
	public List<UserDevice> findByUser(User user) {
 		return udRepo.findByUser(user);
	}
}
