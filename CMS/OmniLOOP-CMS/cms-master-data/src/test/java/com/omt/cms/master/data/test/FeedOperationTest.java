package com.omt.cms.master.data.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.master.entity.Feed;
import com.omt.cms.master.repo.op.FeedOperations;

public class FeedOperationTest extends BaseRepoTest{
	
	@Autowired private FeedOperations feedOps;
	
	@Test
	public void findByFilters(){
		FilterCriteriaBO fcbo = new FilterCriteriaBO();
		fcbo.term="123";
		fcbo.offset=0;
		fcbo.size=1;
		List<Feed> feeds = feedOps.findByFiltersAdmins(fcbo);
		System.out.println(feeds.size());
	}

}
