package com.omt.cms.master.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.master.entity.DataMenu;
import com.omt.cms.master.service.bo.DataMenuBO;

@Component
public class DataMenuMapper extends BaseBOMapper implements BOMapper<DataMenu, DataMenuBO> {

	@Override
	public Class<DataMenu> getEntityType() {
		return DataMenu.class;
	}

	@Override
	public Class<DataMenuBO> getBOType() {
		return DataMenuBO.class;
	}

	@Override
	public DataMenuBO fromEntityToBO(DataMenu entity) {
		DataMenuBO bo = new DataMenuBO();
		BeanUtils.copyProperties(entity, bo);
 		return bo;
	}

	@Override
	public DataMenu createNewEntityFromBO(DataMenuBO bo, Long creatorId) {
		DataMenu entity = new DataMenu();
		if(bo!=null){
			BeanUtils.copyProperties(bo , entity);
			entity.setId(null);
			entity.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public DataMenu copyUpdatedBOToEntity(DataMenuBO bo, DataMenu entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setDataMenu(bo.getDataMenu());
			entity.setDesc(bo.getDesc());
			entity.setUrl(bo.getUrl()); 
			copyUpdatedCommonFields(bo, entity);
		}

		return entity;
	}

}
