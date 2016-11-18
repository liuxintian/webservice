/**
 * 
 */
package com.omt.cms.user.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserRegistered;
import com.omt.cms.user.service.bo.UserRegisteredBO;

/**
 * @author muragesh
 *
 */

@Component
public class UserRegisteredMapper {

	public UserRegisteredBO fromEntityToBO(UserRegistered entity) {
		UserRegisteredBO uwBO = null;
		if (entity != null) {
			uwBO = new UserRegisteredBO();
			BeanUtils.copyProperties(entity, uwBO);
			uwBO.setUserId(entity.getUser().getId());
		}
		return uwBO;
	}

	public UserRegistered createNewEntityFromBO(UserRegisteredBO bo, Long creatorId) {
		UserRegistered entity = null;
		if (bo != null) {
			entity = new UserRegistered();
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			User user=new User();
			user.setId(bo.getUserId());
			entity.setUser(user);
		}
		return entity;
	}

	public UserRegistered copyUpdatedBOToEntity(UserRegisteredBO bo, UserRegistered entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setLastUpdated(DateHelper.getCurTimestamp());
			entity.setAppRegistered(bo.getAppRegistered());
			entity.setIsPrimary(bo.getIsPrimary());
		}
		return entity;
	}
}
