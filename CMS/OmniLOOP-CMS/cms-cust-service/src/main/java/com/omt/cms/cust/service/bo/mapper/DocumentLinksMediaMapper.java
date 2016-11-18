package com.omt.cms.cust.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.DocumentLinksMediaBO;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.entity.DocumentLinksMedia;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class DocumentLinksMediaMapper extends BaseBOMapper implements BOMapper<DocumentLinksMedia, DocumentLinksMediaBO>{

	@Override
	public Class<DocumentLinksMedia> getEntityType() {
		return DocumentLinksMedia.class;
	}

	@Override
	public Class<DocumentLinksMediaBO> getBOType() {
		return DocumentLinksMediaBO.class;
	}

	@Override
	public DocumentLinksMediaBO fromEntityToBO(DocumentLinksMedia entity) {
		DocumentLinksMediaBO bo = new DocumentLinksMediaBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);
			entity.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
		}
		return bo;
	}

	@Override
	public DocumentLinksMedia createNewEntityFromBO(DocumentLinksMediaBO bo, Long creatorId) {
		DocumentLinksMedia entity = new DocumentLinksMedia();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
		}
		return entity;
	}

	@Override
	public DocumentLinksMedia copyUpdatedBOToEntity(DocumentLinksMediaBO bo, DocumentLinksMedia entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setCompanyId(bo.getCompanyId());
				entity.setDocTitle(bo.getDocTitle());
				entity.setDocDesc(bo.getDocDesc());
				entity.setDocSize(bo.getDocSize());
				entity.setDocExtension(bo.getDocExtension());
				entity.setDocThumbnail(bo.getDocThumbnail());
				entity.setDocLinkURL(bo.getDocLinkURL());
				entity.setDocNote(bo.getDocNote());
				entity.setDocType(bo.getDocType());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}

}
