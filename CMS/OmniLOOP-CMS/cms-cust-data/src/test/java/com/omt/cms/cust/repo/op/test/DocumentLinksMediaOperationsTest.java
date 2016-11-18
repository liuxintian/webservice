package com.omt.cms.cust.repo.op.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.core.common.DateHelper;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.cust.repo.op.DocumentLinksMediaOperations;
import com.omt.cms.cust.repo.op.KeyDateOperations;
import com.omt.cms.cust.repo.test.RepoTestBase;
import com.omt.cms.entity.DocumentLinksMedia;

public class DocumentLinksMediaOperationsTest extends RepoTestBase {

	@Autowired DocumentLinksMediaOperations annops;
	
	@Test
	public void findByFilters(){
		FilterCriteriaBO bo = new FilterCriteriaBO();
		//bo.tagRoleEmtpy = true;
		bo.entityName=KeyDateOperations.ENTITY_TABLE_NAME;
		bo.validUntil=DateHelper.getCurTimestamp();
		bo.status="20";
		String[] tags ={"one", "two", "three"}; 
		bo.tagRoles = tags; 
		List<DocumentLinksMedia> ann = annops.findByFilters(bo);
		System.out.println(ann.get(0).getTagRole());
	}
}
