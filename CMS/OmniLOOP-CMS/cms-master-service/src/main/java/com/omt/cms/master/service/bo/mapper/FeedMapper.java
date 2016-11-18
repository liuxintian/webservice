package com.omt.cms.master.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.master.entity.Feed;
import com.omt.cms.master.service.bo.FeedBO;

@Component
public class FeedMapper extends BaseBOMapper implements BOMapper<Feed, FeedBO>{

	public Class<Feed> getEntityType() {
		return Feed.class;
	}

	public Class<FeedBO> getBOType() {
		return FeedBO.class;
	}

	public FeedBO fromEntityToBO(Feed entity) {
		FeedBO bo = new FeedBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);			
		}
		return bo;
	}

	public Feed createNewEntityFromBO(FeedBO bo, Long creatorId) {
		Feed entity = new Feed();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	public Feed copyUpdatedBOToEntity(FeedBO bo, Feed entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setFeedTitle(bo.getFeedTitle());
				entity.setFeedDesc(bo.getFeedDesc());
				entity.setFeedURL(bo.getFeedURL());
				entity.setFeedType(bo.getFeedType());
				entity.setFeedContact(bo.getFeedContact());
				entity.setFeedCompany(bo.getFeedCompany());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}

}
