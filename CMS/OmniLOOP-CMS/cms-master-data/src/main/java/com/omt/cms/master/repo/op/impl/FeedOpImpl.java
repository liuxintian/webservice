package com.omt.cms.master.repo.op.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.SearchCriteriaBuilder;
import com.omt.cms.master.entity.Feed;
import com.omt.cms.master.repo.FeedRepository;
import com.omt.cms.master.repo.op.FeedOperations;
import com.omt.cms.repo.op.base.BaseCrudOpImpl;

/**
 * @author muragesh
 *
 */
@Component
public class FeedOpImpl extends BaseCrudOpImpl<Feed> implements FeedOperations{

	@Autowired FeedRepository feedRepo;
	public static final String DEFAULT_SORT_ON = "feedTitle";
	
	public FeedOpImpl() {
		this.entityType = Feed.class;
	}
	
	@Override
	public PagingAndSortingRepository<Feed, Long> getRepository() {
		return feedRepo;
	}

	@Override
	public List<Feed> getActiveFeeds() {
		return feedRepo.findByStatus(RecordStatus.ACTIVE.getValue());
	}

	@Override
	public List<Feed> findByAllWithLimit() {
		PageRequest page = createPageRequest();
		return feedRepo.findAll(page).getContent();
	}

	@Override
	public List<Feed> findByStatus(int status) {
		PageRequest page = createPageRequest();
		return feedRepo.findByStatus(status, page);
	}

	@Override
	public List<Feed> findByValidUntil(Timestamp vtd) {
		PageRequest page = createPageRequest();
		return feedRepo.findByValidUntilAfter(vtd, page);
	}

	@Override
	public List<Feed> findByStatusValidUntil(int status, Timestamp vtd) {
		PageRequest page = createPageRequest();
		return feedRepo.findByStatusAndValidUntilAfter(status, vtd, page);
	}
	
	@Override
	public List<Feed> findByFilters(FilterCriteriaBO filters) {
		List<Feed> feeds = null;
		if(filters!=null){
			filters.entityName = ENTITY_TABLE_NAME;
			String query = SearchCriteriaBuilder.build(filters);
			if(StringUtils.isNotBlank(query)){
				feeds = findByNativeQuery(query);
			}else{
				feeds = new ArrayList<>(1);
			}
		}
		return feeds;
	}
	
	@Override
	public List<Feed> findByFiltersAdmins(FilterCriteriaBO filter) {
		List<Feed> feeds = null;
		if(filter!=null){
			filter.assignDefaultsIfNotDefined(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			PageRequest page = createPageRequest(filter.offset, filter.size, filter.sortField, filter.sortOrder);
			if(StringUtils.isNotBlank(filter.term)){
				feeds = feedRepo.searchByCriteria(filter.term, page);
			}else{
				feeds = feedRepo.findAll(page).getContent();
			}
		}else{
			PageRequest page = createPageRequest(0, SystemCodes.PAGE_SIZE, DEFAULT_SORT_ON, 1);
			feeds = feedRepo.findAll(page).getContent();
		}
		return feeds;
	}

	@Override
	public Long countByFiltersAdmins(FilterCriteriaBO filter) {
		Long total = null;
		if(filter!=null && StringUtils.isNotBlank(filter.term)){
			total = feedRepo.countByCriteria(filter.term);
		}else{
			total = feedRepo.count();
		}
		return total;
	}

}
