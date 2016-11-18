package com.omt.cms.master.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.service.CompanyService;
import com.omt.cms.master.service.bo.CompanyBO;
import com.omt.cms.master.service.test.base.ServiceTestBase;

public class CompanyServiceTest extends ServiceTestBase {

	@Autowired CompanyService cpSrv;

	@Test
	public void test() {
		ServiceRequest req = new ServiceRequest();
		CompanyBO cp = new CompanyBO();
		cp.setCompanyName("validsrvtest");
		req.addRequestData(cp);
		ServiceResponse res = cpSrv.add(req);
		CompanyBO cpRes = res.getResponseData(CompanyBO.class);
		Assert.assertNotNull(cpRes);
	}
}
