/**
 * 
 */
package com.omt.cms.user.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.user.entity.User;
import com.omt.cms.user.entity.UserStockWatch;
import com.omt.cms.user.service.bo.UserStockWatchBO;

/**
 * @author muragesh
 *
 */

@Component
public class UserStockWatchMapper extends BaseBOMapper implements BOMapper<UserStockWatch, UserStockWatchBO> {

	@Override
	public Class<UserStockWatch> getEntityType() {
		return UserStockWatch.class;
	}

	@Override
	public Class<UserStockWatchBO> getBOType() {
		return UserStockWatchBO.class;
	}

	@Override
	public UserStockWatchBO fromEntityToBO(UserStockWatch entity) {
		UserStockWatchBO uwBO = null;
		if (entity != null) {
			uwBO = new UserStockWatchBO();
			BeanUtils.copyProperties(entity, uwBO);
			uwBO.setUserId(entity.getUser().getId());
		}
		return uwBO;
	}

	@Override
	public UserStockWatch createNewEntityFromBO(UserStockWatchBO bo, Long creatorId) {
		UserStockWatch entity = null;
		if (bo != null) {
			bo.setDefaultValidUntil();
			entity = new UserStockWatch();
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			User user=new User();
			user.setId(bo.getUserId());
			entity.setUser(user);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public UserStockWatch copyUpdatedBOToEntity(UserStockWatchBO bo, UserStockWatch entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setCompanyTicker(bo.getCompanyTicker());
			copyUpdatedCommonFields(bo, entity);
		}
		return entity;
	}
}
