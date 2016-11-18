/**
 * 
 */
package com.omt.cms.master.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.master.entity.ShareRegistry;
import com.omt.cms.master.service.bo.ShareRegistryBO;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public class ShareRegistryMapper extends BaseBOMapper implements  BOMapper<ShareRegistry, ShareRegistryBO> {

	@Override
	public Class<ShareRegistry> getEntityType() {
		return ShareRegistry.class;
	}

	@Override
	public Class<ShareRegistryBO> getBOType() {
		return ShareRegistryBO.class;
	}

	@Override
	public ShareRegistryBO fromEntityToBO(ShareRegistry entity) {
		ShareRegistryBO bo = null;
		if (entity != null) {
			bo = new ShareRegistryBO();
			BeanUtils.copyProperties(entity, bo);
		}
		return bo;
	}

	@Override
	public ShareRegistry createNewEntityFromBO(ShareRegistryBO bo, Long creatorId) {
		ShareRegistry entity = null;
		if (bo != null) {
			entity = new ShareRegistry();
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			entity.setStatus(RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public ShareRegistry copyUpdatedBOToEntity(ShareRegistryBO bo, ShareRegistry entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setShareRegistry(bo.getShareRegistry());
				entity.setSrCountry(bo.getSrCountry());
				entity.setSrAddress(bo.getSrAddress());
				entity.setSrURL(bo.getSrURL());
				entity.setSrPhone(bo.getSrPhone());
				entity.setSrEmail(bo.getSrEmail());
				entity.setSrType(bo.getSrType());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}
}
