package com.omt.cms.master.repo.op;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.master.entity.Feed;
import com.omt.cms.repo.op.base.CrudOperations;

@Component
public interface FeedOperations extends CrudOperations<Feed> {
	
	public static final String ENTITY_TABLE_NAME="feeds";

	public List<Feed> getActiveFeeds();
	
	public List<Feed> findByAllWithLimit();

	public List<Feed> findByStatus(int status);
	
	public List<Feed> findByValidUntil(Timestamp vtd);

	public List<Feed> findByStatusValidUntil(int status, Timestamp vtd);
	
	public List<Feed> findByFilters(FilterCriteriaBO filters);
	
	public List<Feed> findByFiltersAdmins(FilterCriteriaBO filters);
	
	public Long countByFiltersAdmins(FilterCriteriaBO filters);

}
