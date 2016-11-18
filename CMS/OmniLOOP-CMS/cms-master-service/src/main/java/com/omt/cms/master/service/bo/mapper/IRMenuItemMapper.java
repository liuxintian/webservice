package com.omt.cms.master.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.master.entity.IRMenuItem;
import com.omt.cms.master.service.bo.IRMenuItemBO;

@Component
public class IRMenuItemMapper extends BaseBOMapper implements BOMapper<IRMenuItem, IRMenuItemBO>{

	public Class<IRMenuItem> getEntityType() {
		return IRMenuItem.class;
	}

	public Class<IRMenuItemBO> getBOType() {
		return IRMenuItemBO.class;
	}

	public IRMenuItemBO fromEntityToBO(IRMenuItem entity) {
		IRMenuItemBO bo = new IRMenuItemBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);			
		}
		return bo;
	}

	public IRMenuItem createNewEntityFromBO(IRMenuItemBO bo, Long creatorId) {
		IRMenuItem entity = new IRMenuItem();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	public IRMenuItem copyUpdatedBOToEntity(IRMenuItemBO bo, IRMenuItem entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setMenuItemName(bo.getMenuItemName());
				entity.setMenuItemDesc(bo.getMenuItemDesc());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}

}
