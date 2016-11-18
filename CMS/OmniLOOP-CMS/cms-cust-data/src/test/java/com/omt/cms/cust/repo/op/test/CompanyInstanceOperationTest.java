package com.omt.cms.cust.repo.op.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.op.CompanyInstanceOperations;
import com.omt.cms.cust.repo.test.RepoTestBase;


public class CompanyInstanceOperationTest extends RepoTestBase {
	@Autowired private CompanyInstanceOperations<CompanyInstance> ops;
	
	@Test
	public void testCreate(){
		CompanyInstance instance = new CompanyInstance();
		instance.setId(1L);
		instance.setCompanyName("Test Company");
		ops.add(instance);
		instance = ops.findById(1L);
		System.out.println(instance.getCompanyName());
	}

}
