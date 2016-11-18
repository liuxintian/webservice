/**
 * 
 */
package com.omt.cms.master.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.master.entity.CompanyInstanceRegistry;
import com.omt.cms.master.service.bo.CompanyInstanceRegistryBO;

/**
 * @author muragesh
 *
 */

@Component
public class CompanyInstanceRegistryMapper extends BaseBOMapper implements BOMapper<CompanyInstanceRegistry, CompanyInstanceRegistryBO> {

	@Override
	public Class<CompanyInstanceRegistry> getEntityType() {
		return CompanyInstanceRegistry.class;
	}

	@Override
	public Class<CompanyInstanceRegistryBO> getBOType() {
		return CompanyInstanceRegistryBO.class;
	}

	@Override
	public CompanyInstanceRegistryBO fromEntityToBO(CompanyInstanceRegistry entity) {
		CompanyInstanceRegistryBO cirBO = null;
		if (entity != null) {
			cirBO = new CompanyInstanceRegistryBO();
			BeanUtils.copyProperties(entity, cirBO);
			cirBO.setCpId(entity.getCompany().getId());
		}
		return cirBO;
	}

	@Override
	public CompanyInstanceRegistry createNewEntityFromBO(CompanyInstanceRegistryBO bo, Long creatorId) {
		CompanyInstanceRegistry entity = null;
		if (bo != null) {
			entity = new CompanyInstanceRegistry();
			BeanUtils.copyProperties(bo, entity);
			entity.setId(null);
			entity.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public CompanyInstanceRegistry copyUpdatedBOToEntity(CompanyInstanceRegistryBO bo, CompanyInstanceRegistry entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setInstanceName(bo.getInstanceName());
			entity.setInstanceURL(bo.getInstanceURL());
			entity.setInstanceTag(bo.getInstanceTag());
			entity.setWhiteLabel(bo.getWhiteLabel());
			entity.setShareRegistry(bo.getShareRegistry());
			copyUpdatedCommonFields(bo, entity);
		}
		return entity;
	}
}
