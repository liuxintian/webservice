package com.omt.cms.cust.repo.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.cust.entity.CompanyInstance;
import com.omt.cms.cust.repo.LocalUserRepository;

public class LocalUserRepositoryTest extends RepoTestBase {
	@Autowired private LocalUserRepository lur;
	
	@Test
	public void test(){
		CompanyInstance ins = new CompanyInstance();
		ins.setId(1L);
		System.out.println(lur.findCompanySubscribers(ins, true, null).size());	
	}
}
