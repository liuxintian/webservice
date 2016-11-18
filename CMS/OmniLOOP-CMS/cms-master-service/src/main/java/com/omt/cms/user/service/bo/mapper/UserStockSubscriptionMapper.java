/**
 * 
 */
package com.omt.cms.user.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.user.entity.UserStockSubscription;
import com.omt.cms.user.service.bo.UserStockSubscriptionBO;

/**
 * @author muragesh
 *
 */

@Component
public class UserStockSubscriptionMapper extends BaseBOMapper implements BOMapper<UserStockSubscription, UserStockSubscriptionBO> {

	@Override
	public Class<UserStockSubscription> getEntityType() {
		return UserStockSubscription.class;
	}

	@Override
	public Class<UserStockSubscriptionBO> getBOType() {
		return UserStockSubscriptionBO.class;
	}

	@Override
	public UserStockSubscriptionBO fromEntityToBO(UserStockSubscription entity) {
		UserStockSubscriptionBO uwBO = null;
		if (entity != null) {
			uwBO = new UserStockSubscriptionBO();
			BeanUtils.copyProperties(entity, uwBO);
		}
		return uwBO;
	}

	@Override
	public UserStockSubscription createNewEntityFromBO(UserStockSubscriptionBO bo, Long creatorId) {
		UserStockSubscription entity = null;
		if (bo != null) {
			bo.setDefaultValidUntil();
			entity = new UserStockSubscription();
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public UserStockSubscription copyUpdatedBOToEntity(UserStockSubscriptionBO bo, UserStockSubscription entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setCompanyTicker(bo.getCompanyTicker());
			copyUpdatedCommonFields(bo, entity);
		}
		return entity;
	}
}
