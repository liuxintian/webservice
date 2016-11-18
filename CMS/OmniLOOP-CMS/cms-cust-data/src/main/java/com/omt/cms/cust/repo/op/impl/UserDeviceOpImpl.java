/**
 * 
 */
package com.omt.cms.cust.repo.op.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.UserDevice;
import com.omt.cms.cust.repo.UserDeviceRepository;
import com.omt.cms.cust.repo.op.UserDeviceOperations;

/**
 * @author muragesh
 *
 */
@Component
public class UserDeviceOpImpl  implements UserDeviceOperations {
	@Autowired UserDeviceRepository udRepo;


	public List<UserDevice> findByUserId(long userId) {
		return udRepo.findByUserId(userId);
	}

}
