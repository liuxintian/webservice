package com.omt.cms.master.data.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.omt.cms.master.repo.FeedRepository;

public class FeedRepoTest extends BaseRepoTest{

	//@Autowired private FeedSpecification feeds;
	@Autowired private FeedRepository repo;
	
	@Test
	public void test(){
		Sort sort = new Sort(new Order("createdOn"));
		PageRequest page = new PageRequest(1, 1, sort);
		
		System.out.println(repo.searchByCriteria("Welcome".toLowerCase(), page).size());
		
	}
}
