package com.omt.cms.master.service.bo.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.bo.base.mapper.BOMapper;
import com.omt.cms.core.service.bo.base.mapper.BaseBOMapper;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.service.bo.CompanyBO;

@Component
public class CompanyMapper extends BaseBOMapper implements BOMapper<Company, CompanyBO> {

	@Override
	public Class<Company> getEntityType() {
		return Company.class;
	}

	@Override
	public Class<CompanyBO> getBOType() {
		return CompanyBO.class;
	}

	@Override
	public CompanyBO fromEntityToBO(Company entity) {
		if(StringUtils.isNotBlank(entity.getCompanyTicker())){
			entity.setCompanyTicker(entity.getCompanyTicker().toLowerCase());
		}
		CompanyBO bo = new CompanyBO();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}

	@Override
	public Company createNewEntityFromBO(CompanyBO bo, Long creatorId) {
		Company entity = new Company();
		if(bo!=null){
			if(StringUtils.isNotBlank(bo.getCompanyTicker())){
				bo.setCompanyTicker(bo.getCompanyTicker().toLowerCase());
			}
			BeanUtils.copyProperties(bo , entity);
			entity.setId(null);
			entity.setStatus(SystemCodes.RecordStatus.ACTIVE.getValue());
		}
		return entity;
	}

	@Override
	public Company copyUpdatedBOToEntity(CompanyBO bo, Company entity, Long modifierId) {
		if(bo!=null && entity!=null){
			entity.setCompanyName(bo.getCompanyName());
			if(StringUtils.isNotBlank(bo.getCompanyTicker())){
				bo.setCompanyTicker(bo.getCompanyTicker().toLowerCase());
			}
			entity.setCompanyTicker(bo.getCompanyTicker());
			entity.setCompanyLogoBig(bo.getCompanyLogoBig());
			entity.setCompanyLogoSmall(bo.getCompanyLogoSmall());
			entity.setCompanyTeaser(bo.getCompanyTeaser());
			entity.setCompanyURL(bo.getCompanyURL());
			entity.setCompanyDescription(bo.getCompanyDescription());
			entity.setCountry(bo.getCountry());
			entity.setCompanySize(bo.getCompanySize());
			entity.setCompanyShareholders(bo.getCompanyShareholders());
			entity.setIsRegistered(bo.getIsRegistered());
			entity.setShareRegistryId(bo.getShareRegistryId());
			entity.setSector(bo.getSector());
			entity.setIndustry(bo.getIndustry());
			entity.setSubIndustry(bo.getSubIndustry());
			entity.setCompanyType(bo.getCompanyType());
			entity.setCompanyEmail(bo.getCompanyEmail());

			copyUpdatedCommonFields(bo, entity);
		}
		
		return entity;
	}

}
