package com.omt.cms.cust.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.AnnouncementBO;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.cust.entity.Announcement;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class AnnouncementMapper extends BaseBOMapper implements BOMapper<Announcement, AnnouncementBO>{

	@Override
	public Class<Announcement> getEntityType() {
		return Announcement.class;
	}

	@Override
	public Class<AnnouncementBO> getBOType() {
		return AnnouncementBO.class;
	}

	@Override
	public AnnouncementBO fromEntityToBO(Announcement entity) {
		AnnouncementBO bo = new AnnouncementBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);
			bo.setCompanyId(entity.getCompany().getId());
		}
		return bo;
	}

	@Override
	public Announcement createNewEntityFromBO(AnnouncementBO bo, Long creatorId) {
		Announcement entity = new Announcement();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public Announcement copyUpdatedBOToEntity(AnnouncementBO bo, Announcement entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setIsPriceSensitive(bo.getIsPriceSensitive());
				entity.setDocumentLink(bo.getDocumentLink());
				entity.setDocumentTitle(bo.getDocumentTitle());
				entity.setAnnouncementType(bo.getAnnouncementType());
				entity.setAnnouncementDate(bo.getAnnouncementDate());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}

}
