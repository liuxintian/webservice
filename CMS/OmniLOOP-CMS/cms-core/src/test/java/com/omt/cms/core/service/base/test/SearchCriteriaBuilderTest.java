package com.omt.cms.core.service.base.test;

import org.junit.Test;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.SearchCriteriaBuilder;

public class SearchCriteriaBuilderTest {

	@Test
	public void test(){
		FilterCriteriaBO bo = new FilterCriteriaBO();
		//bo.tagRoleEmtpy = true;
		bo.entityName="announcements";
		bo.validUntil=DateHelper.getCurTimestamp();
		bo.status="20";
		String[] tags ={"one", "two", "three"}; 
		bo.tagRoles = tags; 
		System.out.println(SearchCriteriaBuilder.build(bo));
	}
}
