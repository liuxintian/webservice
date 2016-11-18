package com.omt.cms.core.service.bo.base.mapper;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.service.bo.base.CommonBO;
import com.omt.cms.entity.BaseEntity;

public abstract class BaseBOMapper {

	public void copyUpdatedCommonFields(CommonBO bo, BaseEntity entity){
		if(bo!=null && entity!=null){
			entity.setLastUpdated(DateHelper.getCurTimestamp());
			entity.setNotes(bo.getNotes());
			if(bo.getTagRole()!=null)
				entity.setTagRole(bo.getTagRole());
			if(bo.getValidUntil()!=null){
				entity.setValidUntil(bo.getValidUntil());	
			}
		}
	}

	public void updateOnlyStatus(CommonBO bo, BaseEntity entity){
		if(bo!=null && entity!=null && bo.getStatus()!=null){
			entity.setLastUpdated(DateHelper.getCurTimestamp());
			entity.setStatus(bo.getStatus());
		}
	}

}
