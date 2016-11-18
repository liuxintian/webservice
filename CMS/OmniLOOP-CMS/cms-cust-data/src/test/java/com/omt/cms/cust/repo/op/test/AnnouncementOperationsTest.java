package com.omt.cms.cust.repo.op.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.Announcement;
import com.omt.cms.cust.repo.op.AnnouncementOperations;
import com.omt.cms.cust.repo.test.RepoTestBase;

public class AnnouncementOperationsTest extends RepoTestBase {

	@Autowired AnnouncementOperations annops;
	
	@Test
	public void findByFilters(){
		FilterCriteriaBO bo = new FilterCriteriaBO();
		//bo.tagRoleEmtpy = true;
		bo.entityName="announcements";
		bo.validUntil=DateHelper.getCurTimestamp();
		bo.status="20";
		String[] tags ={"one", "two", "three"}; 
		bo.tagRoles = tags; 
		List<Announcement> ann = annops.findByFilters(bo);
		System.out.println(ann.get(0).getTagRole());
	}
}
