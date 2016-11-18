package com.omt.cms.user.service.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.service.bo.base.AddressBO;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.user.service.bo.UserBO;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class LocalUserHelper {

	public UserBO createFrom(LocalUserBO local){
		UserBO user = null;
		if(local!=null){
			user = new UserBO();
			BeanUtils.copyProperties(local, user);
			if(local.getAddress()!=null){
				AddressBO addrBO = user.getAddress();
				if(addrBO==null){
					addrBO = new AddressBO();
				}
				BeanUtils.copyProperties(local.getAddress(), addrBO);
				user.setAddress(addrBO);
			}
		}
		return user;
	}
}
