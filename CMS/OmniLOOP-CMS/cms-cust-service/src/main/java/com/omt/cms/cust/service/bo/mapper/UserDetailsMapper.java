package com.omt.cms.cust.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.cust.entity.InstanceManager;

@Component
public class UserDetailsMapper implements BOMapper<InstanceManager, UserDetailsBO>{

	@Override
	public Class<InstanceManager> getEntityType() {
		return InstanceManager.class;
	}

	@Override
	public Class<UserDetailsBO> getBOType() {
		return UserDetailsBO.class;
	}

	@Override
	public UserDetailsBO fromEntityToBO(InstanceManager entity) {
		UserDetailsBO bo = new UserDetailsBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);
			bo.setCompanyId(entity.getCompany().getId());
		}
		return bo;
	}

	@Override
	public InstanceManager createNewEntityFromBO(UserDetailsBO bo, Long creatorId) {
		InstanceManager entity = new InstanceManager();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public InstanceManager copyUpdatedBOToEntity(UserDetailsBO bo, InstanceManager entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setLastUpdated(DateHelper.getCurTimestamp());
			if(bo.getStatus()==null){
				entity.setName(bo.getName());
			}else{
				entity.setStatus(bo.getStatus());
			}
		}
		return entity;
	}

}
