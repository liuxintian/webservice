package com.omt.cms.master.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.UserDetailsBO;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.master.entity.MasterManager;

@Component
public class UserDetailsMapper implements BOMapper<MasterManager, UserDetailsBO>{

	@Override
	public Class<MasterManager> getEntityType() {
		return MasterManager.class;
	}

	@Override
	public Class<UserDetailsBO> getBOType() {
		return UserDetailsBO.class;
	}

	@Override
	public UserDetailsBO fromEntityToBO(MasterManager entity) {
		UserDetailsBO bo = new UserDetailsBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);			
		}
		return bo;
	}

	@Override
	public MasterManager createNewEntityFromBO(UserDetailsBO bo, Long creatorId) {
		MasterManager entity = new MasterManager();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public MasterManager copyUpdatedBOToEntity(UserDetailsBO bo, MasterManager entity, Long modifierId) {
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
