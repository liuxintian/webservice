package com.omt.cms.core.service.bo.base.mapper;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.bo.base.BaseBusinessObject;
import com.omt.cms.entity.BaseEntity;

@Component
public interface BOMapper<E extends BaseEntity, B extends BaseBusinessObject> {
	
	public Class<E> getEntityType();
	
	public Class<B> getBOType();
	
	public B fromEntityToBO(E entity);
	
	public E createNewEntityFromBO(B bo, Long creatorId);
	
	public E copyUpdatedBOToEntity(B bo, E entity, Long modifierId);
	
}
