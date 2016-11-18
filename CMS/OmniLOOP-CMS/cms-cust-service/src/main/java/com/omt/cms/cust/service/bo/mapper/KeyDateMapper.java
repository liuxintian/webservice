package com.omt.cms.cust.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.KeyDateBO;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.cust.entity.KeyDate;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class KeyDateMapper extends BaseBOMapper implements BOMapper<KeyDate, KeyDateBO>{

	@Override
	public Class<KeyDate> getEntityType() {
		return KeyDate.class;
	}

	@Override
	public Class<KeyDateBO> getBOType() {
		return KeyDateBO.class;
	}

	@Override
	public KeyDateBO fromEntityToBO(KeyDate entity) {
		KeyDateBO bo = new KeyDateBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);
			bo.setCompanyId(entity.getCompany().getId());
		}
		return bo;
	}

	@Override
	public KeyDate createNewEntityFromBO(KeyDateBO bo, Long creatorId) {
		KeyDate entity = new KeyDate();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public KeyDate copyUpdatedBOToEntity(KeyDateBO bo, KeyDate entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setEventDateTime(bo.getEventDateTime());
				entity.setEventStartDateTime(bo.getEventStartDateTime());
				entity.setEventEndDateTime(bo.getEventEndDateTime());
				entity.setEventTitle(bo.getEventTitle());
				entity.setEventSubTitle(bo.getEventSubTitle());
				entity.setEventLocation(bo.getEventLocation());
				entity.setEventDetails(bo.getEventDetails());
				entity.setKeydateType(bo.getKeydateType());
				entity.setEventFullDay(bo.getEventFullDay());
				entity.setEventRange(bo.getEventRange());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}

}
