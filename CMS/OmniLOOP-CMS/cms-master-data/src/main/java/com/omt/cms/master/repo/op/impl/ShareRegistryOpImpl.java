/**
 * 
 */
package com.omt.cms.master.repo.op.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.SearchCriteriaBuilder;
import com.omt.cms.master.entity.ShareRegistry;
import com.omt.cms.master.repo.ShareRegistryRepository;
import com.omt.cms.master.repo.op.ShareRegistryOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class ShareRegistryOpImpl extends BaseCrudOpImpl<ShareRegistry> implements ShareRegistryOperations{

	@Autowired ShareRegistryRepository srRepo;
	public static final String DEFAULT_SORT_ON = "shareRegistry";
	
	public ShareRegistryOpImpl() {
		this.entityType = ShareRegistry.class;
	}

	@Override
	public PagingAndSortingRepository<ShareRegistry, Long> getRepository() {
		return srRepo;
	}

	@Override
	public List<ShareRegistry> getShareRegistries(int status) {
		return srRepo.findByStatus(status);
	}
	
	@Override
	public List<ShareRegistry> findByFiltersAdmins(FilterCriteriaBO filter) {
		List<ShareRegistry> registeries = null;
		if(filter!=null){
			filter.assignDefaultsIfNotDefined(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			PageRequest page = createPageRequest(filter.offset, filter.size, filter.sortField, filter.sortOrder);
			if(StringUtils.isNotBlank(filter.term)){
				registeries = srRepo.searchByCriteria(filter.term, page);	
			}else{
				registeries = srRepo.findAll(page).getContent();
			}
		}else{
			PageRequest page = createPageRequest(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			registeries = srRepo.findAll(page).getContent();
		}
		return registeries;
	}
	
	
	@Override
	public Long countByFiltersAdmins(FilterCriteriaBO filter) {
		Long total = null;
		if(filter!=null && StringUtils.isNotBlank(filter.term)){
			total = srRepo.countByCriteria(filter.term);
		}else{
			total = srRepo.count();
		}
		return total;
	}

	@Override
	public List<ShareRegistry> findByFilters(FilterCriteriaBO filters) {
		List<ShareRegistry> registries = null;
		if(filters!=null){
			filters.entityName = ENTITY_TABLE_NAME;
			String query = SearchCriteriaBuilder.build(filters);
			if(StringUtils.isNotBlank(query)){
				registries = findByNativeQuery(query);
			}else{
				registries = new ArrayList<>(1);
			}
		}
		return registries;
	}

}
