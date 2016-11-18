/**
 * 
 */
package com.omt.cms.master.data.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.CompanyRepository;

/**
 * @author muragesh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com.omt.cms.master.data.config/postgres-beans.xml")
@EnableTransactionManagement
@PropertySource("classpath:/com.omt.cms.master.data.config/postgres.properties")
public class CompanyRepoTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	CompanyRepository repo;

	//@Test
	public void add() {
		Company cp = new Company();
		cp.setCompanyName("test");
		cp.setStatus(RecordStatus.ACTIVE.getValue());
		assertNotNull(repo.save(cp));
	}
	
	@Test
	public void fetch(){
		System.out.println(repo.findByStatus(20));
	}
}
