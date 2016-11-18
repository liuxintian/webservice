package com.omt.cms.master.repo.op;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.master.entity.ShareRegistry;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface ShareRegistryOperations extends CrudOperations<ShareRegistry> {

	public static final String ENTITY_TABLE_NAME="share_registries";
	
	public List<ShareRegistry> getShareRegistries(int status);	
	
	public List<ShareRegistry> findByFilters(FilterCriteriaBO filters);
	
	public List<ShareRegistry> findByFiltersAdmins(FilterCriteriaBO filters);

	public Long countByFiltersAdmins(FilterCriteriaBO filters);

}
