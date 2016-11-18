package com.omt.cms.cust.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.cust.entity.UserDevice;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface UserDeviceOperations  {

	public List<UserDevice> findByUserId(long userId);
	
}
