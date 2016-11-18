/**
 * 
 */
package com.omt.cms.cust.repo.test;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Shiva Kalgudi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com.omt.cms.cust.data.config/postgres-beans.xml")
@EnableTransactionManagement
@PropertySource("classpath:/com.omt.cms.cust.data.config/postgres.properties")
public class RepoTestBase extends AbstractJUnit4SpringContextTests {

}
