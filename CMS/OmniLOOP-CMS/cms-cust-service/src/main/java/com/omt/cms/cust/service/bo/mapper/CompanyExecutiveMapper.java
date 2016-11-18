package com.omt.cms.cust.service.bo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.CompanyExecutiveBO;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.cust.entity.CompanyExecutive;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class CompanyExecutiveMapper extends BaseBOMapper implements BOMapper<CompanyExecutive, CompanyExecutiveBO>{

	@Override
	public Class<CompanyExecutive> getEntityType() {
		return CompanyExecutive.class;
	}

	@Override
	public Class<CompanyExecutiveBO> getBOType() {
		return CompanyExecutiveBO.class;
	}

	@Override
	public CompanyExecutiveBO fromEntityToBO(CompanyExecutive entity) {
		CompanyExecutiveBO bo = new CompanyExecutiveBO();
		if(entity!=null){
			BeanUtils.copyProperties(entity, bo);
			bo.setCompanyId(entity.getCompany().getId());
		}
		return bo;
	}

	@Override
	public CompanyExecutive createNewEntityFromBO(CompanyExecutiveBO bo, Long creatorId) {
		CompanyExecutive entity = new CompanyExecutive();
		if(bo!=null){
			BeanUtils.copyProperties(bo, entity);
			entity.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public CompanyExecutive copyUpdatedBOToEntity(CompanyExecutiveBO bo, CompanyExecutive entity, Long modifierId) {
		if(bo!=null && entity!=null){
			if(bo.getStatus()==null){
				entity.setExecName(bo.getExecName());
				entity.setExecJobTitle(bo.getExecJobTitle());
				entity.setExecDesc(bo.getExecDesc());
				entity.setExecEmail(bo.getExecEmail());
				entity.setExecPhone(bo.getExecPhone());
				entity.setExecImageURL(bo.getExecImageURL());
				entity.setExecType(bo.getExecType());
				copyUpdatedCommonFields(bo, entity);
			}else{
				updateOnlyStatus(bo, entity);
			}
		}
		return entity;
	}

}
