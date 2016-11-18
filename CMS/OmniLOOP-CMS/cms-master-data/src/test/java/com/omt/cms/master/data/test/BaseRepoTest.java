package com.omt.cms.master.data.test;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com.omt.cms.master.data.config/postgres-beans.xml")
@EnableTransactionManagement
@PropertySource("classpath:/com.omt.cms.master.data.config/postgres.properties")
public class BaseRepoTest extends AbstractJUnit4SpringContextTests {

}
