package com.omt.cms.master.data.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.master.repo.op.CompanyOperations;

public class CompanyOperationTest extends BaseRepoTest{
	
	@Autowired CompanyOperations cops;
	
	@Test
	public void findCompany(){
		String asx = "1AL";
		System.out.println(cops.findByCompanyTicker(asx));
	}
	
	
}
