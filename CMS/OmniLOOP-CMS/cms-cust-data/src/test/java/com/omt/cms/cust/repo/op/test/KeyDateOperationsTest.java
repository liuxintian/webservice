package com.omt.cms.cust.repo.op.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.entity.KeyDate;
import com.omt.cms.cust.repo.op.KeyDateOperations;
import com.omt.cms.cust.repo.test.RepoTestBase;

public class KeyDateOperationsTest extends RepoTestBase {

	@Autowired KeyDateOperations annops;
	
	@Test
	public void findByFilters(){
		FilterCriteriaBO bo = new FilterCriteriaBO();
		//bo.tagRoleEmtpy = true;
		bo.entityName=KeyDateOperations.ENTITY_TABLE_NAME;
		bo.validUntil=DateHelper.getCurTimestamp();
		bo.status="20";
		String[] tags ={"one", "two", "three"}; 
		bo.tagRoles = tags; 
		List<KeyDate> ann = annops.findByFilters(bo);
		System.out.println(ann.get(0).getTagRole());
	}
}
